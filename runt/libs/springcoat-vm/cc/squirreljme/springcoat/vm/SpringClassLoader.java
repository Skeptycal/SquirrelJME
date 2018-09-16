// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.springcoat.vm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.multiphasicapps.classfile.ClassFile;
import net.multiphasicapps.classfile.ClassName;
import net.multiphasicapps.classfile.FieldDescriptor;
import net.multiphasicapps.classfile.InvalidClassFormatException;

/**
 * This class acts as the equivalent to {@code ClassLoader} in that it manages
 * the class path and the eventual loading of classes.
 *
 * @since 2018/09/01
 */
public final class SpringClassLoader
{
	/** Class loading lock. */
	protected final Object loaderlock =
		new Object();
	
	/** The class path for the machine. */
	private final SpringClassLibrary[] _classpath;
	
	/** The classes which have been loaded by the virtual machine. */
	private final Map<FieldDescriptor, SpringClass> _classes =
		new HashMap<>();
	
	/**
	 * Initializes the class loader.
	 *
	 * @param __classpath The classpath.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/09/01
	 */
	public SpringClassLoader(SpringClassLibrary... __classpath)
		throws NullPointerException
	{
		for (SpringClassLibrary b : __classpath = (__classpath == null ?
			new SpringClassLibrary[0] : __classpath.clone()))
			if (b == null)
				throw new NullPointerException("NARG");
		this._classpath = __classpath;
	}
	
	/**
	 * Returns the library that is used for booting, the main entry JAR.
	 *
	 * @return The boot library.
	 * @since 2018/09/13
	 */
	public final SpringClassLibrary bootLibrary()
	{
		SpringClassLibrary[] classpath = this._classpath;
		return classpath[classpath.length - 1];
	}
	
	/**
	 * Returns the class loading lock.
	 *
	 * @return The class loading lock.
	 * @since 2018/09/08
	 */
	public final Object classLoadingLock()
	{
		return this.loaderlock;
	}
	
	/**
	 * Loads the specified class.
	 *
	 * @param __cn The name of the class to load.
	 * @return The loaded class.
	 * @throws NullPointerException On null arguments.
	 * @throws SpringClassFormatException If the class is not formatted
	 * properly.
	 * @throws SpringClassNotFoundException If the class was not found.
	 * @since 2018/09/16
	 */
	public final SpringClass loadClass(ClassName __cn)
		throws NullPointerException, SpringClassFormatException,
			SpringClassNotFoundException
	{
		if (__cn == null)
			throw new NullPointerException("NARG");
		
		return this.loadClass(__cn.field());
	}
	
	/**
	 * Loads the specified class from a field descriptor to allow for special
	 * classes.
	 *
	 * @param __cn The name of the class to load.
	 * @return The loaded class.
	 * @throws NullPointerException On null arguments.
	 * @throws SpringClassFormatException If the class is not formatted
	 * properly.
	 * @throws SpringClassNotFoundException If the class was not found.
	 * @since 2018/09/01
	 */
	public final SpringClass loadClass(FieldDescriptor __cn)
		throws NullPointerException, SpringClassFormatException,
			SpringClassNotFoundException
	{
		if (__cn == null)
			throw new NullPointerException("NARG");
		
		// Lock on classes
		Map<FieldDescriptor, SpringClass> classes = this._classes;
		synchronized (this.loaderlock)
		{
			// If the class has already been initialized, use that
			SpringClass rv = classes.get(__cn);
			if (rv != null)
				return rv;
			
			// Debug
			todo.DEBUG.note("Loading class `%s`...", __cn);
			
			// Load class file for this class
			ClassFile cf = this.loadClassFile(__cn);
			
			// Load the super class
			ClassName supername = cf.superName();
			SpringClass superclass = (supername == null ? null :
				this.loadClass(supername));
			
			// Load any interfaces
			ClassName[] interfacenames = cf.interfaceNames();
			int numinterfaces = interfacenames.length;
			SpringClass[] interfaceclasses = new SpringClass[numinterfaces];
			for (int i = 0; i < numinterfaces; i++)
				interfaceclasses[i] = this.loadClass(interfacenames[i]);
			
			// Load class information
			rv = new SpringClass(superclass, interfaceclasses, cf, __cn);
			
			// Store for later use
			classes.put(__cn, rv);
			
			return rv;
		}
	}
	
	/**
	 * This goes through the classpath and loads the specified class file for
	 * the given class.
	 *
	 * @param __cn The class to load.
	 * @return The loaded class file data.
	 * @throws NullPointerException On null arguments.
	 * @throws SpringClassFormatException If the class is not formatted
	 * properly.
	 * @throws SpringClassNotFoundException If the class was not found.
	 * @since 2018/09/01
	 */
	public final ClassFile loadClassFile(FieldDescriptor __cn)
		throws NullPointerException, SpringClassFormatException,
			SpringClassNotFoundException
	{
		if (__cn == null)
			throw new NullPointerException("NARG");
		
		// Debug
		todo.DEBUG.note("Loading class file `%s`...", __cn);
		
		// If this is an array or primitive type use virtual class
		// representation because these classes do not really exist
		if (__cn.isPrimitive() || __cn.isArray())
			return ClassFile.special(__cn);
		
		// Use class name
		ClassName inclass = __cn.className();
		
		// This is the class that is read, in binary form
		String fileform = inclass.toString() + ".class";
		
		// Otherwise we need to go through every single binary to find
		// the class we want, which can take awhile
		byte[] data = null;
		for (SpringClassLibrary b : this._classpath)
			try (InputStream in = b.resourceAsStream(fileform))
			{
				// Class or file does not exist
				if (in == null)
					continue;
				
				// Read in the data
				byte[] buf = new byte[512];
				try (ByteArrayOutputStream baos =
					new ByteArrayOutputStream(1024))
				{
					for (;;)
					{
						int rc = in.read(buf);
						
						if (rc < 0)
						{
							baos.flush();
							data = baos.toByteArray();
							break;
						}
						
						baos.write(buf, 0, rc);
					}
				}
				
				break;
			}
			catch (IOException e)
			{
				// {@squirreljme.error BK03 Failed to read from the class
				// path.}
				throw new SpringException("BK03", e);
			}
		
		// {@squirreljme.error BK02 Could not locate the specified class.
		// (The class which was not found; The class file which was
		// attempted to be located)}
		if (data == null)
			throw new SpringClassNotFoundException(__cn, String.format(
				"BK02 %s %s", __cn, fileform));
		
		// Decode class file
		ClassFile cf;
		try (ByteArrayInputStream bais = new ByteArrayInputStream(data))
		{
			return ClassFile.decode(bais);
		}
		catch (IOException e)
		{
			// {@squirreljme.error BK05 Could not read from the source
			// class file. (The class being read)}
			throw new SpringVirtualMachineException(String.format(
				"BK05 %s", __cn), e);
		}
		catch (InvalidClassFormatException e)
		{
			// {@squirreljme.error BK04 The class is not formatted
			// correctly. (The class being read)}
			throw new SpringClassFormatException(__cn, String.format(
				"BK04 %s", __cn), e);
		}
	}
}

