//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;
import java.util.*;

public class MessageHandler
{
    protected shyAlisa context;
    private ArrayList<IProcessor> globalMessageProcessors;
    private ArrayList<IProcessor> localMessageProcessors;
    protected ProcessorQuestions processorQuestions;
    
    public MessageHandler(final shyAlisa context) {
        this.globalMessageProcessors = new ArrayList<IProcessor>();
        this.localMessageProcessors = new ArrayList<IProcessor>();
        this.context = context;
        this.globalMessageProcessors.add((IProcessor)new ProcessorExtra(context));
        this.globalMessageProcessors.add((IProcessor)new ProcessorProfanity(context));
        this.globalMessageProcessors.add((IProcessor)new ProcessorFlood(context));
        this.globalMessageProcessors.add((IProcessor)new ProcessorSymbolFlood(context));
        this.globalMessageProcessors.add((IProcessor)new ProcessorCaps(context));
        try {
            this.processorQuestions = new ProcessorQuestions(context);
            this.globalMessageProcessors.add((IProcessor)this.processorQuestions);
        }
        catch (Exception e) {
            e.printStackTrace();
            context.log("error creating QuestionProcessor, disabling plugin");
            context.disable();
        }
        this.globalMessageProcessors.add((IProcessor)new ProcessorHelloBye(context));
    }
    
    protected void reloadQuestions() {
        this.processorQuestions.fillQuestionsResponses();
    }
    
    public void handleMessage(final Player player, final String msg0) {
        if (this.context.config.getBoolean("debug")) {
            this.context.log("[debug] [chat] " + player.getName() + ": " + msg0);
        }
        final boolean isGlobal = this.isGlobalMessage(msg0);
        String message = this.deColor(msg0);
        if (isGlobal) {
            message = message.substring(1);
            for (final IProcessor processor : this.globalMessageProcessors) {
                if (processor.processMessage(player, message)) {
                    return;
                }
            }
        }
    }
    
    private boolean isGlobalMessage(final String message) {
        return message.indexOf("!") == 0;
    }
    
    private String deColor(final String msg) {
        return msg.replaceAll("§.", "");
    }
}
