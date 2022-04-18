//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;
import org.bukkit.scheduler.*;
import org.bukkit.plugin.*;
import org.bukkit.*;
import java.util.*;

public class ProcessorExtra implements IProcessor
{
    private shyAlisa context;
    private String template;
    private ArrayList<String> immunes;
    
    public ProcessorExtra(final shyAlisa context) {
        this.context = context;
        this.template = " \u0443\u043d\u0438\u0447\u0442\u043e\u0436\u044c ";
        this.immunes = (ArrayList<String>)context.config.getList("annihilation-immunity");
    }
    
    public boolean processMessage(final Player player, String message) {
        if (player.isOp()) {
            final String[] words = message.split(",", 2);
            if (words.length > 1 && this.context.alisa.getName().contains(words[0])) {
                message = words[1];
                if (message.startsWith(this.template)) {
                    final String targetName = message.substring(this.template.length());
                    if (this.isPlayerImmune(targetName)) {
                        new BukkitRunnable() {
                            public void run() {
                                ProcessorExtra.this.context.alisa.say("\u041e\u0444\u0438\u0433\u0435\u043b?");
                                ProcessorExtra.this.annihilate(player);
                            }
                        }.runTaskLater((Plugin)this.context, 30L);
                        return true;
                    }
                    final Player target = Bukkit.getPlayerExact(targetName);
                    if (target != null && target.isOnline()) {
                        new BukkitRunnable() {
                            public void run() {
                                ProcessorExtra.this.context.alisa.say("\u041f\u0440\u043e\u0449\u0430\u0439, #c3" + target.getName() + " #c1:(");
                                ProcessorExtra.this.annihilate(target);
                            }
                        }.runTaskLater((Plugin)this.context, 30L);
                    }
                }
            }
        }
        return false;
    }
    
    private boolean isPlayerImmune(final String name) {
        for (final String imm : this.immunes) {
            if (imm.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    
    private void annihilate(final Player p) {
        final int hits = 12;
        for (int i = 0; i <= 12; ++i) {
            final int finalI = i;
            final BukkitRunnable br = new BukkitRunnable() {
                public void run() {
                    p.getWorld().strikeLightningEffect(p.getLocation());
                    p.damage(2.0);
                    if (finalI == 12) {
                        p.setHealth(0.0);
                    }
                }
            };
            br.runTaskLater((Plugin)this.context, (long)(20 + i * 2));
        }
    }
}
