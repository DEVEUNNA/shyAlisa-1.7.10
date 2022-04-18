//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import java.lang.management.*;

public class CommandServer implements CommandExecutor
{
    private shyAlisa context;
    
    public CommandServer(final shyAlisa context) {
        this.context = context;
    }
    
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player)commandSender;
            final long jvmUpTime = ManagementFactory.getRuntimeMXBean().getUptime();
            final long toRestart = this.context.config.getInt("restart-period") * 60 * 1000 - jvmUpTime;
            final int seconds = (int)(toRestart / 1000L) % 60;
            final int minutes = (int)(toRestart / 60000L % 60L);
            final int hours = (int)(toRestart / 3600000L % 24L);
            final StringBuilder sb = new StringBuilder("#c2\u0414\u043e \u0440\u0435\u0441\u0442\u0430\u0440\u0442\u0430#c3: ");
            if (hours > 0) {
                sb.append(hours).append(" \u0447\u0430\u0441(\u0430), ");
            }
            sb.append(minutes).append(" \u043c\u0438\u043d\u0443\u0442, ");
            sb.append(seconds).append(" \u0441\u0435\u043a\u0443\u043d\u0434");
            this.context.alisa.whisper(player, sb.toString());
        }
        return true;
    }
}
