//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;
import java.util.*;

public class ProcessorFlood implements IProcessor
{
    protected shyAlisa context;
    private ArrayList<String> tradeFilters1;
    private ArrayList<String> tradeFilters2;
    private HashMap<Player, MessageData> messages;
    private int flood_any_trade_messages_trigger;
    private int flood_identical_trade_messages_trigger;
    private int flood_timeout;
    private int flood_normal_messages_trigger;
    private int flood_any_trade_messages_period;
    
    protected ProcessorFlood(final shyAlisa context) {
        this.messages = new HashMap<Player, MessageData>();
        this.flood_any_trade_messages_trigger = 3;
        this.flood_identical_trade_messages_trigger = 2;
        this.flood_timeout = 45;
        this.flood_normal_messages_trigger = 3;
        this.flood_any_trade_messages_period = 180;
        this.context = context;
        this.tradeFilters1 = (ArrayList<String>)mf.readProjectFileLines("trade-filters-1.txt");
        this.tradeFilters2 = (ArrayList<String>)mf.readProjectFileLines("trade-filters-2.txt");
    }
    
    private int getTempmuteDurationFlood() {
        return this.context.config.getInt("tempmute.flood");
    }
    
    private int getTempmuteDurationAdvertisement() {
        return this.context.config.getInt("tempmute.advertisement");
    }
    
    public boolean processMessage(final Player player, final String message) {
        if (!this.messages.containsKey(player)) {
            this.messages.put(player, new MessageData(this.flood_any_trade_messages_trigger));
        }
        final boolean isTrade = this.isTradeMessage(message);
        final MessageData md = this.messages.get(player);
        if (md.previousMessage.isEmpty() || !md.previousMessage.equalsIgnoreCase(message) || System.currentTimeMillis() - md.previousMessageTime > this.flood_timeout * 1000) {
            md.subsequentMessagesCount = 1;
            md.previousMessage = message;
            md.previousMessageTime = System.currentTimeMillis();
        }
        else {
            final MessageData messageData = md;
            ++messageData.subsequentMessagesCount;
            md.previousMessage = message;
            md.previousMessageTime = System.currentTimeMillis();
        }
        if (isTrade) {
            md.tradeMessageTimers.remove();
            md.tradeMessageTimers.add(System.currentTimeMillis());
        }
        if (isTrade && md.subsequentMessagesCount >= this.flood_identical_trade_messages_trigger) {
            this.context.alisa.punish(Alisa.PunishmentType.MUTE, player, this.getTempmuteDurationAdvertisement(), "3.1 (\u0447\u0430\u0441\u0442\u0430\u044f \u0440\u0435\u043a\u043b\u0430\u043c\u0430)", Alisa.AnswerReason.ADVERTISEMENT);
            return true;
        }
        if (isTrade && md.tradeMessageTimers.get(0) != 0L && System.currentTimeMillis() - md.tradeMessageTimers.get(0) <= this.flood_any_trade_messages_period * 1000) {
            this.context.alisa.punish(Alisa.PunishmentType.MUTE, player, this.getTempmuteDurationAdvertisement(), "3.1 (\u0447\u0430\u0441\u0442\u0430\u044f \u0440\u0435\u043a\u043b\u0430\u043c\u0430+)", Alisa.AnswerReason.ADVERTISEMENT);
            return true;
        }
        if (md.subsequentMessagesCount >= this.flood_normal_messages_trigger) {
            this.context.alisa.punish(Alisa.PunishmentType.MUTE, player, this.getTempmuteDurationFlood(), "3.1 (\u0444\u043b\u0443\u0434)", Alisa.AnswerReason.FLOOD);
            return true;
        }
        return false;
    }
    
    private boolean isTradeMessage(String message) {
        message = message.toLowerCase();
        if (message.length() > 12) {
            for (final String word : this.tradeFilters1) {
                if (message.contains(word)) {
                    return true;
                }
            }
        }
        if (message.length() > 30) {
            for (final String word : this.tradeFilters2) {
                if (message.contains(word)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    class MessageData
    {
        String previousMessage;
        int subsequentMessagesCount;
        long previousMessageTime;
        LinkedList<Long> tradeMessageTimers;
        
        MessageData(final int anyMessagesTrigger) {
            this.previousMessage = "";
            this.subsequentMessagesCount = 1;
            this.previousMessageTime = 0L;
            this.tradeMessageTimers = new LinkedList<Long>();
            for (int i = 0; i < anyMessagesTrigger; ++i) {
                this.tradeMessageTimers.add(0L);
            }
        }
    }
}
