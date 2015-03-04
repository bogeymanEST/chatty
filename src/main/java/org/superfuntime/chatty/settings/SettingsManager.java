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

package org.superfuntime.chatty.settings;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.superfuntime.chatty.yml.YAMLFormat;
import org.superfuntime.chatty.yml.YAMLProcessor;

import java.io.*;

/**
 * Provides access to settings.
 */
public class SettingsManager {
    /**
     * The path to the config file. This must math the location of the file in the resources of this application.
     * The path must not start with '/'.
     */
    public static final String MAIN_CONFIG_PATH = "config/config.yml";
    private static Logger logger = LogManager.getLogger();
    private final YAMLProcessor processor;

    public SettingsManager() {
        File config = new File(MAIN_CONFIG_PATH);
        if (!config.exists()) {
            config.getParentFile().mkdirs();
            InputStream configInStream = getClass().getResourceAsStream("/" + MAIN_CONFIG_PATH);
            if (configInStream == null)
                throw new NullPointerException("Couldn't find default config!");

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(config);
                IOUtils.copy(configInStream, fos);
                logger.info("Created default settings file as {}", MAIN_CONFIG_PATH);
                logger.info("You should modify it.");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    configInStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        processor = new YAMLProcessor(config, true, YAMLFormat.EXTENDED);
        try {
            processor.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YAMLProcessor getMainConfig() {
        return processor;
    }


}
