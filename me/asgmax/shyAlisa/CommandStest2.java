//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class CommandStest2 implements CommandExecutor
{
    private shyAlisa context;
    
    public CommandStest2(final shyAlisa context) {
        this.context = context;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        this.context.alisa.test2((Player)sender, args);
        return true;
    }
}
