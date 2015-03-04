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

package org.superfuntime.chatty.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;

/**
 * An {@link OutputStream} that logs everything written to it.
 */
public class LoggingStream extends OutputStream {
    private final Logger logger;
    private final Level logLevel;

    public LoggingStream(Logger logger, Level logLevel) {
        super();
        this.logger = logger;
        this.logLevel = logLevel;
    }

    @Override
    public void write(byte[] b) throws IOException {
        String string = new String(b);
        log(string);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        String string = new String(b, off, len);
        log(string);
    }

    @Override
    public void write(int b) throws IOException {
        String string = String.valueOf((char) b);
        log(string);
    }

    private void log(String string) {
        if (!string.trim().isEmpty())
            logger.log(logLevel, string);
    }
}
