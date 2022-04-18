//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.scheduler.*;
import org.bukkit.plugin.*;

public abstract class Vote
{
    protected long duration;
    protected float successRatio;
    protected VoteHandler voteHandler;
    protected HashSet<Player> votedFor;
    protected HashSet<Player> votedAgainst;
    protected Player playerStarter;
    protected int successAdvantage;
    protected String positiveOutcome;
    protected String negativeOutcome;
    
    protected Vote(final int durationSeconds, final float successRatio, final int successAdvantage, final VoteHandler voteHandler, final Player starter) {
        this.votedFor = new HashSet<Player>();
        this.votedAgainst = new HashSet<Player>();
        this.duration = durationSeconds * 1000;
        this.successRatio = successRatio;
        this.voteHandler = voteHandler;
        this.playerStarter = starter;
        this.successAdvantage = successAdvantage;
        this.start();
    }
    
    abstract void start();
    
    protected SuccessReport tryToCastVote(final Player player, final boolean YES) {
        if (this.votedFor.contains(player) || this.votedAgainst.contains(player)) {
            return new SuccessReport(true, "\u0442\u044b \u0443\u0436\u0435 \u0443\u0447\u0430\u0441\u0442\u0432\u043e\u0432\u0430\u043b \u0432 \u044d\u0442\u043e\u043c \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0438, <persik> :*");
        }
        if (YES) {
            this.votedFor.add(player);
            return new SuccessReport(true, "\u0442\u044b \u043f\u0440\u043e\u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043b #cg\"\u0437\u0430\"");
        }
        this.votedAgainst.add(player);
        return new SuccessReport(true, "\u0442\u044b \u043f\u0440\u043e\u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043b #cr\"\u043f\u0440\u043e\u0442\u0438\u0432\"");
    }
    
    protected void start0() {
        this.votedFor.add(this.playerStarter);
        final BukkitRunnable br = new BukkitRunnable() {
            public void run() {
                Vote.this.finish();
            }
        };
        br.runTaskLater((Plugin)this.voteHandler.context, this.duration / 1000L * 20L);
    }
    
    protected SuccessReport castVote(final Player player, final boolean FOR) {
        if (this.votedFor.contains(player) || this.votedAgainst.contains(player)) {
            return new SuccessReport(true, "\u0442\u044b \u0443\u0436\u0435 \u043f\u0440\u0438\u043d\u0438\u043c\u0430\u043b \u0443\u0447\u0430\u0441\u0442\u0438\u0435 \u0432 \u044d\u0442\u043e\u043c \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0438, <persik> :*");
        }
        if (FOR) {
            this.votedFor.add(player);
            return new SuccessReport(true, "\u0442\u044b \u043f\u0440\u043e\u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043b #cg\"\u0437\u0430\"#c3");
        }
        this.votedAgainst.add(player);
        return new SuccessReport(true, "\u0442\u044b \u043f\u0440\u043e\u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043b #cr\"\u043f\u0440\u043e\u0442\u0438\u0432\"#c3");
    }
    
    abstract void finish();
}
