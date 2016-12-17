// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.build.interpreter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import net.multiphasicapps.squirreljme.build.projects.InvalidProjectException;
import net.multiphasicapps.squirreljme.build.projects.NamespaceType;
import net.multiphasicapps.squirreljme.build.projects.Project;
import net.multiphasicapps.squirreljme.build.projects.ProjectName;
import net.multiphasicapps.squirreljme.build.projects.ProjectManager;
import net.multiphasicapps.squirreljme.kernel.KernelLaunchParameters;
import net.multiphasicapps.squirreljme.kernel.SystemInstalledSuites;

/**
 * This class manages and determines which suites are used and auto-installed
 * in the system class path.
 *
 * Note that the suites to use are selected by the kernel launch parameters and
 * as such any system suites by their project name which are not specified
 * will not appear in the class path apart from the built-in defaults.
 *
 * @since 2016/12/17
 */
public class InterpreterSystemSuites
	extends SystemInstalledSuites
{
	/**
	 * {@squirreljme.property
	 * net.multiphasicapps.squirreljme.interpreter.select=pkg,...
	 * This selects the API project which are to be used to select which
	 * APIs are available for the interpreter to provide.}
	 */
	public static final String SELECT_PROPERTY =
		"net.multiphasicapps.squirreljme.interpreter.select";
	
	/**
	 * Initializes the system suite manager.
	 *
	 * @param __ai The interpreter which runs the system.
	 * @param __klp The launch parameters for the kernel.
	 * @throws NullPointerException On null arguments.
	 * @since 2016/12/17
	 */
	public InterpreterSystemSuites(AutoInterpreter __ai,
		KernelLaunchParameters __klp)
		throws NullPointerException
	{
		// Check
		if (__ai == null || __klp == null)
			throw new NullPointerException("NARG");
		
		// Parse properites 
		Set<ProjectName> load = new LinkedHashSet<>();
		{
			// Always force cldc-compact to be specified
			load.add(new ProjectName("cldc-compact"));
			
			// Parse projects to add
			String prop = Objects.toString(
				__klp.getSystemProperty(SELECT_PROPERTY), "");
			int n = prop.length();
			for (int i = 0; i < n;)
			{
				// Find next comma
				int nc = prop.indexOf(',', i);
				if (nc < 0)
					nc = n;
				
				// Split off and add
				try
				{
					load.add(new ProjectName(prop.substring(i, nc).trim()));
				}
				
				// Ignore
				catch (InvalidProjectException e)
				{
				}
				
				// Set next
				i = nc + 1;
			}
		}
		
		// Debug
		System.err.printf("DEBUG -- %s%n", load);
		
		// Go through system projects 
		for (Project p : __ai.projectManager())
		{
			// Only accept APIs
			if (p.type() != NamespaceType.API)
				continue;
			
			throw new Error("TODO");
		}
		
		
		throw new Error("TODO");
	}
}

