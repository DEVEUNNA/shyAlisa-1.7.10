//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import java.util.*;
import org.bukkit.entity.*;
import java.util.regex.*;

public class ProcessorProfanity implements IProcessor
{
    protected shyAlisa context;
    ArrayList<Pattern> badWords1;
    ArrayList<Pattern> badWords2;
    
    public ProcessorProfanity(final shyAlisa context) {
        this.context = context;
        final ArrayList<String> temp = (ArrayList<String>)mf.readProjectFileLines("profanity1.txt");
        this.badWords1 = new ArrayList<Pattern>(temp.size());
        for (final String word : temp) {
            final Pattern pat = Pattern.compile(word.replace("|", "\\b"));
            this.badWords1.add(pat);
        }
        final ArrayList<String> temp2 = (ArrayList<String>)mf.readProjectFileLines("profanity2.txt");
        this.badWords2 = new ArrayList<Pattern>(temp2.size());
        for (final String word2 : temp2) {
            final Pattern pat2 = Pattern.compile(word2.replace("|", "\\b"));
            this.badWords2.add(pat2);
        }
    }
    
    public boolean processMessage(final Player player, final String message0) {
        final String message = message0.toLowerCase();
        for (final Pattern p : this.badWords1) {
            final Matcher matcher = p.matcher(message);
            if (matcher.find()) {
                this.context.alisa.punish(Alisa.PunishmentType.MUTE, player, this.getTempmuteDurationProfanity(), "3.2 (\u043d\u0435\u043f\u0440\u0438\u043b\u0438\u0447\u043d\u044b\u0435 \u0432\u044b\u0440\u0430\u0436\u0435\u043d\u0438\u044f)", Alisa.AnswerReason.PROFANITY);
                this.context.debug("mute/profanity: " + player.getName() + ": '" + message + "' (" + message0 + ")");
                return true;
            }
        }
        for (final Pattern p : this.badWords2) {
            final Matcher matcher = p.matcher(message);
            if (matcher.find()) {
                this.context.alisa.punish(Alisa.PunishmentType.WARN, player, 0, "3.2 (\u043d\u0435\u043f\u0440\u0438\u043b\u0438\u0447\u043d\u044b\u0435 \u0432\u044b\u0440\u0430\u0436\u0435\u043d\u0438\u044f)", Alisa.AnswerReason.PROFANITY);
                return true;
            }
        }
        return false;
    }
    
    private int getTempmuteDurationProfanity() {
        return this.context.config.getInt("tempmute.profanity");
    }
}
