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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: Bogeyman
 * Date: 28.09.13
 * Time: 15:05
 */
public class JSONGetter {
    private static Logger logger = LogManager.getLogger();
    private JSONReplyHandler callback;
    private String url;

    public JSONGetter(JSONReplyHandler callback, String url) {
        this.callback = callback;
        this.url = url;
    }

    /**
     * Starts the JSON retrieving process (in a new thread).
     */
    public void start() {
        logger.trace("Getting JSON from " + url);
        new Thread(new JSONThread(url)).start();
    }

    private class JSONThread implements Runnable {
        private final String url;

        private JSONThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            URL url;
            BufferedReader in = null;
            try {
                url = new URL(this.url);
                final URLConnection connection = url.openConnection();
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);
                in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                Object val = JSONValue.parse(in);
                callback.jsonReply(val);
            } catch (Exception e) {
                e.printStackTrace();
                callback.jsonReply(null);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
