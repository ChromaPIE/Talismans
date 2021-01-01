package com.willfp.talismans.talismans.util;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onAttemptTalismanPlace(@NotNull final BlockPlaceEvent event) {
        if (TalismanChecks.getTalismanOnItem(event.getItemInHand()) != null) {
            event.setCancelled(true);
        }
    }
}