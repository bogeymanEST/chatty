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

import org.superfuntime.chatty.arguments.ArgumentValue;
import org.superfuntime.chatty.chat.ChatMessage;
import org.superfuntime.chatty.commands.CommandInfo;

import java.util.List;

/**
 * Called when a command is executed.
 */
public class CommandEvent extends ChatEvent {
    private final List<ArgumentValue> arguments;
    private final CommandInfo command;

    public CommandEvent(List<ArgumentValue> arguments, ChatMessage message, CommandInfo command) {
        super(message);
        this.arguments = arguments;
        this.command = command;
    }

    /**
     * Gets the arguments
     *
     * @return The arguments
     */
    public List<ArgumentValue> getArguments() {
        return arguments;
    }

    /**
     * Returns the stored {@link ArgumentValue} for the given argument.
     *
     * @param name The name of the argument
     * @return The associated {@link ArgumentValue} or {@code null} if the argument doesn't have a value.
     */
    public ArgumentValue getArgument(String name) {
        for (ArgumentValue value : getArguments()) {
            if (value.getArgument().getName().equals(name)) return value;
        }
        return null;
    }

    /**
     * Gets the command that was executed
     *
     * @return The command that was executed
     */
    public CommandInfo getCommand() {
        return command;
    }
}
