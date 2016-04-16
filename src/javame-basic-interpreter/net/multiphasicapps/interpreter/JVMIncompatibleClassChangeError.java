// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU Affero General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.interpreter;

/**
 * This is thrown when a class which was previously compiled along with other
 * classes which were recompiled have been broken interface wise.
 *
 * @since 2016/04/09
 */
public class JVMIncompatibleClassChangeError
	extends JVMEngineException
{
	/**
	 * Initializes the exception with no message.
	 *
	 * @param __f The execution frame.
	 * @since 2016/04/09
	 */
	public JVMIncompatibleClassChangeError(JVMFrameable __f)
	{
		super(__f);
	}
	
	/**
	 * Initializes exception with the given message.
	 *
	 * @param __f The execution frame.
	 * @param __msg The exception message.
	 * @since 2016/04/09
	 */
	public JVMIncompatibleClassChangeError(JVMFrameable __f, String __msg)
	{
		super(__f, __msg);
	}
	
	/**
	 * Initializes exception with the given message and cause.
	 *
	 * @param __f The execution frame.
	 * @param __msg The exception message.
	 * @param __c The cause.
	 * @since 2016/04/09
	 */
	public JVMIncompatibleClassChangeError(JVMFrameable __f, String __msg,
		Throwable __c)
	{
		super(__f, __msg, __c);
	}
	
	/**
	 * Initializes the exception with the given cause and no message.
	 *
	 * @param __f The execution frame.
	 * @param __c The cause of the exception.
	 * @since 2016/04/09
	 */
	public JVMIncompatibleClassChangeError(JVMFrameable __f, Throwable __c)
	{
		super(__f, __c);
	}
}

