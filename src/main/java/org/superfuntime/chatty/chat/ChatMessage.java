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

import java.util.Date;

/**
 * Represents a single chat message.
 * <p/>
 * Every bot implementation should extend this class to provide functionality.
 */
public class ChatMessage {
    private final String content;
    private final User sender;
    private final Chat chat;
    private final Date time;
    private final Type type;

    public ChatMessage(String content, User sender, Chat chat, Date time, Type type) {
        this.content = content;
        this.sender = sender;
        this.chat = chat;
        this.time = time;
        this.type = type;
    }

    /**
     * Gets the content (text) of this chat message.
     *
     * @return The content of this chat mesage
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the sender of this chat message
     *
     * @return The sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * Gets the type of this chat message
     *
     * @return The type
     */
    public Type getType() {
        return type;
    }

    /**
     * Gets the time this chat message was sent at
     *
     * @return The time this chat message was sent at
     */
    public Date getTime() {
        return time;
    }

    /**
     * Gets the chat this message was sent to
     *
     * @return The chat this message was sent to
     */
    public Chat getChat() {
        return chat;
    }

    public enum Type {
        /**
         * This message was said normally
         */
        SAID,
        /**
         * This message was emoted (i.e. /me command).
         */
        EMOTED,
        /**
         * Message doesn't match any other types
         */
        OTHER
    }
}
