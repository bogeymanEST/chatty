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

package org.superfuntime.chatty.permissions;

import java.util.ArrayList;
import java.util.List;

/**
 * This class stores information about a user. All the fields are public for them to be found by SnakeYAML.
 */
public class Profile {
    /**
     * The username used to log in
     */
    public String username;
    /**
     * The (hashed) password used to log in
     */
    public String password;
    /**
     * The user's group
     */
    public Group group = Group.USER;
    /**
     * List of IDs the user can be identified by
     */
    public List<String> ids = new ArrayList<String>();
}
