//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import java.util.*;

public class CooldownIndexBased
{
    private HashMap<Integer, Cooldown> cooldowns;
    private long duration;
    
    public CooldownIndexBased(final int separateDurationSeconds) {
        this.cooldowns = new HashMap<Integer, Cooldown>();
        this.duration = separateDurationSeconds * 1000;
    }
    
    protected boolean isExpired(final int index) {
        return !this.cooldowns.containsKey(index) || this.cooldowns.get(index).isExpired();
    }
    
    protected void trigger(final int index) {
        if (!this.cooldowns.containsKey(index)) {
            this.cooldowns.put(index, new Cooldown(this.duration / 1000L));
        }
        this.cooldowns.get(index).trigger();
    }
}
