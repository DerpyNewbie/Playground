package com.github.derpynewbie.databasetest;

import com.github.derpynewbie.databasetest.command.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting...");
        register();
        DatabaseHandler.openConnection();
        try {
            InputListener inputListener = new InputListener();
        } catch (Exception ex) {
            synchronized (new Main()) {
                ex.printStackTrace();
            }
            System.out.println("Restarting...");
            main(null);
        }
    }

    public static void onStop() {
        System.out.println("Stopping listener...");
        InputListener.stop(true);
        System.out.println("Closing database connection...");
        DatabaseHandler.closeConnection();
        System.out.println("Stopping...");
    }

    private static void register() {
        CommandHandler.registerCommand("sql", new CommandSQL());
        CommandHandler.registerCommand("stop", new CommandStop());
        CommandHandler.registerCommand("add", new CommandAdd());
        CommandHandler.registerCommand("find", new CommandFind());
        CommandHandler.registerCommand("test", new CommandTest());
    }
}
