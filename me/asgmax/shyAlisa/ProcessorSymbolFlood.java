//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;

public class ProcessorSymbolFlood implements IProcessor
{
    private shyAlisa context;
    private int minimumMessageLengthForSymbolCheck;
    
    public ProcessorSymbolFlood(final shyAlisa context) {
        this.minimumMessageLengthForSymbolCheck = 12;
        this.context = context;
    }
    
    public boolean processMessage(final Player player, final String message) {
        if (message.length() >= this.minimumMessageLengthForSymbolCheck && this.getSymbolRatioOfMessage(message) > this.getAllowedSymbolRatio()) {
            this.context.alisa.punish(Alisa.PunishmentType.MUTE, player, this.getTempmuteDurationSymbolFlood(), "3.1 (\u0444\u043b\u0443\u0434 \u0441\u0438\u043c\u0432\u043e\u043b\u0430\u043c\u0438)", Alisa.AnswerReason.FLOOD);
            return true;
        }
        return false;
    }
    
    private int getTempmuteDurationSymbolFlood() {
        return this.context.config.getInt("tempmute.symbol-flood");
    }
    
    public float getSymbolRatioOfMessage(final String message) {
        if (message == null || message.trim().isEmpty()) {
            return 0.0f;
        }
        int count = 0;
        final String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.\u2116;:+=-'";
        for (int i = 0; i < message.length(); ++i) {
            if (specialChars.contains(message.substring(i, i + 1))) {
                ++count;
            }
        }
        return count / (float)message.length();
    }
    
    private float getAllowedSymbolRatio() {
        return this.context.config.getFloat("symbol-ratio");
    }
}
