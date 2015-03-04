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

import org.superfuntime.chatty.chat.User;
import org.superfuntime.chatty.utils.Utils;
import org.superfuntime.chatty.yml.YAMLFormat;
import org.superfuntime.chatty.yml.YAMLProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides methods for working with user profiles.
 */
public class PermissionManager {
    /**
     * Path to the user storage file.
     */
    public static final String USERS_FILE = "data/users.yml";
    private static YAMLProcessor yaml = new YAMLProcessor(new File(USERS_FILE), true, YAMLFormat.COMPACT);

    /**
     * Gets a user's stored profile.
     *
     * @param user The user
     *
     * @return The stored profile or {@code null} if one doesn't exist.
     */
    public static Profile getUserProfile(User user) {
        List<Profile> users = getUserProfiles();
        for (Profile p : users) {
            if (p.ids.contains(user.getUniqueIdentifier())) return p;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static List<Profile> getUserProfiles() {
        List<Profile> users = (List<Profile>) yaml.getProperty("users");
        if (users == null) {
            users = new ArrayList<Profile>();
            yaml.setProperty("users", users);
            yaml.save();
        }
        return users;
    }

    /**
     * Gets a user's stored profile
     *
     * @param username The username
     * @param password The password (not hashed)
     *
     * @return The stored profile or {@code null} if one doesn't exist
     */
    public static Profile getUserProfile(String username, String password) {
        List<Profile> users = getUserProfiles();
        for (Profile p : users) {
            if (!p.username.equals(username)) continue;
            if (Utils.compareHashes(password, p.password)) return p;
        }
        return null;
    }

    /**
     * Creates a new user profile.
     *
     * @param user     The user to create the profile for
     * @param username The username
     * @param password The password (not hashed)
     *
     * @return The newly created profile
     */
    @SuppressWarnings("unchecked")
    public static Profile createUserProfile(User user, String username, String password) {
        Profile p = new Profile();
        p.ids.add(user.getUniqueIdentifier());
        p.username = username;
        p.password = Utils.getHash(password);
        List<Profile> users = getUserProfiles();
        users.add(p);
        yaml.save();
        return p;
    }
}
