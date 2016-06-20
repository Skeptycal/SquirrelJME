// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) 2013-2016 Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) 2013-2016 Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU Affero General Public License v3+, or later.
// For more information see license.mkd.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirreljme.cldc;

/**
 * This class is used as the base for binary search operations within an
 * input array or collection. The input is required to be sorted, otherwise the
 * resulting value returned from the search is undefined.
 *
 * @since 2016/06/19
 */
public final class IndexedBinarySearch
{
	/**
	 * Not used.
	 *
	 * @since 2016/06/19
	 */
	private IndexedBinarySearch()
	{
	}
	
	/**
	 * Searches the given input array for the given element type (which may
	 * be of a different type) within the given range and using the specific
	 * comparator for values).
	 *
	 * @param <Q> The array or collection type.
	 * @param <E> The input element to be searched for.
	 * @param __q The input array or collection.
	 * @param __match The value to compare against.
	 * @param __from The starting index.
	 * @param __to The ending index.
	 * @param __comp The comparator to use when searching.
	 * @return A zero or positive value indicating the index where the entry
	 * was found, 
	 * @throws IllegalArgumentException If the from and/or to index are
	 * negative, or the to index is before the from index.
	 * @throws NullPointerException If no comparator was specified.
	 * @since 2016/06/19
	 */
	public static <Q, E> int search(Q __q, E __match, int __from, int __to,
		IndexedBinaryComparator<Q, E> __comp)
		throws IllegalArgumentException, NullPointerException
	{
		// Check
		if (__comp == null)
			throw new NullPointerException("NARG");
		
		// {@squirreljme.error ZZ0s The from or to index to sort is either
		// negative or the to index is before the from index.}
		if (__from < 0 || __to < 0 || __to < __from)
			throw new IllegalArgumentException("ZZ0s");
		
		// Nothing to search? Insert at the first location
		int n = __to - __from;
		if (n == 0)
			return -(__from) - 1;
		
		// Compare against the highest value to see if it is off the end
		int comp = __comp.binaryCompare(__q, __match, __to - 1);
		if (comp > 0)
			return -(__to) - 1;
		else if (comp == 0)
			return __to - 1;
		
		// And then the lowest
		comp = __comp.binaryCompare(__q, __match, __from);
		if (comp < 0)
			return -(__from) - 1;
		else if (comp == 0 || n == 1)
			return __from;
		
		// Search
		for (int ls = __from, pv = ls + (n >>> 1), rs = __to;;)
		{
			// Compare against the pivot
			comp = __comp.binaryCompare(__q, __match, pv);
			System.err.printf("DEBUG -- BS [%3d-%3d-%3d] %s ? %d = %d%n",
				ls, pv, rs, __match, pv, comp);
			// Matched?
			if (comp == 0)
				return pv;
			
			// Nothing left to search
			if ((rs - ls) <= 1)
				return -(ls) - 1;
			
			// Greater? Go to the right side
			if (comp > 0)
			{
				ls = pv;
				pv = ls + ((rs - ls) >>> 1);
			}
			
			// Otherwise the left side
			else
			{
				rs = pv;
				pv = ls + ((rs - ls) >>> 1);
			}
		}
	}
}

