#!/bin/sh
# ---------------------------------------------------------------------------
# Multi-Phasic Applications: SquirrelJME
#     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
#     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
# ---------------------------------------------------------------------------
# SquirrelJME is under the GNU General Public License v3, or later.
# See license.mkd for licensing and copyright information.
# ---------------------------------------------------------------------------
# DESCRIPTION: This shows the list of error prefixes.

# Force C locale
export LC_ALL=C

# Directory of this script
__exedir="$(dirname -- "$0")"

# Go through all projects
("$__exedir/lsprojects.sh" | sed 's/\/$//g' | while read __project
do
	__man="$__exedir/../$__project/META-INF/MANIFEST.MF"
	if [ -f "$__man" ]
	then
		__err="$(sed \
			'y/abcdefghijklmnopqrstuvwxyz/ABCDEFGHIJKLMNOPQRSTUVWXYZ/' \
			< "$__man" | grep '^X-SQUIRRELJME-ERROR[ \t]*:' |
			sed 's/^X-SQUIRRELJME-ERROR[ \t]*:[ \t]*\([^ \t]*\)[ \t]*/\1/')"
		if [ -n "$__err" ]
		then
			echo "$__err $__project"
		fi
	fi
done) | sort

