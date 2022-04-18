//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.configuration.serialization.*;
import java.util.*;

public class Statistics implements ConfigurationSerializable
{
    int chatMessages;
    int answers;
    int warns;
    int mutes;
    int mutesDuration;
    int totalVotesStarted;
    int successfulVotes;
    int modsAndInfCommands;
    int onlineSeconds;
    
    public Statistics(final int chatMessages, final int answers, final int warns, final int mutes, final int mutesDuration, final int totalVotesStarted, final int successfulVotes, final int modsAndInfCommands, final int onlineSeconds) {
        this.chatMessages = chatMessages;
        this.answers = answers;
        this.warns = warns;
        this.mutes = mutes;
        this.mutesDuration = mutesDuration;
        this.totalVotesStarted = totalVotesStarted;
        this.successfulVotes = successfulVotes;
        this.modsAndInfCommands = modsAndInfCommands;
        this.onlineSeconds = onlineSeconds;
    }
    
    public Map<String, Object> serialize() {
        final LinkedHashMap result = new LinkedHashMap();
        result.put("chatMessages", this.chatMessages);
        result.put("answers", this.answers);
        result.put("warns", this.warns);
        result.put("mutes", this.mutes);
        result.put("mutesDuration", this.mutesDuration);
        result.put("totalVotesStarted", this.totalVotesStarted);
        result.put("successfulVotes", this.successfulVotes);
        result.put("modsAndInfCommands", this.modsAndInfCommands);
        result.put("onlineSeconds", this.onlineSeconds);
        return (Map<String, Object>)result;
    }
    
    public static Statistics deserialize(final Map<String, Object> args) {
        int chatMessages = 0;
        int answers = 0;
        int warns = 0;
        int mutes = 0;
        int mutesDuration = 0;
        int totalVotesStarted = 0;
        int successfulVotes = 0;
        int modsAndInfCommands = 0;
        int onlineSeconds = 0;
        if (args.containsKey("chatMessages")) {
            chatMessages = args.get("chatMessages");
        }
        if (args.containsKey("answers")) {
            answers = args.get("answers");
        }
        if (args.containsKey("warns")) {
            warns = args.get("warns");
        }
        if (args.containsKey("mutes")) {
            mutes = args.get("mutes");
        }
        if (args.containsKey("mutesDuration")) {
            mutesDuration = args.get("mutesDuration");
        }
        if (args.containsKey("totalVotesStarted")) {
            totalVotesStarted = args.get("totalVotesStarted");
        }
        if (args.containsKey("successfulVotes")) {
            successfulVotes = args.get("successfulVotes");
        }
        if (args.containsKey("modsAndInfCommands")) {
            modsAndInfCommands = args.get("modsAndInfCommands");
        }
        if (args.containsKey("onlineSeconds")) {
            onlineSeconds = args.get("onlineSeconds");
        }
        return new Statistics(chatMessages, answers, warns, mutes, mutesDuration, totalVotesStarted, successfulVotes, modsAndInfCommands, onlineSeconds);
    }
}
