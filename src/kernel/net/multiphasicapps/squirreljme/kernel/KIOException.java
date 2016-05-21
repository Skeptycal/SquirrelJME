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

import java.io.IOException;

/**
 * This represents an exception which is thrown by the kernel IPC datagram
 * system.
 *
 * @since 2016/05/20
 */
public final class KIOException
	extends IOException
{
	/**
	 * Initializes the exception with the given message.
	 *
	 * @param __s The message to use.
	 * @since 2016/05/20
	 */
	public KIOException(String __s)
	{
		super(__s);
	}
	
	/**
	 * Initializes the exception with the given message and cause.
	 *
	 * @param __s The message to use.
	 * @param __c The cause of the exception.
	 * @since 2016/05/21
	 */
	public KIOException(String __s, Throwable __c)
	{
		super(__s, __c);
	} 
}

