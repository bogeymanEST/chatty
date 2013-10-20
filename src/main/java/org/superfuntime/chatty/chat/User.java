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

package org.superfuntime.chatty.chat;

import org.superfuntime.chatty.permissions.PermissionManager;
import org.superfuntime.chatty.permissions.Profile;

/**
 * Represents a single user on a chat protocol.
 * <p/>
 * Every bot implementation should extend this class to provide functionality.
 */
public abstract class User {
    private Profile profile = null;

    /**
     * Gets the display (human-friendly) name of the user
     *
     * @return The display name of the user
     */
    public abstract String getDisplayName();

    /**
     * Gets the unique identifier of this user. This does not have to be human-friendly, but it has to be unique to
     * this specific user and change as little as possible over time.
     * <p/>
     * The identifier should include the name of the chat protocol this user belongs to.
     *
     * @return The unique identifier
     */
    public abstract String getUniqueIdentifier();

    /**
     * Gets the user's profile. If the user has no profile, a blank one will be created, <b>but not saved in the user's database!</b>
     * <p/>
     * The user must create a profile for themselves with a username and password (see {@link PermissionManager#createUserProfile(User, String, String)}).
     *
     * @return The user's profile
     */
    public final Profile getProfile() {
        if (profile != null) return profile;
        profile = PermissionManager.getUserProfile(this);
        if (profile != null) return profile;
        profile = new Profile();
        return profile;
    }

    /**
     * Gets if this user has a registered profile.
     *
     * @return {@code true} if the user has a profile, {@code false} otherwise.
     */
    public final boolean hasProfile() {
        return PermissionManager.getUserProfile(this) != null;
    }

    /**
     * Sets the user's profile
     *
     * @param profile The profile to set to
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
