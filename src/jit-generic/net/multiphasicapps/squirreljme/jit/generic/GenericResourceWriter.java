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
import net.multiphasicapps.squirreljme.jit.base.JITException;
import net.multiphasicapps.squirreljme.jit.JITResourceWriter;
import net.multiphasicapps.squirreljme.os.generic.BlobContentType;

/**
 * This writes resources to the output namespace.
 *
 * @since 2016/07/27
 */
public final class GenericResourceWriter
	extends __BaseWriter__
	implements JITResourceWriter
{
	/** Has this been closed? */
	private volatile boolean _closed;
	
	/** The resource information. */
	protected final __Resource__ modresource;
	
	/**
	 * Initializes the generic resource writer.
	 *
	 * @param __nsw The owning namespace writer.
	 * @param __rc The resource module.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/07/27
	 */
	GenericResourceWriter(GenericNamespaceWriter __nsw, __Resource__ __rc)
		throws NullPointerException
	{
		super(__nsw);
		
		// Check
		if (__rc == null)
			throw new NullPointerException("NARG");
		
		// Set
		this.modresource = __rc;
		
		// {@squirreljme.error BA0t A resource cannot start beyond 2GiB.}
		long ds = this.outdata.size();
		if (ds < 0 || ds > Integer.MAX_VALUE)
			throw new JITException("BA0t");
		__rc._datastart = (int)ds;
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
			// Store the position where the data ends 
			if (!this._closed)
			{
				// Mark closed
				this._closed = true;
				
				// {@squirreljme.error BA0s A resource exceeds the
				// end of the data stream.}
				long ds = this.outdata.size();
				if (ds < 0 || ds > Integer.MAX_VALUE)
					throw new JITException("BA0s");
				this.modresource._dataend = (int)ds;
			}
			
			// Super close
			super.close();
		}
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/07/27
	 */
	@Override
	public void write(byte[] __b, int __o, int __l)
		throws IndexOutOfBoundsException, JITException, NullPointerException
	{
		try
		{
			// Lock
			synchronized (this.lock)
			{
				// {@squirreljme.error BA0d Cannot write resource bytes after
				// the stream has been closed.}
				if (this._closed)
					throw new JITException("BA0d");
				
				// Write
				this.outdata.write(__b, __o, __l);
			}
		}
		
		// {@squirreljme.error BA0e Failed to write the resource bytes.}
		catch (IOException e)
		{
			throw new JITException("BA0e", e);
		}
	}
}

