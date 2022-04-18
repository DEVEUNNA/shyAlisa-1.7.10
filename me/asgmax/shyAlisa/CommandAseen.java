//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class CommandAseen implements CommandExecutor
{
    private shyAlisa context;
    
    public CommandAseen(final shyAlisa context) {
        this.context = context;
    }
    
    public boolean onCommand(final CommandSender commandSender, final Command cmd, final String label, final String[] args) {
        if (commandSender instanceof Player) {
            final Player sender = (Player)commandSender;
            if (args.length >= 1) {
                this.context.alisa.whisper(sender, this.getSeenString(args[0]));
            }
            else {
                this.context.alisa.whisper(sender, "\u043f\u043e\u0445\u043e\u0436\u0435 \u0442\u044b \u0437\u0430\u0431\u044b\u043b \u0443\u043a\u0430\u0437\u0430\u0442\u044c \u0438\u043c\u044f");
            }
        }
        return true;
    }
    
    private String getSeenString(final String playerName) {
        final Player onlinePlayer = Bukkit.getPlayerExact(playerName);
        if (onlinePlayer != null && onlinePlayer.isOnline()) {
            if (!this.context.alisa.moderatorsHandler.isModerator(onlinePlayer.getName())) {
                return String.format("\u0438\u0433\u0440\u043e\u043a #c2%s#c3 #cg\u043e\u043d\u043b\u0430\u0439\u043d", onlinePlayer.getName());
            }
            return "\u043f\u0440\u043e\u0441\u0442\u0438, <persik>, \u044d\u0442\u043e \u0441\u0435\u043a\u0440\u0435\u0442\u043d\u0430\u044f \u0438\u043d\u0444\u0430 :(";
        }
        else {
            final OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerName);
            if (offlinePlayer == null || !offlinePlayer.getName().equalsIgnoreCase(playerName)) {
                return String.format("\u043d\u0435 \u043c\u043e\u0433\u0443 \u043d\u0430\u0439\u0442\u0438 \u0438\u0433\u0440\u043e\u043a\u0430 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c #c2%s#c3 :(", playerName);
            }
            if (this.context.alisa.moderatorsHandler.isModerator(offlinePlayer.getName())) {
                return "\u043f\u0440\u043e\u0441\u0442\u0438, <persik>, \u044d\u0442\u043e \u0441\u0435\u043a\u0440\u0435\u0442\u043d\u0430\u044f \u0438\u043d\u0444\u0430 :(";
            }
            final Long diff = System.currentTimeMillis() - offlinePlayer.getLastPlayed();
            final long days = diff / 86400000L;
            final long hours = diff / 3600000L % 24L;
            final long minutes = diff / 60000L % 60L;
            final long seconds = diff / 1000L % 60L;
            if (days > 1000L) {
                return String.format("\u043d\u0435 \u043c\u043e\u0433\u0443 \u043d\u0430\u0439\u0442\u0438 \u0438\u0433\u0440\u043e\u043a\u0430 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c #c2%s#c3 :(", playerName);
            }
            final StringBuilder sb = new StringBuilder(String.format("\u0438\u0433\u0440\u043e\u043a #c2%s#c3 #cr\u043e\u0444\u0444\u043b\u0430\u0439\u043d#c3", offlinePlayer.getName()));
            if (days != 0L) {
                sb.append(String.format(" #c2%02d#c3 \u0434\u043d\u0435\u0439,", days));
            }
            sb.append(String.format(" #c2%02d#c3 \u0447\u0430\u0441\u043e\u0432,", hours));
            sb.append(String.format(" #c2%02d#c3 \u043c\u0438\u043d\u0443\u0442", minutes));
            sb.append(String.format(" #c2%02d#c3 \u0441\u0435\u043a\u0443\u043d\u0434", seconds));
            return sb.toString();
        }
    }
}
