// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package cc.squirreljme.runtime.cldc.system;

import cc.squirreljme.runtime.cldc.debug.CallTraceElement;
import cc.squirreljme.runtime.cldc.system.api.Call;
import cc.squirreljme.runtime.cldc.system.api.ClassEnumConstants;
import cc.squirreljme.runtime.cldc.system.api.ClassEnumCount;
import cc.squirreljme.runtime.cldc.system.api.ClassEnumIndexOf;
import cc.squirreljme.runtime.cldc.system.api.ClassEnumValueOf;
import cc.squirreljme.runtime.cldc.system.api.CurrentTimeMillisCall;
import cc.squirreljme.runtime.cldc.system.api.ExitCall;
import cc.squirreljme.runtime.cldc.system.api.GarbageCollectionHintCall;
import cc.squirreljme.runtime.cldc.system.api.InitializedCall;
import cc.squirreljme.runtime.cldc.system.api.InvokeStaticMainCall;
import cc.squirreljme.runtime.cldc.system.api.MemoryMapBooleanArrayCall;
import cc.squirreljme.runtime.cldc.system.api.MemoryMapByteArrayCall;
import cc.squirreljme.runtime.cldc.system.api.MemoryMapCharacterArrayCall;
import cc.squirreljme.runtime.cldc.system.api.MemoryMapDoubleArrayCall;
import cc.squirreljme.runtime.cldc.system.api.MemoryMapFloatArrayCall;
import cc.squirreljme.runtime.cldc.system.api.MemoryMapIntegerArrayCall;
import cc.squirreljme.runtime.cldc.system.api.MemoryMapLongArrayCall;
import cc.squirreljme.runtime.cldc.system.api.MemoryMapShortArrayCall;
import cc.squirreljme.runtime.cldc.system.api.MemoryMapStringArrayCall;
import cc.squirreljme.runtime.cldc.system.api.NanoTimeCall;
import cc.squirreljme.runtime.cldc.system.api.PipeOutputCall;
import cc.squirreljme.runtime.cldc.system.api.ServiceCallCall;
import cc.squirreljme.runtime.cldc.system.api.ServiceCountCall;
import cc.squirreljme.runtime.cldc.system.api.ServiceQueryClassCall;
import cc.squirreljme.runtime.cldc.system.api.ServiceQueryIndexCall;
import cc.squirreljme.runtime.cldc.system.api.SetDaemonThreadCall;
import cc.squirreljme.runtime.cldc.system.api.TaskListCall;
import cc.squirreljme.runtime.cldc.system.api.ThrowableGetStackCall;
import cc.squirreljme.runtime.cldc.system.api.ThrowableSetStackCall;
import cc.squirreljme.runtime.cldc.system.SystemFunction;
import cc.squirreljme.runtime.cldc.system.type.BooleanArray;
import cc.squirreljme.runtime.cldc.system.type.ByteArray;
import cc.squirreljme.runtime.cldc.system.type.CharacterArray;
import cc.squirreljme.runtime.cldc.system.type.ClassType;
import cc.squirreljme.runtime.cldc.system.type.DoubleArray;
import cc.squirreljme.runtime.cldc.system.type.EnumType;
import cc.squirreljme.runtime.cldc.system.type.FloatArray;
import cc.squirreljme.runtime.cldc.system.type.IntegerArray;
import cc.squirreljme.runtime.cldc.system.type.LongArray;
import cc.squirreljme.runtime.cldc.system.type.ShortArray;
import cc.squirreljme.runtime.cldc.system.type.StringArray;
import cc.squirreljme.runtime.cldc.system.type.VoidType;

/**
 * This class is provided so that calls made into the remote system can be
 * easily performed without needing to perform complex function and argument
 * passing.
 *
 * @since 2018/03/01
 */
public final class EasyCall
	implements ClassEnumConstants,
		ClassEnumCount,
		ClassEnumIndexOf,
		ClassEnumValueOf,
		CurrentTimeMillisCall,
		ExitCall,
		GarbageCollectionHintCall,
		InitializedCall,
		InvokeStaticMainCall,
		MemoryMapBooleanArrayCall,
		MemoryMapByteArrayCall,
		MemoryMapCharacterArrayCall,
		MemoryMapDoubleArrayCall,
		MemoryMapFloatArrayCall,
		MemoryMapIntegerArrayCall,
		MemoryMapLongArrayCall,
		MemoryMapShortArrayCall,
		MemoryMapStringArrayCall,
		NanoTimeCall,
		PipeOutputCall,
		ServiceCallCall,
		ServiceCountCall,
		ServiceQueryClassCall,
		ServiceQueryIndexCall,
		SetDaemonThreadCall,
		TaskListCall,
		ThrowableGetStackCall,
		ThrowableSetStackCall
{
	/**
	 * Initializes internally only.
	 *
	 * @since 2018/03/14
	 */
	EasyCall()
	{
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/17
	 */
	@Override
	@SuppressWarnings({"unchecked"})
	public final <E extends Enum<E>> E[] classEnumConstants(Class<E> __cl)
		throws NullPointerException
	{
		if (__cl == null)
			throw new NullPointerException("NARG");
		
		return (E[])SystemCall.<Object[]>systemCall(Object[].class,
			SystemFunction.CLASS_ENUM_CONSTANTS, __cl);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/21
	 */
	@Override
	public final int classEnumCount(Class<? extends Enum> __cl)
		throws IllegalArgumentException, NullPointerException
	{
		if (__cl == null)
			throw new NullPointerException("NARG");
		
		return SystemCall.<Integer>systemCall(Integer.class,
			SystemFunction.CLASS_ENUM_COUNT, __cl);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/21
	 */
	@Override
	public final <E extends Enum<E>> E classEnumIndexOf(Class<E> __cl, int __i)
		throws IllegalArgumentException, NullPointerException
	{
		if (__cl == null)
			throw new NullPointerException("NARG");
		
		return SystemCall.<E>systemCall(__cl,
			SystemFunction.CLASS_ENUM_INDEXOF, __cl, __i);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/17
	 */
	@Override
	public final <E extends Enum<E>> E classEnumValueOf(Class<E> __cl,
		String __n)
		throws IllegalArgumentException, NullPointerException
	{
		if (__cl == null || __n == null)
			throw new NullPointerException("NARG");
		
		return SystemCall.<E>systemCall(__cl,
			SystemFunction.CLASS_ENUM_VALUEOF, __cl, __n);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final long currentTimeMillis()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final void exit(int __e)
		throws SecurityException
	{
		throw new todo.TODO();
	}
		
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final void garbageCollectionHint()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final void initialized()
	{
		SystemCall.<VoidType>systemCall(VoidType.class,
			SystemFunction.INITIALIZED);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final void invokeStaticMainCall(ClassType __c, String... __args)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final BooleanArray memoryMapBooleanArray(long __a, int __l)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final ByteArray memoryMapByteArray(long __a, int __l)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final CharacterArray memoryMapCharacterArray(long __a, int __l)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final DoubleArray memoryMapDoubleArray(long __a, int __l)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final FloatArray memoryMapFloatArray(long __a, int __l)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final IntegerArray memoryMapIntegerArray(long __a, int __l)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final LongArray memoryMapLongArray(long __a, int __l)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final ShortArray memoryMapShortArray(long __a, int __l)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final StringArray memoryMapStringArray(long __a, int __l)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final long nanoTime()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final void pipeOutput(boolean __err, int __b)
	{
		throw new todo.TODO();
	}

	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final void pipeOutput(boolean __err, ByteArray __b, int __o,
		int __l)
		throws IndexOutOfBoundsException, NullPointerException
	{
		throw new todo.TODO();
	}
		
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final Object serviceCall(int __dx, EnumType __func,
		Object... __args)
	{
		throw new todo.TODO();
	}
		
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final int serviceCount()
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final ClassType serviceQueryClass(int __dx)
	{
		return SystemCall.<ClassType>systemCall(ClassType.class,
			SystemFunction.SERVICE_QUERY_CLASS, __dx);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final int serviceQueryIndex(ClassType __cl)
	{
		return SystemCall.<Integer>systemCall(Integer.class,
			SystemFunction.SERVICE_QUERY_INDEX, __cl);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final void setDaemonThread(Thread __t)
		throws IllegalThreadStateException, NullPointerException
	{
		SystemCall.<VoidType>systemCall(VoidType.class,
			SystemFunction.SET_DAEMON_THREAD, __t);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final IntegerArray taskList(boolean __incsys)
	{
		throw new todo.TODO();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final CallTraceElement[] throwableGetStack(Throwable __t)
		throws NullPointerException
	{
		return SystemCall.<CallTraceElement[]>systemCall(
			CallTraceElement[].class,
			SystemFunction.THROWABLE_GET_STACK, __t);
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/14
	 */
	@Override
	public final void throwableSetStack(Throwable __t,
		CallTraceElement[] __e)
		throws NullPointerException
	{
		SystemCall.<VoidType[]>systemCall(VoidType[].class,
			SystemFunction.THROWABLE_SET_STACK, __t, __e);
	}
}

