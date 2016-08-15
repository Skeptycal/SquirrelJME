// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.jit.generic;

import java.io.IOException;
import java.util.Objects;
import net.multiphasicapps.squirreljme.java.symbols.ClassNameSymbol;
import net.multiphasicapps.squirreljme.jit.base.JITClassFlag;
import net.multiphasicapps.squirreljme.jit.base.JITClassFlags;
import net.multiphasicapps.squirreljme.jit.base.JITException;
import net.multiphasicapps.squirreljme.jit.JITClassWriter;
import net.multiphasicapps.squirreljme.jit.JITCompilerOrder;
import net.multiphasicapps.squirreljme.jit.JITConstantPool;
import net.multiphasicapps.squirreljme.os.generic.BlobContentType;
import net.multiphasicapps.squirreljme.os.generic.GenericBlob;
import net.multiphasicapps.io.data.ExtendedDataOutputStream;

/**
 * This writes classes to the output namespace.
 *
 * @since 2016/07/27
 */
public final class GenericClassWriter
	extends __BaseWriter__
	implements JITClassWriter
{
	/** The name of the class being written. */
	protected final ClassNameSymbol classname;
	
	/** The current order. */
	private volatile JITCompilerOrder _order =
		JITCompilerOrder.FIRST;
	
	/** Has this been closed? */
	private volatile boolean _closed;
	
	/** The constant pool to use. */
	private volatile JITConstantPool _pool;
	
	/** The string table position. */
	private volatile int _stringpos;
	
	/** The number of strings in the table. */
	private volatile int _stringcount;
	
	/** The constant pool table position. */
	private volatile int _poolpos;
	
	/** The number of entries in the constant pool. */
	private volatile int _poolcount;
	
	/** Class flags, written later. */
	private volatile JITClassFlags _flags;
	
	/** The super class pool ID. */
	private volatile int _scpooldx;
	
	/** The interface count. */
	private volatile int _ifacecount;
	
	/** The pointer where interfaces are stored. */
	private volatile int _ifacepos;
	
	/** The index in the constant pool containing the current class name. */
	private volatile int _nameindex;
	
	/**
	 * Initializes the generic class writer.
	 *
	 * @param __nsw The owning namespace writer.
	 * @param __dos The stream to write to.
	 * @param __cn The class name.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/07/27
	 */
	GenericClassWriter(GenericNamespaceWriter __nsw,
		ExtendedDataOutputStream __dos, ClassNameSymbol __cn)
		throws NullPointerException
	{
		super(__nsw, __dos, BlobContentType.CLASS, __cn.toString());
		
		// Check
		if (__cn == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.classname = __cn;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/07/27
	 */
	@Override
	public void classFlags(JITClassFlags __cf)
		throws JITException, NullPointerException
	{
		// Check
		if (__cf == null)
			throw new NullPointerException("NARG");
		
		// Lock
		synchronized (this.lock)
		{
			// Check order
			__order(JITCompilerOrder.CLASS_FLAGS);
			
			// Write later
			this._flags = __cf;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/07/27
	 */
	@Override
	public void close()
		throws JITException
	{
		// Lock
		synchronized (this.lock)
		{
			// Close if not closed
			if (!this._closed)
			{
				// Mark closed
				this._closed = true;
				
				// Could fail
				try
				{
					// Get output
					ExtendedDataOutputStream dos = this.output;
					
					// Align
					while ((dos.size() & 3) != 0)
						dos.writeByte(0);
					
					// String table
					dos.writeInt(this._stringpos);
					dos.writeShort(this._stringcount);
					
					// Current class name
					dos.writeShort(this._nameindex);
					
					// Constant pool
					dos.writeInt(this._poolpos);
					dos.writeShort(this._poolcount);
					
					// The super class name
					dos.writeShort(this._scpooldx);
					
					// The interfaces implemented
					dos.writeInt(this._ifacepos);
					dos.writeShort(this._ifacecount);
					
					// The class flags
					int flags = 0;
					for (JITClassFlag f : this._flags)
						flags |= (1 << f.ordinal());
					dos.writeShort(flags);
					
					// Field table
					System.err.println("TODO -- Write fields at end.");
					dos.writeInt(0);	// offset
					dos.writeShort(0);	// size
					
					// Method table
					System.err.println("TODO -- Write methods at end.");
					dos.writeShort(0);	// size
					dos.writeInt(0);	// offset
					
					// End with magic number
					dos.writeInt(GenericBlob.END_CLASS_MAGIC_NUMBER);
				}
			
				// {@squirreljme.error BA11 Failed to write the end of the
				// class.}
				catch (IOException e)
				{
					throw new JITException("BA11", e);
				}
			}
			
			// Super close
			super.close();
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/08/12
	 */
	@Override
	public void constantPool(JITConstantPool __pool, int __cndx)
	{
		// Check
		if (__pool == null)
			throw new NullPointerException("NARG");
		
		// Lock
		synchronized (this.lock)
		{
			// Check order
			__order(JITCompilerOrder.SET_CONSTANT_POOL);
			
			// Just set the pool
			this._pool = __pool;
			
			// Set the name index
			this._nameindex = __cndx;
			
			// Setup pool writer then write it
			try
			{
				// Write the pool
				__PoolWriter__ pw = new __PoolWriter__(__pool);
				pw.__write(this.output);
				
				// Store the string table position and the constant pool
				// position
				this._stringpos = pw._stringpos;
				this._stringcount = pw._stringcount;
				this._poolpos = pw._poolpos;
				this._poolcount = pw._poolcount;
			}
			
			// {@squirreljme.error BA0u Failed to write the constant pool.}
			catch (IOException e)
			{
				throw new JITException("BA0u", e);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/07/27
	 */
	@Override
	public void interfaceClasses(ClassNameSymbol[] __ins, int[] __dxs)
		throws JITException, NullPointerException
	{
		// Check
		if (__ins == null || __dxs == null)
			throw new NullPointerException("NARG");
		
		// Lock
		synchronized (this.lock)
		{
			// Check order
			__order(JITCompilerOrder.INTERFACE_CLASS_NAMES);
			
			// Could fail
			try
			{
				// Get output
				ExtendedDataOutputStream dos = this.output;
				
				// Align
				while ((dos.size() & 3) != 0)
					dos.writeByte(0);
			
				// {@squirreljme.error BA0z The interface table starts at a
				// position outside the range of 2GiB.}
				long pos = dos.size();
				if (pos < 0 || pos > Integer.MAX_VALUE)
					throw new JITException("BA0z");
				this._ifacepos = (int)pos;
			
				// Write
				int n = __ins.length;
				this._ifacecount = n;
				for (int i = 0; i < n; i++)
					dos.writeShort(__dxs[i]);
			}
			
			// {@squirreljme.error BA10 Failed to write the interface table.}
			catch (IOException e)
			{
				throw new JITException("BA10", e);
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/07/27
	 */
	@Override
	public void superClass(ClassNameSymbol __cn, int __dx)
		throws JITException
	{
		// Lock
		synchronized (this.lock)
		{
			// Check order
			__order(JITCompilerOrder.SUPER_CLASS_NAME);
			
			// Set
			this._scpooldx = __dx;
		}
	}
	
	/**
	 * Checks that the current order is the given expected order and proceeds
	 * to the next order.
	 *
	 * @param __exp The current order that is expected.
	 * @throws JITException If the order is not correct.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/07/22
	 */
	private void __order(JITCompilerOrder __exp)
		throws JITException, NullPointerException
	{
		// Check
		if (__exp == null)
			throw new NullPointerException("NARG");
		
		// Lock
		synchronized (this.lock)
		{
			// {@squirreljme.error BA0j JIT invocation is not in order.
			// (The order that was attempted to be used; The expected order)}
			JITCompilerOrder order = this._order;
			if (order != __exp)
				throw new JITException(String.format("BA0j %s %s", __exp,
					order));
			
			// Set next
			this._order = order.next();
		}
	}
}

