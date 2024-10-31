package com.zszf.ayakabase.command;

import com.zszf.ayakabase.command.impl.ToggleCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    public static List<Command> commands;

    public void init() {
        commands = new ArrayList<>();
        addCommand(new ToggleCommand());
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void callCommand(String command, List<String> params) {
        commands.forEach(i -> {
            if (i.getName().equals(command) || i.getAliases().contains(command)) {
                i.onCommand(params);
            }
        });
    }
}
