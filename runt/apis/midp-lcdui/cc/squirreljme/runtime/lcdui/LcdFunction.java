// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.lcdui;

/**
 * This represents a function for the LCDUI interface for system calls, all
 * service calls to the host interface server.
 *
 * @since 2018/03/16
 */
public enum LcdFunction
{
	/** Sets whether a given action is enabled. */
	ACTION_SET_ENABLED,
	
	/** Sets the font for the action. */
	ACTION_SET_FONT,
	
	/** Sets the labels and images for an action. */
	ACTION_SET_LABELS,
	
	/** A collectable was garbage collected, so clean it up. */
	COLLECTABLE_CLEANUP,
	
	/** Create a new collectable. */
	COLLECTABLE_CREATE,
	
	/** Vibrates the display. */
	DISPLAY_VIBRATE,
	
	/** Queries available displays and sets the local callback method. */
	QUERY_DISPLAYS,
	
	/** Gets the string from a ticker. */
	TICKER_GET_STRING,
	
	/** Sets the ticker to the given string. */
	TICKER_SET_STRING,
	
	/** Adds a widget to this widget. */
	WIDGET_ADD,
	
	/** Shows a widget which is an alert, the alert is considered modal. */
	WIDGET_ALERT_SHOW,
	
	/** Clears all widgets that are to be displayed then sets it. */
	WIDGET_CLEAR_AND_SET,
	
	/** Returns the widget height. */
	WIDGET_GET_HEIGHT,
	
	/** Gets the ticker of a widget. */
	WIDGET_GET_TICKER,
	
	/** Returns the widget width. */
	WIDGET_GET_WIDTH,
	
	/** Repaints a widget. */
	WIDGET_REPAINT,
	
	/** Sets the ticker of a widget. */
	WIDGET_SET_TICKER,
	
	/** Sets the title of a widget. */
	WIDGET_SET_TITLE,
	
	/** End. */
	;
	
	/**
	 * Is this function interruptable?
	 *
	 * @return If this function can be interrupted.
	 * @since 2018/03/18
	 */
	public final boolean isInterruptable()
	{
		switch (this)
		{
			default:
				return false;
		}
	}
	
	/**
	 * Returns {@code true} if this is a function which queries and returns a
	 * value.
	 *
	 * @return If this is to be queried and a value returned.
	 * @since 2018/03/18
	 */
	public final boolean query()
	{
		switch (this)
		{
			case COLLECTABLE_CREATE:
			case QUERY_DISPLAYS:
			case TICKER_GET_STRING:
			case WIDGET_ADD:
			case WIDGET_GET_HEIGHT:
			case WIDGET_GET_TICKER:
			case WIDGET_GET_WIDTH:
				return true;
			
			default:
				return false;
		}
	}
}

