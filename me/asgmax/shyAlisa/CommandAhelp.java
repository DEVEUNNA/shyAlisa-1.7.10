

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.util.*;

public class CommandAhelp implements CommandExecutor
{
    private shyAlisa context;
    private ArrayList<String> help;
    
    public CommandAhelp(final shyAlisa context) {
        this.context = context;
        this.help = mf.readProjectFileLines("command-help.txt");
    }
    
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player)commandSender;
            final StringBuilder sb = new StringBuilder("#c2\u0421\u043f\u0438\u0441\u043e\u043a \u0434\u043e\u0441\u0442\u0443\u043f\u043d\u044b\u0445 \u043a\u043e\u043c\u0430\u043d\u0434#c3:\n");
            for (final String str : this.help) {
                sb.append(str).append("\n");
            }
            this.context.alisa.whisper(player, sb.toString());
        }
        return true;
    }
}
