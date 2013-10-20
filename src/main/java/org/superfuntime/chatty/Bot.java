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

package org.superfuntime.chatty;

import org.superfuntime.chatty.chat.ChatMessage;
import org.superfuntime.chatty.events.ChatReceivedEvent;
import org.superfuntime.chatty.events.ChatSentEvent;
import org.superfuntime.chatty.events.EventManager;
import org.superfuntime.chatty.utils.BotClassInfo;
import org.superfuntime.chatty.yml.YAMLNode;

import java.util.List;

/**
 * A single bot that can connect to a chat service.
 */
public abstract class Bot extends Plugin {
    private YAMLNode mainConfigNode = null;
    protected BotClassInfo info = null;

    /**
     * Sets the display name of the bot.
     *
     * @param name The name to set to.
     */
    public abstract void setName(String name);

    /**
     * Gets the type of this bot. This is set in the bot.yml inside this bot's jar.
     *
     * @return The type of this bot
     */
    public final String getType() {
        return info.getNode().getString("type");
    }

    /**
     * Called to start the bot up. It should connect to the given server.
     * After this method the bot must be ready to do work if no exceptions have been thrown.
     * <p/>
     * Throwing an exception here will stop the bot's startup process and exclude it from the list of available bots.
     */
    @Override
    public void start() {

    }

    /**
     * Called to stop the bot. Usually used to disconnect from the server and chat rooms.
     */
    @Override
    public void stop() {

    }

    /**
     * Called by the bot instance to notify the chat listener of a received message.
     *
     * @param message The chat message
     */
    public final void onChatMessageReceived(ChatMessage message) {
        EventManager.call(new ChatReceivedEvent(message));
    }

    /**
     * Called by the bot instance to notify the chat listener of a sent message.
     *
     * @param message The chat message
     */
    public final void onChatMessageSent(ChatMessage message) {
        EventManager.call(new ChatSentEvent(message));
    }

    /**
     * Gets the YAML config node in the main config of the chat bot.
     *
     * @return The node.
     */
    public final YAMLNode getSettingsNode() {
        if (mainConfigNode != null) return mainConfigNode;
        final List<YAMLNode> bots = ChatBot.getSettingsManager().getMainConfig().getNodeList("bots", null);
        for (YAMLNode bot : bots) {
            if (bot.getString("type", "UNKNOWN").equals(getType())) {
                mainConfigNode = bot.getNode("settings");
                break;
            }
        }
        return mainConfigNode;
    }
}
