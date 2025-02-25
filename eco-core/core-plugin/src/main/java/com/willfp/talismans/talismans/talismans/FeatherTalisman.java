package com.willfp.talismans.talismans.talismans;

import com.willfp.talismans.talismans.Talisman;
import com.willfp.talismans.talismans.TalismanLevel;
import com.willfp.talismans.talismans.Talismans;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class FeatherTalisman extends Talisman {
    public FeatherTalisman() {
        super("feather");
    }

    @Override
    public void onDamage(@NotNull final TalismanLevel level,
                         @NotNull final Player victim,
                         @NotNull final EntityDamageEvent event) {
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }

        double damage = event.getDamage() * this.getConfig().getDouble(Talismans.CONFIG_LOCATION + "multiplier");
        if (damage < 0.5) {
            event.setCancelled(true);
            return;
        }

        event.setDamage(damage);
    }
}
