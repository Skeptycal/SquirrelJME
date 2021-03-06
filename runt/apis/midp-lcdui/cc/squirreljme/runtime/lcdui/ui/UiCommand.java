// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.lcdui.ui;

import cc.squirreljme.runtime.lcdui.CollectableType;

/**
 * This represents a command which is used as an abstract representation of
 * a command which may be executed by the client.
 *
 * @since 2018/04/04
 */
public final class UiCommand
	implements UiCollectable, UiHasSettableImage, UiHasSettableLabel,
		UiHasSettableLongLabel, UiInterface
{
	/** The handle for the command. */
	protected final int handle;
	
	/**
	 * Initializes the command.
	 *
	 * @param __handle The handle for the command.
	 * @since 2018/04/05
	 */
	public UiCommand(int __handle)
	{
		this.handle = __handle;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/04/04
	 */
	@Override
	public final void cleanup()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/04/04
	 */
	@Override
	public final CollectableType collectableType()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/04/04
	 */
	@Override
	public final UiImage getImage()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/04/04
	 */
	@Override
	public final String getLabel()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/04/04
	 */
	@Override
	public final String getLongLabel()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/04/04
	 */
	@Override
	public final int handle()
	{
		return this.handle;
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/04/04
	 */
	@Override
	public final void setImage(UiImage __i)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/04/04
	 */
	@Override
	public final void setLabel(String __s)
		throws NullPointerException
	{
		if (__s == null)
			throw new NullPointerException("NARG");
		
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/04/04
	 */
	@Override
	public final void setLongLabel(String __s)
	{
		throw new todo.TODO();
	}
}

