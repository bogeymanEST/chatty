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

package org.superfuntime.chatty.chat;

/**
 * User: Bogeyman
 * Date: 2.10.13
 * Time: 19:02
 */
public abstract class Chat {

    /**
     * Sends a message to this chat.
     *
     * @param message The message to send
     */
    public abstract void send(String message);

    /**
     * Send multiple lines. Default implementation just sends multiple messages.
     *
     * @param lines The lines of chat to send
     */
    public void send(String... lines) {
        for (String line : lines) {
            send(line);
        }
    }

    /**
     * Gets a human-friendly name of this chat.
     *
     * @return A human-friendly name of this chat.
     */
    public abstract String getName();

    /**
     * Gets the type of this chat.
     *
     * @return The type of this chat.
     */
    public abstract Type getType();

    /**
     * Gets the name of the chat protocol this chat belongs to.
     *
     * @return The name of the protocol (Skype, IRC...)
     */
    public abstract String getChatProtocol();

    public enum Type {
        /**
         * The chat is a one-on-one chat (private message).
         */
        PM,
        /**
         * The chat is a group chat (has more than two participants).
         */
        GROUP,
        /**
         * The chat type doesn't match anything else
         */
        OTHER;
    }
}
