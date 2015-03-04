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

package org.superfuntime.chatty.utils;

import org.superfuntime.chatty.Bot;
import org.superfuntime.chatty.yml.YAMLNode;

/**
 * Contains info about a loaded bot. The bot does not have to be running.
 */
public class BotClassInfo {
    private Class<? extends Bot> botClass;
    private YAMLNode node;

    public BotClassInfo(Class<? extends Bot> botClass, YAMLNode node) {
        this.botClass = botClass;
        this.node = node;
    }

    /**
     * Gets the class of the bot
     *
     * @return The class of the bot
     */
    public Class<? extends Bot> getBotClass() {
        return botClass;
    }

    /**
     * Gets the settings node of this bot. This is the bot.yml configuration in the bot's jar.
     *
     * @return The settings node of this bot
     */
    public YAMLNode getNode() {
        return node;
    }
}
