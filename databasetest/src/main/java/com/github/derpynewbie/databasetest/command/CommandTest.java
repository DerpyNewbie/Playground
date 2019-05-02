package com.github.derpynewbie.databasetest.command;

public class CommandTest extends CommandExecutor {

    @Override
    public boolean onCommand(String[] args) {

        System.out.println("Test command has been executed.");
        System.out.print("args: ");
        for (String s :
                args) {
            System.out.print(s + ", ");
        }
        System.out.println();
        return true;
    }
}
