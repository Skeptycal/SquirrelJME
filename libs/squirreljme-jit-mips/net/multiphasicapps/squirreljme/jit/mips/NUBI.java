// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.jit.mips;

import net.multiphasicapps.squirreljme.jit.JITException;

/**
 * This class just contains public fields and helper methods for the NUBI ABI
 * and is given registers as needed.
 *
 * The ABI that this engine uses on MIPS machines is NUBI, documentation of it
 * is available here:
 * {@link ftp://ftp.linux-mips.org/pub/linux/mips/doc/NUBI/} in a file called
 * {@code MD00438-2C-NUBIDESC-SPC-00.20.pdf}.
 *
 * @since 2017/02/19
 */
public final class NUBI
{
	/**
	 * Not used.
	 *
	 * @since 2017/02/19
	 */
	private NUBI()
	{
	}
	
	/** The zero register. */
	public static final MIPSRegister ZERO =
		MIPSRegister.R0;
	
	/** PIC code function call/address temporary. */
	public static final MIPSRegister PF =
		MIPSRegister.R2;
	
	/** Global data pointer. */
	public static final MIPSRegister GP =
		MIPSRegister.R3;
	
	/** Argument 1 or return value 1. */
	public static final MIPSRegister A0 =
		MIPSRegister.R4;
	
	/** Argument 2 or return value 2. */
	public static final MIPSRegister A1 =
		MIPSRegister.R5;
	
	/** Argument 3 or return value 3. */
	public static final MIPSRegister A2 =
		MIPSRegister.R6;
	
	/** Argument 4 or return value 4. */
	public static final MIPSRegister A3 =
		MIPSRegister.R7;
	
	/** Argument 5. */
	public static final MIPSRegister A4 =
		MIPSRegister.R8;
	
	/** Argument 6. */
	public static final MIPSRegister A5 =
		MIPSRegister.R9;
	
	/** Argument 7. */
	public static final MIPSRegister A6 =
		MIPSRegister.R10;
	
	/** Argument 8. */
	public static final MIPSRegister A7 =
		MIPSRegister.R11;
	
	/** Saved register 1. */
	public static final MIPSRegister S0 =
		MIPSRegister.R12;

	/** Saved register 2. */
	public static final MIPSRegister S1 =
		MIPSRegister.R13;

	/** Saved register 3. */
	public static final MIPSRegister S2 =
		MIPSRegister.R14;

	/** Saved register 4. */
	public static final MIPSRegister S3 =
		MIPSRegister.R15;

	/** Saved register 5. */
	public static final MIPSRegister S4 =
		MIPSRegister.R16;

	/** Saved register 6. */
	public static final MIPSRegister S5 =
		MIPSRegister.R17;

	/** Saved register 7. */
	public static final MIPSRegister S6 =
		MIPSRegister.R18;

	/** Saved register 8. */
	public static final MIPSRegister S7 =
		MIPSRegister.R19;

	/** Saved register 9. */
	public static final MIPSRegister S8 =
		MIPSRegister.R20;

	/** Saved register 10. */
	public static final MIPSRegister S9 =
		MIPSRegister.R21;

	/** Saved register 11. */
	public static final MIPSRegister S10 =
		MIPSRegister.R22;

	/** Saved register 12. */
	public static final MIPSRegister S11 =
		MIPSRegister.R23;
	
	/** Temporary 1. */
	public static final MIPSRegister T1 =
		MIPSRegister.R24;
	
	/** Temporary 2. */
	public static final MIPSRegister T2 =
		MIPSRegister.R25;
	
	/** Temporary 3. */
	public static final MIPSRegister T3 =
		MIPSRegister.R1;
	
	/** Reserved for trap/interrupt handler 1. */
	public static final MIPSRegister K0 =
		MIPSRegister.R26;
	
	/** Reserved for trap/interrupt handler 2. */
	public static final MIPSRegister K1 =
		MIPSRegister.R27;
	
	/** Thread pointer. */
	public static final MIPSRegister TP =
		MIPSRegister.R28;
	
	/** Stack pointer. */
	public static final MIPSRegister SP =
		MIPSRegister.R29;
	
	/** Frame pointer. */
	public static final MIPSRegister FP =
		MIPSRegister.R30;
	
	/** Link for JAL, also return address. */
	public static final MIPSRegister RA =
		MIPSRegister.R31;
	
	/** Argument register 1 or return value 1. */
	public static final MIPSRegister FA0 =
		MIPSRegister.F12;

	/** Argument register 2 or return value 2. */
	public static final MIPSRegister FA1 =
		MIPSRegister.F13;

	/** Argument register 3 or return value 3. */
	public static final MIPSRegister FA2 =
		MIPSRegister.F14;

	/** Argument register 4 or return value 4. */
	public static final MIPSRegister FA3 =
		MIPSRegister.F15;

	/** Argument register 5 or return value 5. */
	public static final MIPSRegister FA4 =
		MIPSRegister.F16;

	/** Argument register 6 or return value 6. */
	public static final MIPSRegister FA5 =
		MIPSRegister.F17;

	/** Argument register 7 or return value 7. */
	public static final MIPSRegister FA6 =
		MIPSRegister.F18;

	/** Argument register 8 or return value 8. */
	public static final MIPSRegister FA7 =
		MIPSRegister.F19;
	
	/** Temporary float register 1. */
	public static final MIPSRegister FT0 =
		MIPSRegister.F1;
	
	/** Temporary float register 2. */
	public static final MIPSRegister FT1 =
		MIPSRegister.F3;

	/** Temporary float register 3. */
	public static final MIPSRegister FT2 =
		MIPSRegister.F4;

	/** Temporary float register 4. */
	public static final MIPSRegister FT3 =
		MIPSRegister.F5;

	/** Temporary float register 5. */
	public static final MIPSRegister FT4 =
		MIPSRegister.F6;

	/** Temporary float register 6. */
	public static final MIPSRegister FT5 =
		MIPSRegister.F7;

	/** Temporary float register 7. */
	public static final MIPSRegister FT6 =
		MIPSRegister.F8;

	/** Temporary float register 8. */
	public static final MIPSRegister FT7 =
		MIPSRegister.F9;

	/** Temporary float register 9. */
	public static final MIPSRegister FT8 =
		MIPSRegister.F10;

	/** Temporary float register 10. */
	public static final MIPSRegister FT9 =
		MIPSRegister.F11;
	
	/** Temporary float register 11. */
	public static final MIPSRegister FT10 =
		MIPSRegister.F0;
	
	/** Temporary float register 12. */
	public static final MIPSRegister FT11 =
		MIPSRegister.F2;
		
	/** Saved float register 1. */
	public static final MIPSRegister FS0 =
		MIPSRegister.F24;

	/** Saved float register 2. */
	public static final MIPSRegister FS1 =
		MIPSRegister.F25;

	/** Saved float register 3. */
	public static final MIPSRegister FS2 =
		MIPSRegister.F26;

	/** Saved float register 4. */
	public static final MIPSRegister FS3 =
		MIPSRegister.F27;

	/** Saved float register 5. */
	public static final MIPSRegister FS4 =
		MIPSRegister.F28;

	/** Saved float register 6. */
	public static final MIPSRegister FS5 =
		MIPSRegister.F29;

	/** Saved float register 7. */
	public static final MIPSRegister FS6 =
		MIPSRegister.F30;

	/** Saved float register 8. */
	public static final MIPSRegister FS7 =
		MIPSRegister.F31;
	
	/** Saved float register 9. */
	public static final MIPSRegister FS8 =
		MIPSRegister.F20;

	/** Saved float register 10. */
	public static final MIPSRegister FS9 =
		MIPSRegister.F21;

	/** Saved float register 11. */
	public static final MIPSRegister FS10 =
		MIPSRegister.F22;

	/** Saved float register 12. */
	public static final MIPSRegister FS11 =
		MIPSRegister.F23;
	
	/**
	 * Returns the argument register which follows this.
	 *
	 * @param __r The next register.
	 * @return The next register, or {@code null} if no next register remains.
	 * @throws JITException If the specified register is not an argument
	 * register.
	 * @since 2017/02/20
	 */
	public static MIPSRegister nextArgument(MIPSRegister __r)
		throws JITException
	{
		// Integer
		if (__r == NUBI.A1)
			return NUBI.A2;
		else if (__r == NUBI.A2)
			return NUBI.A3;
		else if (__r == NUBI.A3)
			return NUBI.A4;
		else if (__r == NUBI.A4)
			return NUBI.A5;
		else if (__r == NUBI.A5)
			return NUBI.A6;
		else if (__r == NUBI.A6)
			return NUBI.A7;
		else if (__r == NUBI.A7)
			return null;
		
		// Floating point
		else if (__r == NUBI.FA1)
			return NUBI.FA2;
		else if (__r == NUBI.FA2)
			return NUBI.FA3;
		else if (__r == NUBI.FA3)
			return NUBI.FA4;
		else if (__r == NUBI.FA4)
			return NUBI.FA5;
		else if (__r == NUBI.FA5)
			return NUBI.FA6;
		else if (__r == NUBI.FA6)
			return NUBI.FA7;
		else if (__r == NUBI.FA7)
			return null;
		
		// {@squirreljme.error AM04 The specified register is not an
		// argument register. (The specified register)}
		else
			throw new JITException(String.format("AM04 %s", __r));
	}
}
