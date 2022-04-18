//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;

public class VoteHandler
{
    protected shyAlisa context;
    protected Vote currentVote;
    
    protected VoteHandler(final shyAlisa context) {
        this.context = context;
    }
    
    protected SuccessReport castVote(final Player player, final boolean YES) {
        if (this.currentVote == null) {
            return new SuccessReport(true, "\u043d\u043e \u0432\u0435\u0434\u044c \u0441\u0435\u0439\u0447\u0430\u0441 \u043d\u0435 \u0438\u0434\u0435\u0442 \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0435, <persik> :*");
        }
        return this.currentVote.tryToCastVote(player, YES);
    }
    
    protected SuccessReport tryToStartVotesun(final Player player) {
        if (this.currentVote != null) {
            return new SuccessReport(true, "\u043f\u0440\u043e\u0441\u0442\u0438, <persik>, \u043d\u043e \u0441\u0435\u0439\u0447\u0430\u0441 \u0438\u0434\u0435\u0442 \u0434\u0440\u0443\u0433\u043e\u0435 \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0435 :(");
        }
        if (!this.context.getMainWorld().hasStorm()) {
            return new SuccessReport(true, "\u043d\u043e \u0432\u0435\u0434\u044c \u0441\u0435\u0439\u0447\u0430\u0441 \u043d\u0435 \u0438\u0434\u0435\u0442 \u0434\u043e\u0436\u0434\u044c, <persik> :*");
        }
        if (!this.context.alisa.cooldownsHandler.votesunGlobalCooldown.isExpired()) {
            return new SuccessReport(true, String.format("\u0438\u0437\u0432\u0438\u043d\u0438, <persik>, \u043d\u043e \u0433\u043b\u043e\u0431\u0430\u043b\u044c\u043d\u044b\u0439 \u043a\u0443\u043b\u0434\u0430\u0443\u043d \u043d\u0430 \u044d\u0442\u043e \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0435 \u0435\u0449\u0435 \u043d\u0435 \u043f\u0440\u043e\u0448\u0435\u043b :( \u041e\u0441\u0442\u0430\u043b\u043e\u0441\u044c #c2%d#c3 \u0441\u0435\u043a\u0443\u043d\u0434", this.context.alisa.cooldownsHandler.votesunGlobalCooldown.getSecondsLeft()));
        }
        if (!this.context.alisa.cooldownsHandler.votesunPersonalCooldowns.isExpired(player)) {
            return new SuccessReport(true, String.format("\u043f\u0440\u043e\u0441\u0442\u0438, <persik>, \u043d\u043e \u0442\u0432\u043e\u0439 \u043a\u0443\u043b\u0434\u0430\u0443\u043d \u043d\u0430 \u044d\u0442\u043e \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0435 \u0435\u0449\u0435 \u043d\u0435 \u043f\u0440\u043e\u0448\u0435\u043b :( \u043e\u0441\u0442\u0430\u043b\u043e\u0441\u044c \u0432\u0441\u0435\u0433\u043e \u043b\u0438\u0448\u044c #c2%d#c3 \u0441\u0435\u043a\u0443\u043d\u0434", this.context.alisa.cooldownsHandler.votesunPersonalCooldowns.getSecondsLeft(player)));
        }
        this.context.alisa.voteHandler.startVotesun(player);
        return new SuccessReport(false, "");
    }
    
    private boolean isNight() {
        final long time = this.context.getMainWorld().getTime();
        return time > 12700L && time < 23000L;
    }
    
    protected SuccessReport tryToStartVoteday(final Player player) {
        if (this.currentVote != null) {
            return new SuccessReport(true, "\u043f\u0440\u043e\u0441\u0442\u0438, <persik>, \u043d\u043e \u0441\u0435\u0439\u0447\u0430\u0441 \u0438\u0434\u0435\u0442 \u0434\u0440\u0443\u0433\u043e\u0435 \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0435 :(");
        }
        if (!this.isNight()) {
            return new SuccessReport(true, "\u043d\u043e \u0432\u0435\u0434\u044c \u0441\u0435\u0439\u0447\u0430\u0441 \u043d\u0435 \u043d\u043e\u0447\u044c, <persik> :*");
        }
        if (!this.context.alisa.cooldownsHandler.votedayGlobalCooldown.isExpired()) {
            return new SuccessReport(true, String.format("\u0438\u0437\u0432\u0438\u043d\u0438, <persik>, \u043d\u043e \u0433\u043b\u043e\u0431\u0430\u043b\u044c\u043d\u044b\u0439 \u043a\u0443\u043b\u0434\u0430\u0443\u043d \u043d\u0430 \u044d\u0442\u043e \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0435 \u0435\u0449\u0435 \u043d\u0435 \u043f\u0440\u043e\u0448\u0435\u043b :( \u041e\u0441\u0442\u0430\u043b\u043e\u0441\u044c #c2%d#c3 \u0441\u0435\u043a\u0443\u043d\u0434", this.context.alisa.cooldownsHandler.votedayGlobalCooldown.getSecondsLeft()));
        }
        if (!this.context.alisa.cooldownsHandler.votedayPersonalCooldowns.isExpired(player)) {
            return new SuccessReport(true, String.format("\u043f\u0440\u043e\u0441\u0442\u0438, <persik>, \u043d\u043e \u0442\u0432\u043e\u0439 \u043a\u0443\u043b\u0434\u0430\u0443\u043d \u043d\u0430 \u044d\u0442\u043e \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0435 \u0435\u0449\u0435 \u043d\u0435 \u043f\u0440\u043e\u0448\u0435\u043b :( \u043e\u0441\u0442\u0430\u043b\u043e\u0441\u044c \u0432\u0441\u0435\u0433\u043e \u043b\u0438\u0448\u044c #c2%d#c3 \u0441\u0435\u043a\u0443\u043d\u0434", this.context.alisa.cooldownsHandler.votedayPersonalCooldowns.getSecondsLeft(player)));
        }
        this.context.alisa.voteHandler.startVoteday(player);
        return new SuccessReport(false, "");
    }
    
    private void startVotesun(final Player player) {
        final int duration = this.context.config.getInt("duration.votesun");
        final float successRatio = this.context.config.getFloat("success-ratio.votesun");
        final int successAdvantage = this.context.config.getInt("success-advantage.votesun");
        this.currentVote = new VoteSun(duration, successRatio, successAdvantage, this, player);
        this.context.alisa.cooldownsHandler.votesunGlobalCooldown.trigger();
        this.context.alisa.cooldownsHandler.votesunPersonalCooldowns.trigger(player);
        final Statistics statistics = this.context.alisa.statistics;
        ++statistics.totalVotesStarted;
    }
    
    private void startVoteday(final Player player) {
        final int duration = this.context.config.getInt("duration.voteday");
        final float successRatio = this.context.config.getFloat("success-ratio.voteday");
        final int successAdvantage = this.context.config.getInt("success-advantage.voteday");
        this.currentVote = (Vote)new VoteDay(duration, successRatio, successAdvantage, this, player);
        this.context.alisa.cooldownsHandler.votedayGlobalCooldown.trigger();
        this.context.alisa.cooldownsHandler.votedayPersonalCooldowns.trigger(player);
        final Statistics statistics = this.context.alisa.statistics;
        ++statistics.totalVotesStarted;
    }
}
