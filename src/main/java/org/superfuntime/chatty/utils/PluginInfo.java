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

import org.superfuntime.chatty.Plugin;
import org.superfuntime.chatty.yml.YAMLNode;

/**
 * Contains information about a running plugin.
 */
public class PluginInfo {
    private String name;
    private Plugin instance;
    private YAMLNode node;

    public PluginInfo(String name, Plugin instance, YAMLNode node) {
        this.name = name;
        this.instance = instance;
        this.node = node;
    }

    /**
     * Gets the name of the plugin
     *
     * @return The name of the plugin
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the plugin's instance.
     *
     * @return The plugin's instance
     */
    public Plugin getInstance() {
        return instance;
    }

    /**
     * Gets the settings node of this plugin. This is a representation of the data in plugin.yml in the plugin's jar file.
     *
     * @return The plugin's settings node
     */
    public YAMLNode getNode() {
        return node;
    }
}
