//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.*;

public class ProcessorCaps implements IProcessor
{
    private shyAlisa context;
    private int minimumMessageLengthForCapsCheck;
    private int minimumLettersForCapsCheck;
    private ArrayList<String> ignoredWords;
    
    public ProcessorCaps(final shyAlisa context) {
        this.minimumMessageLengthForCapsCheck = 6;
        this.minimumLettersForCapsCheck = 3;
        this.context = context;
        this.ignoredWords = (ArrayList<String>)mf.readProjectFileLines("ignored-words-caps.txt");
    }
    
    public boolean processMessage(final Player player, final String message0) {
        String message = this.removeIgnoredWords(message0);
        message = this.removePlayerNames(message);
        message = this.removeDigitsAndSpaces(message);
        if (message.length() > this.minimumMessageLengthForCapsCheck && this.getCapsRatioOfMessage(message) > this.getAllowedCapsRatio()) {
            this.context.alisa.punish(Alisa.PunishmentType.MUTE, player, this.getTempmuteDurationCaps(), "3.1 (\u043a\u0430\u043f\u0441)", Alisa.AnswerReason.CAPS);
            this.context.debug("mute/caps: " + player.getName() + ": '" + message0 + "' (" + message + ")");
            return true;
        }
        return false;
    }
    
    private String removeDigitsAndSpaces(String message) {
        message = message.replaceAll("\\d", "");
        message = message.replaceAll("\\s", "");
        return message;
    }
    
    private String removeIgnoredWords(String message) {
        for (final String word : this.ignoredWords) {
            message = message.replaceAll(word, "");
        }
        return message;
    }
    
    private String removePlayerNames0(String message) {
        for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
            message = message.replaceFirst(p.getName(), "");
        }
        return message;
    }
    
    private String removePlayerNames(String message) {
        for (final String playername : this.context.alisa.knownPlayerNames) {
            message = message.replaceFirst(playername, "");
        }
        return message;
    }
    
    private int getTempmuteDurationCaps() {
        return this.context.config.getInt("tempmute.caps");
    }
    
    private float getAllowedCapsRatio() {
        return this.context.config.getFloat("caps-ratio");
    }
    
    private float getCapsRatioOfMessage(final String message) {
        int total = 0;
        int uppercase = 0;
        for (int i = 0; i < message.length(); ++i) {
            if (Character.isLetter(message.charAt(i))) {
                ++total;
                if (Character.isUpperCase(message.charAt(i))) {
                    ++uppercase;
                }
            }
        }
        if (total < this.minimumLettersForCapsCheck) {
            return 0.0f;
        }
        return uppercase / (float)total;
    }
}
