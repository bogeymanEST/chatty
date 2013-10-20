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

package org.superfuntime.chatty.arguments;

/**
 * User: Bogeyman
 * Date: 28.09.13
 * Time: 14:07
 */
public class ArgumentValue {
    private final Argument argument;
    private final Object value;

    public ArgumentValue(Argument argument, Object value) {
        this.argument = argument;
        this.value = value;
    }

    /**
     * Gets the argument this value belongs to
     *
     * @return The argument this value belongs to
     */
    public Argument getArgument() {
        return argument;
    }

    /**
     * Gets the value of the argument
     *
     * @return The value of the argument
     */
    public Object getValue() {
        return value;
    }

    /**
     * Returns a string representation of the argument's value.
     *
     * @return The argument's value
     */
    @Override
    public String toString() {
        return getValue().toString();
    }
}
