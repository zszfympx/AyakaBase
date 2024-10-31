package com.zszf.ayakabase.command;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class Command {
    private final String name;
    private final List<String> aliases;

    public Command(String name, List<String> aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    public void onCommand(List<String> params) {

    }

}
