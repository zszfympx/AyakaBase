package com.zszf.ayakabase.command.impl;

import com.zszf.ayakabase.Ayakabase;
import com.zszf.ayakabase.command.Command;

import java.util.List;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("toggle", List.of("t"));
    }

    @Override
    public void onCommand(List<String> params) {
        if (params.size() != 1) {
            return;
        }
        Ayakabase.getInstance().moduleManager.getModule(params.get(0)).toggle();
    }
}
