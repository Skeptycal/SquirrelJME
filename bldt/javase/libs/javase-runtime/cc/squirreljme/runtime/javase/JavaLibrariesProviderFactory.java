// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.javase;

import cc.squirreljme.kernel.impl.base.file.FileLibrariesProvider;
import cc.squirreljme.kernel.impl.base.file.FileLibrariesProviderFactory;
import cc.squirreljme.kernel.lib.server.LibrariesProvider;
import cc.squirreljme.kernel.lib.server.LibrariesProviderFactory;

/**
 * This allows the Java kernel to manage libraries as needed.
 *
 * @since 2018/01/05
 */
public class JavaLibrariesProviderFactory
	extends FileLibrariesProviderFactory
{
	/**
	 * {@inheritDoc}
	 * @since 2018/01/05
	 */
	@Override
	protected FileLibrariesProvider createFileLibraries()
	{
		return new JavaLibrariesProvider();
	}
}

