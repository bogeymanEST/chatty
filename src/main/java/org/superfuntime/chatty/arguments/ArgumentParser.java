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

import java.util.Scanner;

/**
 * An argument parser.
 * <p/>
 * A parser reads the given input and gets the value of the argument from it.
 */
public interface ArgumentParser<T> {
    /**
     * Called to parse the given string into the value of the argument
     *
     * @param scanner The scanner
     *
     * @return The value of the argument, null if invalid
     */
    public T parse(Scanner scanner);

    /**
     * Called if the given value is determined to be invalid. Gets a string describing the expected text.
     *
     * @return String describing what value is expected.
     */
    public String getUsage();
}
