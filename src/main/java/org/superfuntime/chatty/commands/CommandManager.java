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

package org.superfuntime.chatty.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.superfuntime.chatty.arguments.Argument;
import org.superfuntime.chatty.arguments.ArgumentValue;
import org.superfuntime.chatty.chat.Chat;
import org.superfuntime.chatty.chat.ChatMessage;
import org.superfuntime.chatty.chat.User;
import org.superfuntime.chatty.events.CommandEvent;
import org.superfuntime.chatty.events.EventManager;
import org.superfuntime.chatty.permissions.Profile;

import java.util.*;

/**
 * User: Bogeyman
 * Date: 27.09.13
 * Time: 20:30
 */
public class CommandManager {
    public static final String COMMAND_PREFIX = "!";
    private static Logger logger = LogManager.getLogger("CmdMgr");
    private static Map<String, CommandInfo> commands = new HashMap<String, CommandInfo>();

    /**
     * Adds a command to the list of available commands
     *
     * @param info The command
     *
     * @return The given command
     */
    public static CommandInfo addCommand(CommandInfo info) {
        logger.trace("Added command " + info.getName());
        commands.put(info.getName(), info);
        return info;
    }

    /**
     * Gets a map of all registered commands.
     *
     * @return Map of all commands. Key is the name of the command.
     */
    public static Map<String, CommandInfo> getCommands() {
        return commands;
    }

    /**
     * Gets a command with the given name
     *
     * @param name The name of the command
     *
     * @return The command or {@code null} if no command with that name exists
     */
    public static CommandInfo getCommand(String name) {
        return commands.get(name);
    }

    /**
     * Parses a message and executes the command if the message contains a command
     *
     * @param message The message
     */
    public static void parse(ChatMessage message) {
        String msg = message.getContent();
        if (!msg.startsWith(COMMAND_PREFIX)) return;
        Scanner sc = new Scanner(msg);
        String name = sc.next().substring(COMMAND_PREFIX.length());
        String arguments = "";
        if (sc.hasNext()) {
            sc.skip(" ");
            arguments = sc.nextLine();
        }
        Chat chat = message.getChat();
        CommandInfo cmd = commands.get(name);
        if (cmd != null) {
            User sender = message.getSender();
            String senderName = sender.getDisplayName();
            Profile profile = sender.getProfile();
            if (!profile.group.hasAccess(cmd.getMinimumGroup())) {
                chat.send("You don't have enough permissions to use this command!");
                logger.info("{} failed to execute '{}' (Minimum group {}, user has {})", senderName, msg,
                            cmd.getMinimumGroup(), profile.group);
                return;
            }
            if (cmd.getChatType() != null && cmd.getChatType() != chat.getType()) {
                chat.send("This command can only be used in " + cmd.getChatType().name() + " chats.");
                logger.info("{} failed to execute '{}' (Restricted to {}, executed in {})", senderName, msg,
                            cmd.getChatType(), chat.getType());
                return;
            }
            sc = new Scanner(arguments);
            List<ArgumentValue> values = new ArrayList<ArgumentValue>();
            List<Argument> args = cmd.getArguments();
            for (int i = 0; i < args.size(); i++) {
                Argument arg = args.get(i);
                Object value = null;
                try {
                    value = arg.getParser().parse(sc);
                } catch (NoSuchElementException e) {
                    if (arg.isRequired()) {
                        logger.info("{} failed to execute '{}' (Missing argument: {})",
                                    senderName, msg, arg.getName());
                        chat.send("USAGE: " + cmd.getUsage());
                        return;
                    }
                } catch (Exception e) {
                    logger.error("Exception when parsing command '{}'", msg);
                    e.printStackTrace();
                }
                if (value == null && arg.isRequired()) {
                    logger.info("{} failed to execute '{}' (Invalid argument: {})",
                                senderName,
                                msg, arg.getName());
                    chat.send("Invalid " + arg.getName() + ". Expecting " + arg.getParser().getUsage());
                    chat.send("USAGE: " + cmd.getUsage());
                    return;
                }
                values.add(new ArgumentValue(arg, value));
            }
            try {
                EventManager.call(new CommandEvent(values, message, cmd));
            } catch (Exception e) {
                logger.error("Failed to execute command '{}'", msg);
                e.printStackTrace();
                return;
            }
            logger.info("{} executed '{}' in chat '{}'", senderName, msg,
                        chat.getName());
        }
    }
}
