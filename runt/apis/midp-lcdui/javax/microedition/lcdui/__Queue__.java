// -*- Mode: Java; indent-tabs-mode: t; tab-width: 4 -*-
// ---------------------------------------------------------------------------
// Multi-Phasic Applications: SquirrelJME
//     Copyright (C) Stephanie Gawroriski <xer@multiphasicapps.net>
//     Copyright (C) Multi-Phasic Applications <multiphasicapps.net>
// ---------------------------------------------------------------------------
// SquirrelJME is under the GNU General Public License v3+, or later.
// See license.mkd for licensing and copyright information.
// ---------------------------------------------------------------------------

package javax.microedition.lcdui;

import cc.squirreljme.runtime.cldc.system.SystemCall;
import cc.squirreljme.runtime.cldc.system.type.VoidType;
import cc.squirreljme.runtime.lcdui.DisplayableType;
import cc.squirreljme.runtime.lcdui.LcdFunction;
import cc.squirreljme.runtime.lcdui.LcdServiceCall;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a queue which manages which handles displayable which are bound to
 * the remote server, but when they are garbage collected allows them to be
 * cleaned up by the server accordingly.
 *
 * @since 2018/03/17
 */
final class __Queue__
	implements Runnable
{
	/** Single queue instance. */
	static final __Queue__ INSTANCE =
		new __Queue__();
		
	/** Internal queue access lock. */
	protected final Object lock =
		new Object();
	
	/** Reference to index mapping, for sending to the server. */
	protected final Map<Reference<__Widget__>, Integer> _distoid =
		new HashMap<>();
	
	/** Index to widget so the client knows what the server wanted. */
	protected final Map<Integer, Reference<__Widget__>> _idtodis =
		new HashMap<>();
	
	/** Queue to tell the remote server that handles should be cleaned up. */
	protected final ReferenceQueue<__Widget__> _disqueue =
		new ReferenceQueue<>();
	
	/** Terminate the queue? */
	private volatile boolean _terminate;
	
	/**
	 * Internally initialized.
	 *
	 * @since 2018/03/17
	 */
	private __Queue__()
	{
		Thread cleanuper = new Thread(this, "LCDUI-Cleanup-Thread");
		SystemCall.EASY.setDaemonThread(cleanuper);
		cleanuper.start();
	}
	
	/**
	 * {@inheritDoc}
	 * @since 2018/03/23
	 */
	@Override
	public void run()
	{
		Object lock = this.lock;
		ReferenceQueue<__Widget__> disqueue = this._disqueue;
		Map<Reference<__Widget__>, Integer> distoid = this._distoid;
		Map<Integer, Reference<__Widget__>> idtodis = this._idtodis;
		
		// Loop forever looking for displayable that are no longer
		// referenced ever
		for (;;)
		{
			// Terminate thread?
			if (this._terminate)
				return;
			
			// Get the next reference which went away
			Reference<? extends __Widget__> bye;
			try
			{
				bye = disqueue.remove();
			}
			
			// Ignore
			catch (InterruptedException e)
			{
				continue;
			}
			
			// Remove
			int svdx;
			synchronized (lock)
			{
				// Only cleanup valid references
				Integer dx = distoid.get(bye);
				if (dx == null)
					continue;
				
				// Remove from mappings
				distoid.remove(bye);
				idtodis.remove(dx);
				
				// The server is notified of this
				svdx = dx;
			}
			
			// If the server failed to clean it up properly then just ignore it
			try
			{
				LcdServiceCall.<VoidType>call(VoidType.class,
					LcdFunction.widget_CLEANUP, svdx);
			}
			catch (Throwable t)
			{
				t.printStackTrace();
			}
		}
	}
	
	/**
	 * Returns the displayable used for the given index.
	 *
	 * @param <X> The type of widget to get.
	 * @param __cl The type of widget to get.
	 * @param __dx The index to get.
	 * @return The widget for the given index.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/18
	 */
	final <X extends __Widget__> X __get(Class<X> __cl, int __dx)
		throws NullPointerException
	{
		if (__cl == null)
			throw new NullPointerException("NARG");
		
		Map<Integer, Reference<__Widget__>> idtodis = this._idtodis;
		synchronized (this.lock)
		{
			Reference<__Widget__> ref = idtodis.get(__dx);
			
			// Do not know what this is?
			if (ref == null)
				return;
			__Widget__ rv = ref.get();
			
			// If this is not the right kind of class, ignore
			if (!__cl.isInstance(rv))
				return null;
			return __cl.cast(rv);
		}
	}
	
	/**
	 * Registers the given widget and returns the remote handle to it.
	 *
	 * @param __d The widget to register.
	 * @return The handle of the widget on the remote end.
	 * @throws NullPointerException On null arguments.
	 * @since 2018/03/17
	 */
	final int __register(__Widget__ __d)
		throws NullPointerException
	{
		if (__d == null)
			throw new NullPointerException("NARG");
		
		// The remote index
		int dx;
		
		// Determine the type of displayable this is first
		WidgetType type;
		if (__d instanceof Canvas)
			type = WidgetType.CANVAS;
		else if (__d instanceof Alert)
			type = WidgetType.ALERT;
		else if (__d instanceof FileSelector)
			type = WidgetType.FILE_SELECTOR;
		else if (__d instanceof Form)
			type = WidgetType.FORM;
		else if (__d instanceof List)
			type = WidgetType.LIST;
		else if (__d instanceof TabbedPane)
			type = WidgetType.TABBED_PANE;
		else if (__d instanceof TextBox)
			type = WidgetType.TEXT_BOX;
		
		// {@squirreljme.error EB1x Could not determine the type displayable
		// that this is. (The displayable type)}
		else
			throw new RuntimeException(String.format(
				"EB1x %s", __d.getClass()));
		
		// Register and get the index for it
		dx = LcdServiceCall.<Integer>call(Integer.class,
			LcdFunction.CREATE_DISPLAYABLE, type);
		
		// Reference the widget for future cleanup on the remote end
		Map<Reference<__Widget__>, Integer> distoid = this._distoid;
		Map<Integer, Reference<__Widget__>> idtodis = this._idtodis;
		synchronized (this.lock)
		{
			Reference<__Widget__> ref =
				new WeakReference<>(__d, this._disqueue);
			Integer idx = dx;
			distoid.put(ref, idx);
			idtodis.put(idx, ref);
		}
		
		// The displayable uses this index to interact with the server
		return dx;
	}
}

