package com.willfp.talismans.talismans.talismans;

import com.willfp.eco.core.integrations.mcmmo.McmmoManager;
import com.willfp.talismans.talismans.Talisman;
import com.willfp.talismans.talismans.TalismanLevel;
import com.willfp.talismans.talismans.Talismans;
import com.willfp.talismans.talismans.util.TalismanChecks;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.jetbrains.annotations.NotNull;

public class FluxTalisman extends Talisman {
    public FluxTalisman() {
        super("flux");
    }

    @EventHandler
    public void onMeleeAttack(@NotNull final EntityDamageByEntityEvent event) {
        if (McmmoManager.isFake(event)) {
            return;
        }

        if (!(event.getDamager() instanceof Player attacker)) {
            return;
        }

        if (event.isCancelled()) {
            return;
        }

        if (this.getDisabledWorlds().contains(attacker.getWorld())) {
            return;
        }

        double distance = this.getConfig().getDouble(Talismans.CONFIG_LOCATION + "distance");


        for (Entity nearbyEntity : attacker.getNearbyEntities(distance, distance, distance)) {
            if (!(nearbyEntity instanceof Player player)) {
                continue;
            }

            TalismanLevel level = TalismanChecks.getTalismanLevel(player, this);

            if (level == null) {
                continue;
            }

            event.setDamage(event.getDamage() * (1 + (level.getConfig().getDouble(Talismans.CONFIG_LOCATION + "percent-more-damage")) / 100));
        }
    }
}
