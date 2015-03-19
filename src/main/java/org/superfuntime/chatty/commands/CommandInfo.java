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

package org.superfuntime.chatty.commands;

import org.superfuntime.chatty.chat.Chat;
import org.superfuntime.chatty.permissions.Group;
import org.superfuntime.chatty.arguments.Argument;
import org.superfuntime.chatty.arguments.ArgumentParser;

import java.util.ArrayList;
import java.util.List;

/**
 * A command definition. Contains all information about a command.
 */
public class CommandInfo {
    private final String name;
    private final String description;
    private List<Argument> arguments = new ArrayList<Argument>();
    private Chat.Type chatType;
    private Group minimumGroup = Group.USER;
    private boolean hidden;

    public CommandInfo(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Adds an argument
     *
     * @param argument The argument
     *
     * @return this
     */
    public CommandInfo arg(Argument argument) {
        arguments.add(argument);
        return this;
    }

    /**
     * Adds a required argument
     *
     * @param name        The name of the argument
     * @param description The description of the argument
     * @param type        The type of the argument
     *
     * @return this
     */
    public CommandInfo arg(String name, String description, Argument.Type type) {
        return arg(name, description, type, true);
    }

    /**
     * Adds an argument
     *
     * @param name        The name of the argument
     * @param description The description of the argument
     * @param type        The type of the argument
     * @param required    {@code true} if the argument is required, {@code false} otherwise.
     *
     * @return this
     */
    public CommandInfo arg(String name, String description, Argument.Type type, boolean required) {
        return arg(new Argument(name, description, type, required));
    }

    /**
     * Adds a required argument
     *
     * @param name        The name of the argument
     * @param description The description of the argument
     * @param parser      The parser
     *
     * @return this
     */
    public CommandInfo arg(String name, String description, ArgumentParser parser) {
        return arg(name, description, parser, true);
    }

    /**
     * Adds an argument
     *
     * @param name        The name of the argument
     * @param description The description of the argument
     * @param parser      The parser
     * @param required    {@code true} if the argument is required, {@code false} otherwise
     *
     * @return this
     */
    public CommandInfo arg(String name, String description, ArgumentParser parser, boolean required) {
        return arg(new Argument(name, description, parser, required));
    }

    /**
     * Gets the name of the command
     *
     * @return Name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a list of the command's arguments
     *
     * @return List of the command's arguments
     */
    public List<Argument> getArguments() {
        return arguments;
    }

    /**
     * Gets a string describing the usage of this command
     *
     * @return A string describing the usage of this command
     */
    public String getUsage() {
        StringBuilder usage = new StringBuilder(CommandManager.COMMAND_PREFIX + getName());
        List<Argument> arguments1 = getArguments();
        for (Argument arg : arguments1) {
            usage.append(" ");
            char[] brackets = new char[]{'(', ')'};
            if (arg.isRequired())
                brackets = new char[]{'<', '>'};
            usage.append(brackets[0]).append(arg.getName()).append(brackets[1]);
        }
        return usage.toString();
    }

    /**
     * Gets the description of this command
     *
     * @return Description of this command
     */
    public String getDescription() {
        return description;
    }

    /**
     * Restricts the command to only the given type of chat.
     *
     * @param type The type of chat to restrict the command to.
     *
     * @return this
     */
    public CommandInfo setChatType(Chat.Type type) {
        chatType = type;
        return this;
    }

    /**
     * Gets the chat type this command is restricted to.
     *
     * @return The chat type this command is restricted to, {@code null} if it is not restricted.
     */
    public Chat.Type getChatType() {
        return chatType;
    }

    /**
     * Gets the lowest ranked group a user must be in to perform this command
     *
     * @return The lowest ranked group a user must be in to perform this command
     */
    public Group getMinimumGroup() {
        return minimumGroup;
    }

    /**
     * Sets the lowest ranked group a user must be in to perform this command.
     *
     * @param minimumGroup The lowest ranked group a user must be in to perform this command.
     *
     * @return this
     */
    public CommandInfo setMinimumGroup(Group minimumGroup) {
        this.minimumGroup = minimumGroup;
        return this;
    }

    /**
     * Gets whether this command is hidden or not. A hidden command should not be displayed in a public list of commands.
     *
     * @return {@code true} if the command is hidden, {@code false} otherwise.
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Sets whether this command is hidden or not. A hidden command should not be displayed in a public list of commands.
     *
     * @param hidden {@code true} if the command should be hidden, {@code false} otherwise.
     *
     * @return this
     */
    public CommandInfo setHidden(boolean hidden) {
        this.hidden = hidden;
        return this;
    }
}
