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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import net.multiphasicapps.squirreljme.kernel.display.ConsoleDisplay;
import net.multiphasicapps.squirreljme.kernel.event.EventQueue;
import net.multiphasicapps.squirreljme.kernel.Kernel;
import net.multiphasicapps.squirreljme.kernel.perm.PermissionManager;

/**
 * This is the base class for the kernel interfaces which are defined by
 * systems to provide anything that the default kernel does not provide
 * when it comes to interfaces. All calls which are done by the user interface
 * and the running programs in the kernel, will call the kernel to perform
 * system calls and such. The kernel manages processes which are running on
 * the virtual machine.
 *
 * @since 2016/05/14
 */
public abstract class Kernel
{
	/** Threads currently associated with the kernel. */
	@Deprecated
	protected final Set<Thread> threads =
		new HashSet<>();
	
	/** The kernel process. */
	private final KernelProcess _kernelprocess;
	
	/** Kernel processes. */
	private final List<KernelProcess> _processes =
		new ArrayList<>();
	
	/**
	 * Initializes the base kernel interface.
	 *
	 * @since 2016/05/16
	 */
	public Kernel()
	{
		// Setup kernel process
		KernelProcess kp = new KernelProcess(this, true);
		kp.__addThread(Thread.currentThread());
		this._kernelprocess = kp;
		
		// Kernel process is a global one
		this._processes.add(kp);
	}
	
	/**
	 * Creates a view of a console window.
	 *
	 * Note that if multi-headed consoles are supported then the interface
	 * may show multiple terminals either in windows, tabs, or some other
	 * interface specific means. If a console does not support multiple heads
	 * then any console being displayed will potentially erase or draw over
	 * a previously drawn console.
	 *
	 * @return A newly created console window or {@code null} if it could not
	 * be created for some reason.
	 * @since 2016/05/14
	 */
	public abstract ConsoleDisplay createConsoleDisplay();
	
	/**
	 * Returns the process which is associated with the current thread.
	 *
	 * @return The process for the current thread, if {@code null} then no
	 * process owns the thread.
	 * @since 2016/05/16
	 */
	public final KernelProcess currentProcess()
	{
		return processByThread(Thread.currentThread());
	}
	
	/**
	 * Returns the kernel process.
	 *
	 * @return The kernel process.
	 * @since 2016/05/16
	 */
	public final KernelProcess kernelProcess()
	{
		return _kernelprocess;
	}
	
	/**
	 * Creates a new thread and registers it with the kernel.
	 *
	 * @param __r The code to be ran when the thread is started.
	 * @return The newly created thread.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/05/14
	 */
	@Deprecated
	public final Thread newThread(Runnable __r)
		throws NullPointerException
	{
		// Check
		if (__r == null)
			throw new NullPointerException("NARG");
		
		// Setup new thread and start it
		Thread rv = new Thread(__r);
		rv.start();
		
		// Lock
		Set<Thread> thr = threads;
		synchronized (thr)
		{
			thr.add(rv);
		}
		
		// Return the newly created thread
		return rv;
	}
	
	/**
	 * Locates the process that owns the given thread.
	 *
	 * @param __t The thread to get the process of.
	 * @return The process which owns the given thread or {@code null} if no
	 * process is bound to it.
	 * @since 2016/05/16
	 */
	public final KernelProcess processByThread(Thread __t)
	{
		// Lock
		List<KernelProcess> kps = this._processes;
		synchronized (kps)
		{
			// Go through all of them
			for (KernelProcess kp : kps)
				throw new Error("TODO");
		}
		
		// Not found
		return null;
	}
	
	/**
	 * Does not return until all threads are no longer alive.
	 *
	 * @since 2016/05/14
	 */
	@Deprecated
	public final void untilThreadless()
	{
		// Loop
		Set<Thread> thr = threads;
		for (;;)
		{
			// Lock
			int livecount = 0;
			synchronized (thr)
			{
				// Count and remove threads
				Iterator<Thread> it = thr.iterator();
				try
				{
					// Go through each iteration
					for (;;)
					{
						// Get the next thread
						Thread t = it.next();
						
						// If the thread is alive, count it
						if (t.isAlive())
							livecount++;
						
						// Otherwise remove it
						else
							it.remove();
					}
				}
				
				// End
				catch (NoSuchElementException e)
				{
				}
			}
			
			// Out of threads?
			if (livecount < 0)
				return;
			
			// Rest for a bit since threads usually will not just die.
			try
			{
				Thread.sleep(750L);
			}
			
			// Do nothing
			catch (InterruptedException e)
			{
			}
		}
	}
}

