//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.command.*;
import java.util.*;

public class CommandInf implements CommandExecutor
{
    private shyAlisa context;
    
    public CommandInf(final shyAlisa context) {
        this.context = context;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Statistics statistics = this.context.alisa.statistics;
        ++statistics.modsAndInfCommands;
        if (args.length < 1) {
            this.context.alisa.whisper(sender, "#c3\u043f\u043e\u0445\u043e\u0436\u0435, \u0442\u044b \u0437\u0430\u0431\u044b\u043b \u0432\u0432\u0435\u0441\u0442\u0438 \u043f\u0443\u043d\u043a\u0442 \u043f\u0440\u0430\u0432\u0438\u043b, <persik> :*");
            return true;
        }
        final String paragraph = args[0].toLowerCase().replace(",", ".");
        if (this.context.alisa.serverRules.containsKey(paragraph)) {
            final String message = this.getAnswerFromServerRulesMap(paragraph, this.context.alisa.serverRules);
            this.context.alisa.whisper(sender, message);
            return true;
        }
        if (this.context.alisa.serverRulesExtra.containsKey(paragraph)) {
            final String message = this.getAnswerFromServerRulesMap(paragraph, this.context.alisa.serverRulesExtra);
            this.context.alisa.whisper(sender, message);
            return true;
        }
        this.context.alisa.whisper(sender, "#c3\u043f\u0440\u043e\u0441\u0442\u0438, <persik>, \u043d\u043e \u0442\u0430\u043a\u043e\u0433\u043e \u043f\u0443\u043d\u043a\u0442\u0430 \u043f\u0440\u0430\u0432\u0438\u043b \u043d\u0435\u0442 :(");
        return true;
    }
    
    private String getAnswerFromServerRulesMap(final String paragraph, final HashMap<String, ServerRule> map) {
        String result = "#cr" + paragraph + "#c3: " + map.get(paragraph).description + "\n";
        if (!map.get(paragraph).punishment.isEmpty()) {
            result = result + "#cr\u041d\u0430\u043a\u0430\u0437\u0430\u043d\u0438\u0435: #c3" + map.get(paragraph).punishment;
        }
        return result;
    }
}
