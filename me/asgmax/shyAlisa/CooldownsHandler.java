//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

public class CooldownsHandler
{
    protected shyAlisa context;
    protected Cooldown helloByeCooldown;
    protected Cooldown votesunGlobalCooldown;
    protected Cooldown votedayGlobalCooldown;
    protected CooldownIndexBased questionsCooldowns;
    protected CooldownPlayerBased warnCooldowns;
    protected CooldownPlayerBased votesunPersonalCooldowns;
    protected CooldownPlayerBased votedayPersonalCooldowns;
    
    protected CooldownsHandler(final shyAlisa context) {
        this.context = context;
        this.helloByeCooldown = new Cooldown((long)context.config.getInt("cooldown.hello"));
        this.questionsCooldowns = new CooldownIndexBased(context.config.getInt("cooldown.answers"));
        this.warnCooldowns = new CooldownPlayerBased(context.config.getInt("cooldown.warn"));
        this.votesunGlobalCooldown = new Cooldown((long)context.config.getInt("cooldown.votesun-global"));
        this.votedayGlobalCooldown = new Cooldown((long)context.config.getInt("cooldown.voteday-global"));
        this.votesunPersonalCooldowns = new CooldownPlayerBased(context.config.getInt("cooldown.votesun-personal"));
        this.votedayPersonalCooldowns = new CooldownPlayerBased(context.config.getInt("cooldown.voteday-personal"));
    }
}
