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

package org.superfuntime.chatty.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.superfuntime.chatty.events.ChatReceivedEvent;
import org.superfuntime.chatty.commands.CommandManager;
import org.superfuntime.chatty.events.ChatSentEvent;
import org.superfuntime.chatty.events.Listener;

import java.util.Calendar;

/**
 * Listens for chat events and executes commands.
 */
public class ChatListener {

    private static Logger logger = LogManager.getLogger();

    @Listener
    public void chatMessageReceived(ChatReceivedEvent event) {
        ChatMessage message = event.getMessage();
        if (message.getType() != ChatMessage.Type.SAID) return; //Only said messages
        Seconds secDiff = Seconds
                .secondsBetween(new DateTime(message.getTime()), new DateTime(Calendar.getInstance().getTime()));
        if (secDiff.getSeconds() > 120) return; //Don't reply to old messages
        logger.trace("Received '{}' from '{}'", message.getContent(), message.getSender().getDisplayName());
        CommandManager.parse(message);
    }

    @Listener
    public void chatMessageSent(ChatSentEvent event) {
        ChatMessage message = event.getMessage();
        logger.trace("Sent '{}'", message.getContent());
    }
}
