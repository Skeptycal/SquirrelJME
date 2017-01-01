// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Steven Gawroriski <steven@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package net.multiphasicapps.squirrelscavenger.game.chunk;

/**
 * This is a class which just contains functions that work on positions.
 *
 * @since 2016/10/09
 */
public class PositionFunctions
{
	/** The number of bits to use for the breadth. */
	public static final int WORLD_CHUNK_BREADTH_BITS =
		13;
	
	/** The number of bits to use for the height. */
	public static final int WORLD_CHUNK_HEIGHT_BITS =
		6;
	
	/** The number of chunks for the breadth of the world. */
	public static final int WORLD_CHUNK_BREADTH =
		1 << WORLD_CHUNK_BREADTH_BITS;
	
	/** The number of chunks in the height of the world. */
	public static final int WORLD_CHUNK_HEIGHT =
		1 << WORLD_CHUNK_HEIGHT_BITS;
	
	/** The mask used for the chunk breadth. */
	public static final int WORLD_CHUNK_BREADTH_MASK =
		WORLD_CHUNK_BREADTH - 1;
	
	/** The mask used for the chunk height. */
	public static final int WORLD_CHUNK_HEIGHT_MASK =
		WORLD_CHUNK_HEIGHT - 1;
	
	/** The number of bits to use for blocks. */
	public static final int BLOCK_BITS =
		3;
	
	/** The scale of a block. */
	public static final int BLOCK_SCALE =
		1 << BLOCK_BITS;
	
	/** The mask used for blocks. */
	public static final int BLOCK_VALUE_MASK =
		BLOCK_SCALE - 1;
	
	/** The number of bits to use for the sub-position. */
	public static final int SUB_BITS =
		32 - WORLD_CHUNK_BREADTH_BITS - BLOCK_BITS;
	
	/** The scale for sub-values. */
	public static final int SUB_SCALE =
		1 << SUB_BITS;
	
	/** The breadth of the world in blocks. */
	public static final int WORLD_BLOCK_BREADTH =
		WORLD_CHUNK_BREADTH * BLOCK_SCALE;
	
	/** The height of the world in blocks. */
	public static final int WORLD_BLOCK_HEIGHT =
		WORLD_CHUNK_HEIGHT * BLOCK_SCALE;
	
	/** The shift for Z values. */
	public static final int WORLD_CHUNK_X_SHIFT =
		WORLD_CHUNK_BREADTH_BITS + WORLD_CHUNK_HEIGHT_BITS;
	
	/** The shift for Y values. */
	public static final int WORLD_CHUNK_Y_SHIFT =
		WORLD_CHUNK_HEIGHT_BITS;
	
	/** The shift for the entity to get the chunk. */
	public static final int ENTITY_CHUNK_SHIFT =
		SUB_BITS + BLOCK_BITS;
	
	/** The shift for the entity to get the block. */
	public static final int ENTITY_BLOCK_SHIFT =
		SUB_BITS;
	
	/** The block index shift for X positions. */
	public static final int BLOCK_INDEX_X_SHIFT =
		BLOCK_BITS + BLOCK_BITS;
	
	/** The block index shift for Y positions. */
	public static final int BLOCK_INDEX_Y_SHIFT =
		BLOCK_BITS;
	
	/**
	 * Returns the block index for the given block position.
	 *
	 * @param __x The X position.
	 * @param __y The Y position.
	 * @param __z The Z position.
	 * @return The block index for the given position.
	 * @since 2016/10/09
	 */
	public static int blockPositionToBlockIndex(int __x, int __y, int __z)
	{
		return ((__x & BLOCK_VALUE_MASK) << BLOCK_INDEX_X_SHIFT) |
			((__y & BLOCK_VALUE_MASK) << BLOCK_INDEX_Y_SHIFT) |
			(__z & BLOCK_VALUE_MASK);
	}
	
	/**
	 * This caps the Z position of a chunk.
	 *
	 * @param __z The Z position to cap.
	 * @return The capped value.
	 * @since 2016/10/09
	 */
	public static int capChunkPositionZ(int __z)
	{
		if (__z < 0)
			return 0;
		else if (__z > WORLD_CHUNK_HEIGHT_MASK)
			return WORLD_CHUNK_HEIGHT_MASK;
		return __z;
	}
	
	/**
	 * This converts a chunk position to a chunk index position.
	 *
	 * @param __x The X position.
	 * @param __y The Y position.
	 * @param __z The Z position.
	 * @return The chunk index for the given position.
	 * @since 2016/10/09
	 */
	public static int chunkPositionToChunkIndex(int __x, int __y, int __z)
	{
		return ((__x & WORLD_CHUNK_BREADTH_MASK) << WORLD_CHUNK_X_SHIFT) |
			((__y & WORLD_CHUNK_BREADTH_MASK) << WORLD_CHUNK_Y_SHIFT) |
			capChunkPositionZ(__z);
	}
	
	/**
	 * This converts an entity position to a chunk index position.
	 *
	 * @param __x The X position.
	 * @param __y The Y position.
	 * @param __z The Z position.
	 * @return The chunk index for the given position.
	 * @since 2016/10/09
	 */
	public static int entityPositionToChunkIndex(int __x, int __y, int __z)
	{
		return chunkPositionToChunkIndex(__x >>> ENTITY_CHUNK_SHIFT,
			__y >>> ENTITY_CHUNK_SHIFT,
			__z >> ENTITY_CHUNK_SHIFT);
	}
	
	/**
	 * Returns the block index for the given entity position.
	 *
	 * @param __x The X position.
	 * @param __y The Y position.
	 * @param __z The Z position.
	 * @return The block index for the given position.
	 * @since 2016/10/09
	 */
	public static int entityPositionToBlockIndex(int __x, int __y, int __z)
	{
		return blockPositionToBlockIndex(__x >>> ENTITY_BLOCK_SHIFT,
			__y >>> ENTITY_BLOCK_SHIFT, __z >>> ENTITY_BLOCK_SHIFT);
	}
}

