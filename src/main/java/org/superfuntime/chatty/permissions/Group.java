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

package org.superfuntime.chatty.permissions;

/**
 * Permission group.
 */
public enum Group {
    ADMIN(100),
    OPERATOR(50),
    USER(0);
    private int level;

    private Group(int level) {
        this.level = level;
    }

    /**
     * Gets if the group has access over a group.
     *
     * @param minGroup The lowest ranking group to have access
     *
     * @return {@code true} if the given group is the same or lower in rank, {@code false} otherwise.
     */
    public boolean hasAccess(Group minGroup) {
        return level >= minGroup.level;
    }
}
