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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * An argument that can only have a value from a given list of values. All values are case insensitive.
 */
public class ListArgumentParser implements ArgumentParser<String> {
    private final List<String> values = new ArrayList<String>();

    @Override
    public String parse(Scanner scanner) {
        String value = scanner.next().toLowerCase();
        if (values.contains(value)) return value;
        return null;
    }

    @Override
    public String getUsage() {
        StringBuilder usage = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            String value = values.get(i);
            if (i != 0) {
                usage.append("/");
            }
            usage.append(value);
        }
        return usage.toString();
    }

    /**
     * Adds a valid value.
     *
     * @param value The value to add. Case-insensitive
     *
     * @return this
     */
    public ListArgumentParser add(String value) {
        values.add(value.toLowerCase());
        return this;
    }
}
