package com.github.derpynewbie.databasetest.command;

import com.github.derpynewbie.databasetest.Main;

public class CommandStop extends CommandExecutor {

    @Override
    public boolean onCommand(String[] args) {

        Main.onStop();

        return true;
    }
}
