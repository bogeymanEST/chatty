/*
 * An easily extendable chat bot for any chat service.
 * Copyright (C) 2013 bogeymanEST
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package org.superfuntime.chatty.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Bogeyman
 * Date: 11.10.13
 * Time: 19:03
 */
public class EventManager {
    private static Logger logger = LogManager.getLogger("EventMgr");
    private static List<EventListenerInfo> LISTENERS = new ArrayList<EventListenerInfo>();

    /**
     * Registers the given object as an event listener.
     * <p/>
     * Every method that is a listener method in this class must have
     * the {@link Listener} annotation and exactly one parameter with type {@link BaseEvent} or a subclass of it. When an event
     * that is assignable to the type of the parameter is called, then the method is invoked with the event as the argument.
     *
     * @param listener The event listener object
     */
    public static void addListener(Object listener) {
        logger.trace("Registered {} as event listener", listener.getClass().getName());
        EventListenerInfo info = new EventListenerInfo(listener);
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Listener.class)) continue;
            Class<?>[] params = method.getParameterTypes();
            if (params.length != 1)
                continue;
            Class<?> param = params[0];
            if (!BaseEvent.class.isAssignableFrom(param)) continue;
            info.getListeners().add(new EventListenerMethod(method, param.asSubclass(BaseEvent.class)));
        }
        LISTENERS.add(info);
    }

    /**
     * Calls an event.
     * <p/>
     * All event listener methods, whose argument is of a type the given event can be assigned from, are invoked.
     *
     * @param event The event
     */
    public static void call(BaseEvent event) {
        logger.trace("Called event {}", event.getClass().getSimpleName());
        for (EventListenerInfo listener : LISTENERS) {
            for (EventListenerMethod method : listener.getListeners()) {
                if (event.getClass().isAssignableFrom(method.getEvent())) {
                    try {
                        method.getMethod().setAccessible(true);
                        method.getMethod().invoke(listener.getObject(), event);
                        logger.trace("Invoked listener method {}", method.getMethod());
                    } catch (IllegalAccessException e) {
                        logger.error("Failed to invoke listener method: {}", method.getMethod());
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        logger.error("Failed to invoke listener method: {}", method.getMethod());
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
