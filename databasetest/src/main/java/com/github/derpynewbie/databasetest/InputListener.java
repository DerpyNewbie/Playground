package com.github.derpynewbie.databasetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputListener {

    private static CommandHandler commandHandler = new CommandHandler();
    private static boolean isEnable;


    private void listen() {
        System.out.println("Listening...\n");
        String input = new Scanner(System.in).nextLine();
        String[] rawArg = input.split(" ");
        List<String> commandArg = new ArrayList<>();
        String commandName = rawArg[0];

        for (int i = 1; rawArg.length > i; i++) {
            commandArg.add(rawArg[i]);
        }

        commandHandler.execute(commandName, commandArg.toArray(new String[]{}));
        if (isEnable) {
            listen();
        }
    }

    public static void stop(boolean b) {
        isEnable = !b;
    }

    public InputListener() {
        isEnable = true;
        listen();
    }

}
