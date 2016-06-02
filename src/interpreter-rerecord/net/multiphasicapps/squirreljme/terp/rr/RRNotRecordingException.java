// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU Affero General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.terp.rr;

/**
 * This is thrown when an attempt is made to record to a stream which is not
 * being recorded into.
 *
 * @since 2016/06/01
 */
public class RRNotRecordingException
	extends RuntimeException
{
	/**
	 * Initializes the exception with the given message.
	 *
	 * @param __m The message to use.
	 * @since 2016/06/01
	 */
	public RRNotRecordingException(String __m)
	{
		super(__m);
	}
	
	/**
	 * Initializes the exception with the given message and cause.
	 *
	 * @param __m The message to use.
	 * @param __t The cause to use.
	 * @since 2016/06/01
	 */
	public RRNotRecordingException(String __m, Throwable __t)
	{
		super(__m, __t);
	}
}

