// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.lcdui.server;

import cc.squirreljme.runtime.lcdui.CollectableType;
import cc.squirreljme.runtime.lcdui.LcdServiceCall;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the base of the graphical LCDUI display system which
 * is used by the public facing LCDUI code to enable the use of graphics.
 *
 * All operations in the server are performed inside of a single lock because
 * LCDUI for the most part requires that behavior, additionally most operating
 * systems and display interfaces that exist only allow interaction with
 * graphical APIs in the same thread that is using them. Although this has a
 * speed loss, if most graphical operations are performed on buffers this will
 * not cause much issue.
 *
 * @since 2018/03/15
 */
public abstract class LcdDefinition
{
	/** The displays that are available for usage. */
	protected final LcdDisplays displays;
	
	/**
	 * Initializes the base definition.
	 *
	 * @param __ld The display manager which is used to manager displays and
	 * provide access to events for execution.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/15
	 */
	public LcdDefinition(LcdDisplays __ld)
		throws NullPointerException
	{
		throw new todo.TODO();
		/*
		super(LcdServiceCall.Provider.class);
		
		if (__ld == null)
			throw new NullPointerException("NARG");
		
		this.displays = __ld;
		*/
	}
}

