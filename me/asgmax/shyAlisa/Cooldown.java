//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

public class Cooldown
{
    private long lastTriggered;
    private long duration;
    
    protected Cooldown(final long durationSeconds) {
        this.duration = durationSeconds * 1000L;
        this.lastTriggered = 0L;
    }
    
    protected boolean isExpired() {
        return System.currentTimeMillis() - this.lastTriggered >= this.duration;
    }
    
    protected void trigger() {
        this.lastTriggered = System.currentTimeMillis();
    }
    
    protected long getSecondsLeft() {
        return (this.lastTriggered + this.duration - System.currentTimeMillis()) / 1000L;
    }
}
