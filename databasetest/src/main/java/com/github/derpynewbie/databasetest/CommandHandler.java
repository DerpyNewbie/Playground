package com.github.derpynewbie.databasetest;

import com.github.derpynewbie.databasetest.command.CommandExecutor;

import java.util.HashMap;

public class CommandHandler {

    private static HashMap<String, CommandExecutor> commandHandlers = new HashMap<>();

    public void execute(String commandName, String[] args) {
        if (commandHandlers.containsKey(commandName.toLowerCase())) {
            if (!commandHandlers.get(commandName.toLowerCase()).onCommand(args)) {
                System.out.println("Command returned false");
            }
        } else {
            System.out.println("Command not found");
        }
    }

    public static void registerCommand(String commandName, CommandExecutor handler) {
        if (commandName == null || handler == null) {
            throw new IllegalArgumentException();
        }

        commandHandlers.put(commandName.toLowerCase(), handler);
    }

}
