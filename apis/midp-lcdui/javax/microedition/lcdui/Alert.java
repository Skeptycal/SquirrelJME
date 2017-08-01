// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package javax.microedition.lcdui;

import net.multiphasicapps.squirreljme.lcdui.DisplayableType;

public class Alert
	extends Screen
{
	/**
	 * This is delivered to a listener to specify that the alert has been
	 * dismissed.
	 */
	public static final Command DISMISS_COMMAND =
		new Command("", Command.OK, 0);
	
	/** Specifies that the alert should last forever. */
	public static final int FOREVER =
		-2;
	
	/** The title of the alert. */
	private final String _title;
	
	/** The message to display. */
	private volatile String _message;
	
	/** The icon to use. */
	private volatile Image _icon;
	
	/** The type of alert this is. */
	private volatile AlertType _type;
	
	/** The duration the alert should last in milliseconds. */
	private volatile int _timeout =
		FOREVER;
	
	/**
	 * Initializes the alert with just a title.
	 *
	 * @param __title The title of the alert, may be {@code null}.
	 * @since 2017/02/28
	 */
	public Alert(String __title)
	{
		this(__title, null, null, null);
	}
	
	/**
	 * Initializes the alert with just a title, message, image, and type.
	 *
	 * @param __title The title of the alert, may be {@code null}.
	 * @param __message The message to show in the alert, may be {@code null}.
	 * @param __icon The icon to display, may be {@code null}.
	 * @param __type The type of this alert, may be {@code null}.
	 * @since 2017/02/28
	 */
	public Alert(String __title, String __message, Image __icon,
		AlertType __type)
	{
		super(DisplayableType.ALERT);
		
		// Set
		this._title = __title;
		this._message = __message;
		this._icon = __icon;
		this._type = __type;
		
		throw new todo.TODO();
	}
	
	@Override
	public void addCommand(Command __a)
	{
		throw new todo.TODO();
	}
	
	public int getDefaultTimeout()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/05/24
	 */
	@Override
	public int getHeight()
	{
		return __getHeight();
	}
	
	public Image getImage()
	{
		throw new todo.TODO();
	}
	
	public Gauge getIndicator()
	{
		throw new todo.TODO();
	}
	
	public String getString()
	{
		throw new todo.TODO();
	}
	
	public int getTimeout()
	{
		throw new todo.TODO();
	}
	
	public AlertType getType()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2017/05/24
	 */
	@Override
	public int getWidth()
	{
		return __getWidth();
	}
	
	@Override
	public void removeCommand(Command __a)
	{
		throw new todo.TODO();
	}
	
	@Override
	public void setCommandListener(CommandListener __a)
	{
		throw new todo.TODO();
	}
	
	public void setImage(Image __a)
	{
		throw new todo.TODO();
	}
	
	public void setIndicator(Gauge __a)
	{
		throw new todo.TODO();
	}
	
	/**
	 * Sets the message which is used in the alert.
	 *
	 * @param __s The message to use.
	 * @since 2017/02/28
	 */
	public void setString(String __s)
	{
		this._message = __s;
		
		System.err.printf("DEBUG -- Set alert message: %s%n", __s);
	}
	
	/**
	 * Sets the duration for which the alert should last.
	 *
	 * @param __ms The number of milliseconds to display the message for or
	 * {@link #FOREVER}.
	 * @throws IllegalArgumentException If the duration is not positive and is
	 * not {@link #FOREVER}.
	 * @since 2017/02/28
	 */
	public void setTimeout(int __ms)
		throws IllegalArgumentException
	{
		// {@squirreljme.error EB0s The specified number of milliseconds is
		// negative. (The number of milliseconds specified)}
		if (__ms < 0 && __ms != FOREVER)
			throw new IllegalArgumentException(String.format("EB0s %d", __ms));
		
		// Set
		this._timeout = __ms;
	}
	
	/**
	 * Sets the type of this alert.
	 *
	 * @param __t The alert type, may be {@code null}.
	 * @since 2017/02/28
	 */
	public void setType(AlertType __t)
	{
		this._type = __t;
	}
}


