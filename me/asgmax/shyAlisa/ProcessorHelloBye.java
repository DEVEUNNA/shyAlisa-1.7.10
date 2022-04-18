//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;
import java.util.regex.*;
import java.util.*;

public class ProcessorHelloBye implements IProcessor
{
    private shyAlisa context;
    private Pattern vsem;
    private ArrayList<String> helloSecondaryWords;
    private ArrayList<String> byeSecondaryWords;
    
    public boolean processMessage(final Player player, String message) {
        if (this.context.alisa.cooldownsHandler.helloByeCooldown.isExpired()) {
            message = message.toLowerCase();
            final Matcher m = this.vsem.matcher(message);
            if (message.contains("\u0432\u0441\u0435\u043c") && !m.find()) {
                if (this.containsWord(message, this.helloSecondaryWords)) {
                    this.context.alisa.sayHello(player);
                    this.context.alisa.cooldownsHandler.helloByeCooldown.trigger();
                    return false;
                }
                if (this.containsWord(message, this.byeSecondaryWords)) {
                    this.context.alisa.sayBye(player);
                    this.context.alisa.cooldownsHandler.helloByeCooldown.trigger();
                    return false;
                }
            }
        }
        return false;
    }
    
    private boolean containsWord(final String message, final ArrayList<String> words) {
        for (final String s : words) {
            if (message.contains(s)) {
                return true;
            }
        }
        return false;
    }
    
    public ProcessorHelloBye(final shyAlisa context) {
        this.context = context;
        this.vsem = Pattern.compile("\\S\u0432\u0441\u0435\u043c");
        this.helloSecondaryWords = (ArrayList<String>)mf.readProjectFileLines("hello-secondary-words.txt");
        this.byeSecondaryWords = (ArrayList<String>)mf.readProjectFileLines("bye-secondary-words.txt");
    }
}
