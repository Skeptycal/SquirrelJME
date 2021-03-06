// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.cldc.asm;

/**
 * This class provides access to tasks which are running.
 *
 * @since 2018/11/04
 */
public final class TaskAccess
{
	/** The entry point is not valid. */
	public static final int ERROR_INVALID_ENTRY =
		-2;
	
	/** Exit code indicating bad task things. */
	public static final int EXIT_CODE_FATAL_EXCEPTION =
		127;
	
	/**
	 * Not used.
	 *
	 * @since 2018/11/04
	 */
	private TaskAccess()
	{
	}
	
	/**
	 * Causes the thread to sleep for the given milliseconds and nanoseconds.
	 *
	 * @param __ms The milliseconds to sleep for.
	 * @param __ns The nanoseconds to sleep for, in the range of 0-999999.
	 * @return {@code true} if the thread was interrupted, otherwise
	 * {@code false}.
	 * @since 2018/11/04
	 */
	public static final native boolean sleep(long __ms, int __ns);
	
	/**
	 * Starts the specified task.
	 *
	 * @param __cp The classpath used.
	 * @param __main The main entry point.
	 * @param __args Arguments to start the task with.
	 * @return The task identifier or a negative number if the task could
	 * not start.
	 * @since 2018/11/04
	 */
	public static final native int startTask(String[] __cp, String __main,
		String[] __args);
	
	/**
	 * Returns the status of the target task.
	 *
	 * @param __tid The task to get the status of.
	 * @return The status for the given task.
	 * @since 2018/11/04
	 */
	public static final native int taskStatus(int __tid);
}

