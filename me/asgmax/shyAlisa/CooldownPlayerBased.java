//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import java.util.*;
import org.bukkit.entity.*;

public class CooldownPlayerBased
{
    private HashMap<Player, Cooldown> cooldowns;
    private long duration;
    
    public CooldownPlayerBased(final int separateDurationSeconds) {
        this.cooldowns = new HashMap<Player, Cooldown>();
        this.duration = separateDurationSeconds * 1000;
    }
    
    protected boolean isExpired(final Player player) {
        return !this.cooldowns.containsKey(player) || this.cooldowns.get(player).isExpired();
    }
    
    protected void trigger(final Player player) {
        if (!this.cooldowns.containsKey(player)) {
            this.cooldowns.put(player, new Cooldown(this.duration / 1000L));
        }
        this.cooldowns.get(player).trigger();
    }
    
    protected long getSecondsLeft(final Player player) {
        if (this.cooldowns.containsKey(player)) {
            return this.cooldowns.get(player).getSecondsLeft();
        }
        return 0L;
    }
}
