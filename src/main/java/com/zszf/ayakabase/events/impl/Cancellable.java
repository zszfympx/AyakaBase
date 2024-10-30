package com.zszf.ayakabase.events.impl;

public interface Cancellable {
    boolean isCancelled();

    void setCancelled(boolean cancel);
}
