//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class ModeratorsHandler
{
    shyAlisa context;
    public ArrayList<ModeratorsEntry> groups;
    protected ArrayList<Player> hiddenModerators;
    
    public ModeratorsHandler(final shyAlisa context) {
        this.groups = new ArrayList<ModeratorsEntry>();
        this.hiddenModerators = new ArrayList<Player>();
        this.context = context;
        this.loadModeratorsConfig();
    }
    
    protected SuccessReport createGroup(final ModeratorsEntry moderatorsEntry) {
        this.groups.add(moderatorsEntry);
        Collections.sort(this.groups);
        this.saveModeratorsConfig();
        return new SuccessReport(true, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0441\u043e\u0437\u0434\u0430\u043d\u0430 \u0433\u0440\u0443\u043f\u043f\u0430 #c2%s#c3 (ID #c2%d#c3)", moderatorsEntry.groupName, moderatorsEntry.ID));
    }
    
    protected String getAllModsListString() {
        final StringBuilder sb = new StringBuilder("#c2\u0421\u043f\u0438\u0441\u043e\u043a \u043c\u043e\u0434\u0435\u0440\u0430\u0442\u043e\u0440\u043e\u0432 \u043f\u043e \u0433\u0440\u0443\u043f\u043f\u0430\u043c#c3:\n");
        final ArrayList<String> responses = new ArrayList<String>();
        for (int i = 0; i < this.groups.size(); ++i) {
            responses.add(this.groups.get(i).getModListString());
        }
        sb.append(String.join("\n", responses));
        return sb.toString();
    }
    
    protected SuccessReport addPlayerToGroup(final int ID, final String playerName0) {
        final OfflinePlayer op = Bukkit.getOfflinePlayer(playerName0);
        if (op == null || op.getName() == null) {
            return new SuccessReport(false, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u0433\u0440\u043e\u043a \u0441 \u0438\u043c\u0435\u043d\u0435\u043c #c2%s#c3 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d", playerName0));
        }
        final String playerName = op.getName();
        this.removePlayerFromAllGroupsExceptOneSilent(playerName, ID);
        final ModeratorsEntry group;
        if ((group = this.getGroupById(ID)) != null) {
            final SuccessReport sr = group.addPlayerByName(playerName);
            this.saveModeratorsConfig();
            return sr;
        }
        return new SuccessReport(false, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0433\u0440\u0443\u043f\u043f\u0430 \u0441 ID #c2%d#c3 \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442", ID));
    }
    
    protected SuccessReport removePlayerFromAllGroups(final String playername) {
        int removedGroupsCount = 0;
        for (final ModeratorsEntry group : this.groups) {
            final SuccessReport sr = group.removePlayerByName(playername);
            if (sr.success) {
                ++removedGroupsCount;
            }
        }
        if (removedGroupsCount > 0) {
            this.saveModeratorsConfig();
            return new SuccessReport(true, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0438\u0433\u0440\u043e\u043a #c2%s#c3 \u0443\u0434\u0430\u043b\u0435\u043d \u0438\u0437 \u0441\u043f\u0438\u0441\u043a\u0430 \u043c\u043e\u0434\u0435\u0440\u0430\u0442\u043e\u0440\u043e\u0432", playername));
        }
        return new SuccessReport(true, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u0433\u0440\u043e\u043a #c2%s#c3 \u043d\u0435 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0441\u043f\u0438\u0441\u043a\u0430\u0445 \u043c\u043e\u0434\u0435\u0440\u0430\u0442\u043e\u0440\u043e\u0432", playername));
    }
    
    private void removePlayerFromAllGroupsExceptOneSilent(final String playername, final int exceptionGroupID) {
        for (final ModeratorsEntry group : this.groups) {
            if (group.ID != exceptionGroupID) {
                group.removePlayerByName(playername);
            }
        }
    }
    
    private ModeratorsEntry getGroupById(final int ID) {
        for (final ModeratorsEntry me : this.groups) {
            if (me.ID == ID) {
                return me;
            }
        }
        return null;
    }
    
    protected String getOnlineModsString() {
        final StringBuilder sb = new StringBuilder("#c2\u0421\u043f\u0438\u0441\u043e\u043a \u043c\u043e\u0434\u0435\u0440\u0430\u0442\u043e\u0440\u043e\u0432 \u043e\u043d\u043b\u0430\u0439\u043d#c3:\n");
        final ArrayList<String> responses = new ArrayList<String>();
        for (int i = 0; i < this.groups.size(); ++i) {
            responses.add(this.groups.get(i).getOnlinePlayersString());
        }
        sb.append(String.join("\n", responses));
        return sb.toString();
    }
    
    protected SuccessReport addModsGroup(final String groupName, final int ID, final String prefixColor, final String nameColor) {
        if (this.getGroupById(ID) == null && !this.groupWithThisNameExists(groupName)) {
            final ModeratorsEntry me = new ModeratorsEntry(ID, groupName, prefixColor, nameColor);
            return this.createGroup(me);
        }
        return new SuccessReport(false, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0433\u0440\u0443\u043f\u043f\u0430 \u0441 \u0438\u043c\u0435\u043d\u0435\u043c #c2%s#c3 \u0438\u043b\u0438 ID #c2%s#c3 \u0443\u0436\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442", groupName, ID));
    }
    
    private boolean groupWithThisNameExists(final String name) {
        for (final ModeratorsEntry me : this.groups) {
            if (me.groupName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    
    protected SuccessReport removeModsGroup(final int ID) {
        final ModeratorsEntry toRemove;
        if ((toRemove = this.getGroupById(ID)) != null) {
            this.groups.remove(toRemove);
            this.saveModeratorsConfig();
            return new SuccessReport(true, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0433\u0440\u0443\u043f\u043f\u0430 \u0441 ID #c2%d#c3 (#c2%s#c3) \u0431\u044b\u043b\u0430 \u0443\u0434\u0430\u043b\u0435\u043d\u0430", ID, toRemove.groupName));
        }
        return new SuccessReport(true, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0433\u0440\u0443\u043f\u043f\u044b \u0441 ID #c2%d#c3 \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442", ID));
    }
    
    protected SuccessReport editGroup(final int ID, final String newName, final String newPrefixColor, final String newNameColor) {
        final ModeratorsEntry me;
        if ((me = this.getGroupById(ID)) != null) {
            me.groupName = newName;
            me.prefixColor = newPrefixColor;
            me.playernameColor = newNameColor;
            this.saveModeratorsConfig();
            Collections.sort(this.groups);
            return new SuccessReport(true, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0433\u0440\u0443\u043f\u043f\u0430 \u0441 ID #c2%d#c3 (#c2%s#c3) \u0431\u044b\u043b\u0430 \u043e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0430", ID, me.groupName));
        }
        return new SuccessReport(false, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0433\u0440\u0443\u043f\u043f\u044b \u0441 ID #c2%d#c3 \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442", ID));
    }
    
    protected void saveModeratorsConfig() {
        this.context.config.set("moderators", (Object)this.groups);
    }
    
    protected void loadModeratorsConfig() {
        this.groups = (ArrayList<ModeratorsEntry>)this.context.getConfig().getList("moderators", (List)new ArrayList());
    }
    
    protected boolean isModerator(final Player p) {
        for (final ModeratorsEntry me : this.groups) {
            for (final String name : me.playerNames) {
                if (name.equalsIgnoreCase(p.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected void toggleDetect(final Player player) {
        if (this.isModerator(player)) {
            if (this.hiddenModerators.contains(player)) {
                this.hiddenModerators.remove(player);
                this.context.alisa.whisper(player, "#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0442\u0435\u043f\u0435\u0440\u044c \u0442\u0435\u0431\u044f #cg\u0432\u0438\u0434\u043d\u043e#c3 \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 \u043e\u043d\u043b\u0430\u0439\u043d \u043c\u043e\u0434\u0435\u0440\u0430\u0442\u043e\u0440\u043e\u0432 (\u0434\u043e \u0440\u0435\u0441\u0442\u0430\u0440\u0442\u0430)");
            }
            else {
                this.hiddenModerators.add(player);
                this.context.alisa.whisper(player, "#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0442\u0435\u043f\u0435\u0440\u044c \u0442\u0435\u0431\u044f #cr\u043d\u0435 \u0432\u0438\u0434\u043d\u043e#c3 \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 \u043e\u043d\u043b\u0430\u0439\u043d \u043c\u043e\u0434\u0435\u0440\u0430\u0442\u043e\u0440\u043e\u0432 (\u0434\u043e \u0440\u0435\u0441\u0442\u0430\u0440\u0442\u0430)");
            }
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0442\u0435\u0431\u044f \u043d\u0435\u0442 \u0432 \u0441\u043f\u0438\u0441\u043a\u0430\u0445 \u043c\u043e\u0434\u0435\u0440\u0430\u0442\u043e\u0440\u043e\u0432");
        }
    }
    
    protected boolean isModeratorHidden(final String name) {
        for (final Player p : this.hiddenModerators) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean isModerator(final String playerName) {
        for (final ModeratorsEntry me : this.groups) {
            for (final String name : me.playerNames) {
                if (name.equalsIgnoreCase(playerName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
