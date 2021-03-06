/*
 * An easily extendable chat bot for any chat service.
 * Copyright (C) 2015 bogeymanEST
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

import org.superfuntime.chatty.chat.ChatMessage;

/**
 * A chat event. Called when a chat message is sent or received.
 */
public abstract class ChatEvent implements BaseEvent {
    private ChatMessage message;

    public ChatEvent(ChatMessage message) {
        this.message = message;
    }

    /**
     * Gets the message
     *
     * @return The message
     */
    public ChatMessage getMessage() {
        return message;
    }

    /**
     * Convenience method to send a message back to the chat this message was received from.
     * @param lines The chat lines to send
     */
    public void respond(String... lines) {
        message.getChat().send(lines);
    }
}
