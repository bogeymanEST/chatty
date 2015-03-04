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

package org.superfuntime.chatty.arguments;

/**
 * A single command argument.
 */
public class Argument {
    private final String name;
    private final String description;
    private final boolean required;
    private final ArgumentParser parser;

    public Argument(String name, String description, boolean required, ArgumentParser parser) {
        this.name = name;
        this.description = description;
        this.required = required;
        this.parser = parser;
    }

    public Argument(String name, String description, ArgumentParser parser, boolean required) {
        this(name, description, required, parser);
    }

    public Argument(String name, String description, Type type, boolean required) {
        this(name, description, required, type.parser);
    }

    /**
     * Gets the description of this argument
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the name of this argument
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets whether this argument is required
     *
     * @return {@code true} if this argument is required, {@code false} otherwise
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * Gets the parser of this command. Parsers validate the argument and get its value.
     *
     * @return The parser
     */
    public ArgumentParser getParser() {
        return parser;
    }

    @SuppressWarnings("UnusedDeclaration")
    public enum Type {
        /**
         * String type. Everything up to the next space.
         */
        STRING(new StringArgument()),
        /**
         * Integer type.
         */
        INT(new IntArgument()),
        /**
         * Everything to the end of the line is included.
         */
        ALL(new AllArgument());
        private final ArgumentParser parser;

        Type(ArgumentParser parser) {
            this.parser = parser;
        }
    }
}
