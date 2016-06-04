// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU Affero General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.kernel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import net.multiphasicapps.squirreljme.classpath.ClassPath;

/**
 * This represents a process within the kernel which where
 * {@link KernelThread}s are assigned to for performing work.
 *
 * Processes may be assigned memory maps and other details, as such the
 * implementation of the kernel must implement processes.
 *
 * @since 2016/05/16
 */
public abstract class KernelProcess
	implements __Identifiable__
{
	/** The owning kernel. */
	protected final Kernel kernel;
	
	/** The process ID. */
	protected final int id;
	
	/** The classpath used for the process. */
	protected final ClassPath classpath;
	
	/**
	 * Initializes the kernel process.
	 *
	 * @param __k The owning kernel.
	 * @param __cp The classpath used for class lookup.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/05/29
	 */
	public KernelProcess(Kernel __k, ClassPath __cp)
		throws NullPointerException
	{
		// Check
		if (__k == null || __cp == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.kernel = __k;
		this.id = __k._processesidgen.__next();
		this.classpath = (__cp = Objects.<ClassPath>requireNonNull(
			adjustClassPath(__cp)));
	}
	
	/**
	 * Adjusts the class path to be used by the process.
	 *
	 * @param __cp The class path to use.
	 * @return The adjusted class path.
	 * @since 2016/06/03
	 */
	protected ClassPath adjustClassPath(ClassPath __cp)
	{
		return __cp;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/05/29
	 */
	@Override
	public final int id()
	{
		return this.id;
	}
	
	/**
	 * Returns the alternative socket implementation that this process uses.
	 *
	 * @return The alternative socket implementation.
	 * @since 2016/05/31
	 */
	public final KernelIPCAlternative ipcAlternative()
	{
		throw new Error("TODO");
	}
	
	/**
	 * Returns the kernel which owns this process.
	 *
	 * @return The owning kernel.
	 * @since 2016/06/03
	 */
	public final Kernel kernel()
	{
		return this.kernel;
	}
}

