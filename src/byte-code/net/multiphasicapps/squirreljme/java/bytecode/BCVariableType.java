// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU Affero General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.java.bytecode;

import net.multiphasicapps.squirreljme.java.symbols.FieldSymbol;

/**
 * This represents the type of value which is stored in a variable.
 *
 * @since 2016/05/12
 */
public enum BCVariableType
{
	/** 32-bit Integer. */
	INTEGER(false),
	
	/** 64-bit Integer. */
	LONG(true),
	
	/** 32-bit Float. */
	FLOAT(false),
	
	/** 64-bit Double. */
	DOUBLE(true),
	
	/** Object. */
	OBJECT(false),
	
	/** The top of a long or double. */
	TOP(false),
	
	/** End. */
	;
	
	/** Is this type wide? */
	protected final boolean iswide;
	
	/**
	 * Initializes the variable type.
	 *
	 * @param __w If {@code true} then the type consumes two spaces.
	 * @since 2016/05/12
	 */
	private BCVariableType(boolean __w)
	{
		iswide = __w;
	}
	
	/**
	 * Returns {@code true} if this is a wide type.
	 *
	 * @return {@code true} if a wide type.
	 * @since 2016/05/12
	 */
	public final boolean isWide()
	{
		return iswide;
	}
	
	/**
	 * Obtains the type of variable to use by its symbol.
	 *
	 * @param __sym The symbol to use for the variable.
	 * @return The variable which is associated with the given symbol.
	 * @throws BCException If the type is not known.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/03/23
	 */
	public static BCVariableType bySymbol(FieldSymbol __sym)
		throws BCException, NullPointerException
	{
		// Check
		if (__sym == null)
			throw new NullPointerException("NARG");
		
		// If an array then it is always an object
		if (__sym.isArray())
			return OBJECT;
		
		// Depends on the first character otherwise
		switch (__sym.charAt(0))
		{
			case 'L': return OBJECT;
			case 'D': return DOUBLE;
			case 'F': return FLOAT;
			case 'J': return LONG;
				
				// All of these map to integer (promotion)
			case 'B':
			case 'C':
			case 'I':
			case 'S':
			case 'Z':
				return INTEGER;
				
				// Unknown
			default:
				// {@squirreljme.error AX09 The specified field symbol
				// cannot be mapped to a variable type. (The field symbol)}
				throw new BCException(String.format("AX09 %s", __sym));
		}
	}
}

