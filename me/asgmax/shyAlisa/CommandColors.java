//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.command.*;
import java.util.*;

public class CommandColors implements CommandExecutor
{
    private shyAlisa context;
    private ArrayList<String> colors;
    
    public CommandColors(final shyAlisa context) {
        this.context = context;
        this.colors = mf.readProjectFileLines("colors.txt");
    }
    
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        final StringBuilder sb = new StringBuilder("#c2\u0446\u0432\u0435\u0442\u043e\u0432\u044b\u0435 \u043a\u043e\u0434\u044b \u0447\u0430\u0442\u0430#c3:\n");
        for (final String colorstring : this.colors) {
            sb.append(colorstring).append("\n");
        }
        this.context.alisa.whisper(commandSender, sb.toString());
        return true;
    }
}
