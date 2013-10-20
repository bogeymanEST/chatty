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

package org.superfuntime.chatty.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * User: Bogeyman
 * Date: 13.10.13
 * Time: 14:58
 */
public class Utils {
    /**
     * Hashes the given string
     *
     * @param str The string
     *
     * @return The hashed string
     */
    public static String getHash(String str) {
        return BCrypt.hashpw(str, BCrypt.gensalt());
    }

    /**
     * Compares the hash of an unhashed string to a hashed string
     *
     * @param unhashed The unhashed string
     * @param hashed   The hashed string
     *
     * @return {@code true} if the hash of the unhashed string matches the hashed string.
     */
    public static boolean compareHashes(String unhashed, String hashed) {
        return BCrypt.checkpw(unhashed, hashed);
    }
}
