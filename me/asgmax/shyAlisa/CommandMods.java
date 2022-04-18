//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.command.*;

public class CommandMods implements CommandExecutor
{
    private shyAlisa context;
    
    public CommandMods(final shyAlisa context) {
        this.context = context;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Statistics statistics = this.context.alisa.statistics;
        ++statistics.modsAndInfCommands;
        this.context.alisa.whisper(sender, this.context.alisa.moderatorsHandler.getOnlineModsString());
        return true;
    }
}
