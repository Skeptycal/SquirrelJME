// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.builder.linux;

import net.multiphasicapps.squirreljme.builder.BuildConfig;
import net.multiphasicapps.squirreljme.builder.BuildInstance;
import net.multiphasicapps.squirreljme.builder.TargetNotSupportedException;
import net.multiphasicapps.squirreljme.jit.base.JITCPUEndian;
import net.multiphasicapps.squirreljme.jit.base.JITTriplet;

/**
 * This is the base build instance for all Linux based targets.
 *
 * @since 2016/09/02
 */
public abstract class LinuxBuildInstance
	extends BuildInstance
{
	/**
	 * Initializes the build instance.
	 *
	 * @param __conf The build configuration.
	 * @since 2016/09/02
	 */
	public LinuxBuildInstance(BuildConfig __conf)
	{
		super(__conf);
		
		// {@squirreljme.error BU03 Only Linux is supported by this build
		// instance.}
		JITTriplet triplet = __conf.triplet();
		if (!triplet.operatingSystem().equals("linux"))
			throw new TargetNotSupportedException("BU03");
		
		// {@squirreljme.error BU06 Only 32-bit and 64-bit Linux targets
		// are supported.}
		int bits = triplet.bits();
		if (bits != 32 && bits != 64)
			throw new TargetNotSupportedException("BU06");
		
		// {@squirreljme.error BU07 Only big endian or little endian Linux
		// targets are supported.}
		JITCPUEndian end = triplet.endianess();
		if (end != JITCPUEndian.BIG && end != JITCPUEndian.LITTLE)
			throw new TargetNotSupportedException("BU07");
		
		throw new Error("TODO");
	}
}

