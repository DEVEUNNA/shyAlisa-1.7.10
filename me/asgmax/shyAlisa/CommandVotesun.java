//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class CommandVotesun implements CommandExecutor
{
    private shyAlisa context;
    
    public CommandVotesun(final shyAlisa context) {
        this.context = context;
    }
    
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player)commandSender;
            final SuccessReport sr = this.context.alisa.voteHandler.tryToStartVotesun(player);
            if (sr.success) {
                this.context.alisa.whisper(player, sr.message);
            }
        }
        return true;
    }
}
