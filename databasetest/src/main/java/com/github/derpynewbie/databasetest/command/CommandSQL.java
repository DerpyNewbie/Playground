package com.github.derpynewbie.databasetest.command;

import com.github.derpynewbie.databasetest.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommandSQL extends CommandExecutor {

    @Override
    public boolean onCommand(String[] args) {
        if (args == null) {
            return false;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String s :
                    args) {
                sb.append(" ").append(s);
            }
            Statement statement = DatabaseHandler.getConnection().createStatement();
            boolean status = statement.execute(sb.toString().trim());
            System.out.println(status);
            return status;
        } catch (SQLException ex) {
            System.out.println(ex.getCause().toString());
            return false;
        }
    }
}
