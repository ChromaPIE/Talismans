package com.willfp.talismans.talismans.talismans;

import com.willfp.talismans.talismans.Talisman;
import com.willfp.talismans.talismans.TalismanLevel;
import com.willfp.talismans.talismans.Talismans;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class ResistanceTalisman extends Talisman {
    public ResistanceTalisman() {
        super("resistance");
    }

    @Override
    public void onDamage(@NotNull final TalismanLevel level,
                         @NotNull final Player victim,
                         @NotNull final EntityDamageEvent event) {
        event.setDamage(event.getDamage() * (1 - (level.getConfig().getDouble(Talismans.CONFIG_LOCATION + "percent-less-damage")) / 100));
    }
}
