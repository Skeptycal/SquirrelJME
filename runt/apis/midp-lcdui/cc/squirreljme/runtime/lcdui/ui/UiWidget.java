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

/**
 * This represents the base for all widgets, widgets may be contained within
 * other widgets potentially and form a tree-like structure.
 *
 * @since 2018/04/04
 */
public interface UiWidget
	extends UiCollectable, UiInterface
{
	/**
	 * Returns the current height of the widget in pixels.
	 *
	 * @return The current height of the widget.
	 * @since 2018/04/06
	 */
	public abstract int getHeight();
	
	/**
	 * Returns the current width of the widget in pixels.
	 *
	 * @return The current width of the widget.
	 * @since 2018/04/06
	 */
	public abstract int getWidth();
}

