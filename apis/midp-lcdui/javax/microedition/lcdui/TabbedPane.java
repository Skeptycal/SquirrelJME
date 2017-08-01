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

public class TabbedPane
	extends Screen
{
	public TabbedPane(String __title, boolean __stringtab, boolean __suptitle)
	{
		super(DisplayableType.TABBED_PANE);
		
		throw new todo.TODO();
	}
	
	public void addTab(Screen __t, Image __i)
	{
		throw new todo.TODO();
	}
	
	public void addTabListener(TabListener __tl)
	{
		throw new todo.TODO();
	}
	
	public int getCount()
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
	
	public Screen getScreen(int __i)
	{
		throw new todo.TODO();
	}
	
	public int getSelectedIndex()
	{
		throw new todo.TODO();
	}
	
	public Image getTabIcon(int __i)
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
	
	public void insertTab(int __i, Screen __t, Image __img)
	{
		throw new todo.TODO();
	}
	
	public void removeTab(int __i)
	{
		throw new todo.TODO();
	}
	
	public void setFocus(int __i)
	{
		throw new todo.TODO();
	}
	
	public void setTabIcon(int __i, Image __icon)
	{
		throw new todo.TODO();
	}
}

