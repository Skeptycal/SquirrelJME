// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.builder.linux.powerpc;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import net.multiphasicapps.squirreljme.builder.BuildConfig;
import net.multiphasicapps.squirreljme.builder.BuildInstance;
import net.multiphasicapps.squirreljme.builder.linux.LinuxBuilder;
import net.multiphasicapps.squirreljme.builder.TargetBuilder;
import net.multiphasicapps.squirreljme.builder.TargetEmulator;
import net.multiphasicapps.squirreljme.builder.TargetEmulatorArguments;
import net.multiphasicapps.squirreljme.builder.TargetNotSupportedException;
import net.multiphasicapps.squirreljme.exe.elf.ELFOutput;
import net.multiphasicapps.squirreljme.exe.elf.ELFProgram;
import net.multiphasicapps.squirreljme.java.symbols.ClassNameSymbol;
import net.multiphasicapps.squirreljme.jit.base.JITCPUEndian;
import net.multiphasicapps.squirreljme.jit.base.JITException;
import net.multiphasicapps.squirreljme.jit.base.JITTriplet;
import net.multiphasicapps.squirreljme.jit.generic.GenericABI;
import net.multiphasicapps.squirreljme.jit.JITClassNameRewrite;
import net.multiphasicapps.squirreljme.jit.JITOutputConfig;
import net.multiphasicapps.zip.blockreader.ZipFile;
import net.multiphasicapps.zip.streamwriter.ZipStreamWriter;
import net.multiphasicapps.zip.ZipCompressionType;

/**
 * This is the builder for PowerPC based Linux systems.
 *
 * @since 2016/09/02
 */
public class LinuxPowerPCBuilder
	extends LinuxBuilder
{
	/**
	 * Initializes the Linux PowerPC target builder.
	 *
	 * @since 2016/09/02
	 */
	public LinuxPowerPCBuilder()
	{
		super(
			"powerpc-32+g1,big~soft.linux.sysv",
				"Generic Big Endian 32-bit Linux PowerPC (G1, Software Float)",
			"powerpc-32+g3,big~hard64.linux.sysv",
				"Generic Big Endian 32-bit Linux PowerPC (G3, Hardware Float)",
			"powerpc-64+g5,little~hard.linux.poweropen",
				"Generic Little Endian 64-bit Linux PowerPC (Hardware Float)",
			"powerpc-64+g5,big~hard.linux.poweropen",
				"Generic Big Endian 64-bit Linux PowerPC (Hardware Float)");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/02
	 */
	@Override
	public BuildInstance createBuildInstance(BuildConfig __conf) 
		throws TargetNotSupportedException
	{
		return new LinuxPowerPCBuildInstance(__conf);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/02
	 */
	@Override
	protected void dependentELF(BuildConfig __conf, ELFOutput __eo)
		throws IOException, NullPointerException
	{
		// Check
		if (__conf == null || __eo == null)
			throw new NullPointerException("NARG");
		
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/02
	 */
	@Override
	public TargetEmulator emulate(TargetEmulatorArguments __args)
		throws IllegalArgumentException, NullPointerException
	{
		throw new Error("TODO");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/02
	 */
	@Override
	public void outputConfig(JITOutputConfig __conf, BuildConfig __bc)
		throws NullPointerException
	{
		// Check
		if (__conf == null || __bc == null)
			throw new NullPointerException("NARG");
		
		// Rewrite calls
		__conf.addStaticCallRewrite(new JITClassNameRewrite(
			ClassNameSymbol.of(
				"net/multiphasicapps/squirreljme/unsafe/SquirrelJME"),
			ClassNameSymbol.of(
				"net/multiphasicapps/squirreljme/os/linux/powerpc/" +
				"SquirrelJME")));
		
		// Which ABI to use?
		GenericABI abi;
		JITTriplet triplet = __bc.triplet();
		String osvar;
		switch ((osvar = triplet.operatingSystemVariant()))
		{
				// SysV
			case "sysv":
				if (true)
					throw new Error("TODO");
				break;
				
				// EABI
			case "eabi":
				if (true)
					throw new Error("TODO");
				break;
				
				// PowerOpen
			case "poweropen":
				if (true)
					throw new Error("TODO");
				break;
			
				// {@squirreljme.error BT01 Do not know how to build for the
				// given operating system variant. (The operating system
				// variant)}
			default:
				throw new JITException(String.format("BU01 %s", osvar));
		}
		
		// Use the given ABI
		__conf.<GenericABI>registerObject(GenericABI.class, abi);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/02
	 */
	@Override
	public boolean supportsConfig(BuildConfig __conf)
		throws NullPointerException
	{
		// Check
		if (__conf == null)
			throw new NullPointerException("NARG");
		
		// Any Linux PowerPC system
		JITTriplet triplet = __conf.triplet();
		return triplet.architecture().equals("powerpc") &&
			triplet.operatingSystem().equals("linux");
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2016/09/02
	 */
	@Override
	public String targetPackageGroup(BuildConfig __conf)
		throws NullPointerException
	{
		// Check
		if (__conf == null)
			throw new NullPointerException("NARG");
		
		// Linux something
		return "linux-powerpc-" + __conf.triplet().operatingSystemVariant();
	}
}

