//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;
import net.md_5.bungee.api.chat.*;

public class VoteSun extends Vote
{
    protected VoteSun(final int durationSeconds, final float successRatio, final int successAdvantage, final VoteHandler voteHandler, final Player starter) {
        super(durationSeconds, successRatio, successAdvantage, voteHandler, starter);
    }
    
    void start() {
        this.voteHandler.context.alisa.sayRaw(new TextComponent[] { this.voteHandler.context.alisa.textComponent(String.format("#c3%s#c1 \u043d\u0430\u0447\u0430\u043b \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0435 \u0437\u0430 #c2\u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u0438\u0435 \u0434\u043e\u0436\u0434\u044f#c1", this.playerStarter.getName())) });
        this.voteHandler.context.alisa.sayRawDelayed(new TextComponent[] { this.voteHandler.context.alisa.textComponent("#c1\u0413\u043e\u043b\u043e\u0441\u0443\u0439: "), this.voteHandler.context.alisa.clickComponent("#cg\u043e\u0442\u043a\u043b\u044e\u0447\u0438\u0442\u044c", "/yes"), this.voteHandler.context.alisa.textComponent(" "), this.voteHandler.context.alisa.clickComponent("#cr\u043e\u0441\u0442\u0430\u0432\u0438\u0442\u044c", "/no") });
        super.start0();
    }
    
    void finish() {
        final int FOR = this.votedFor.size();
        final int AGAINST = this.votedAgainst.size();
        boolean b = false;
        Label_0054: {
            Label_0053: {
                if (FOR - AGAINST >= this.successAdvantage) {
                    if (AGAINST != 0) {
                        if (FOR / (FOR + (float)AGAINST) < this.successRatio) {
                            break Label_0053;
                        }
                    }
                    b = true;
                    break Label_0054;
                }
            }
            b = false;
        }
        final boolean succeeded = b;
        final String append = succeeded ? "\u0414\u043e\u0436\u0434\u044c #cg\u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d" : "\u0414\u043e\u0436\u0434\u044c #cr\u043d\u0435 \u0432\u044b\u043a\u043b\u044e\u0447\u0435\u043d";
        this.voteHandler.context.alisa.say(String.format("\u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0435 \u043e\u043a\u043e\u043d\u0447\u0435\u043d\u043e: #cg\"\u0437\u0430\"#c1 - #cg%d#c1, #cr\"\u043f\u0440\u043e\u0442\u0438\u0432\"#c1 - #cr%d#c1. %s", FOR, AGAINST, append));
        if (succeeded) {
            this.voteHandler.context.getMainWorld().setStorm(false);
            final Statistics statistics = this.voteHandler.context.alisa.statistics;
            ++statistics.successfulVotes;
        }
        this.voteHandler.currentVote = null;
    }
}
