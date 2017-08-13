// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.jit.java;

/**
 * This represents the location where a variable is stored.
 *
 * @since 2017/08/13
 */
public enum VariableLocation
{
	/** Local variables. */
	LOCAL,
	
	/** Stack variables. */
	STACK,
	
	/** The copied {@code this} reference, used for {@code synchronized}. */
	COPIED_THIS,
	
	/** The exception that is in the throwing state. */
	THROWING_EXCEPTION,
	
	/** End. */
	;
}

