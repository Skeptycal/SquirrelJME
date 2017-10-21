// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.lcdui.font;

/**
 * This represents the family of a font and is used to provide a native
 * representation of a given family.
 *
 * @since 2017/10/20
 */
public abstract class FontFamily
{
	/** The name of the family. */
	protected final FontFamilyName name;
	
	/**
	 * Initializes the base font family.
	 *
	 * @param __n The name of the font.
	 * @throws NullPointerException On null arguments.
	 * @since 2017/10/21
	 */
	public FontFamily(FontFamilyName __n)
		throws NullPointerException
	{
		if (__n == null)
			throw new NullPointerException("NARG");
		
		this.name = __n;
	}
}

