//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.configuration.serialization.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import java.util.*;

@SerializableAs("ModeratorsEntry")
public class ModeratorsEntry implements Comparable<ModeratorsEntry>, ConfigurationSerializable
{
    public String groupName;
    public String prefixColor;
    public String playernameColor;
    public ArrayList<String> playerNames;
    public int ID;
    
    @Override
    public int compareTo(final ModeratorsEntry o) {
        return o.ID - this.ID;
    }
    
    protected ModeratorsEntry(final int ID, final String groupName, final String prefixColor, final String playernameColor) {
        this.playerNames = new ArrayList<String>();
        this.ID = ID;
        this.groupName = groupName;
        this.prefixColor = prefixColor;
        this.playernameColor = playernameColor;
    }
    
    protected ModeratorsEntry(final int ID, final String groupName, final ArrayList<String> playerNames, final String prefixColor, final String playernameColor) {
        this.playerNames = new ArrayList<String>();
        this.ID = ID;
        this.groupName = groupName;
        this.prefixColor = prefixColor;
        this.playernameColor = playernameColor;
        this.playerNames = playerNames;
    }
    
    public String getModListString() {
        return String.format("[%d] %s[%s%s%s]#c3: %s", this.ID, ChatColor.GRAY, '§' + this.prefixColor, this.groupName, ChatColor.GRAY, this.getPlayerListString());
    }
    
    private String getPlayerListString() {
        final ArrayList<String> coloredMods = new ArrayList<String>();
        for (final String name : this.playerNames) {
            coloredMods.add(String.format("%s%s#c3", '§' + this.playernameColor, name));
        }
        return String.join(", ", coloredMods);
    }
    
    protected SuccessReport addPlayerByName(final String playerName) {
        if (!this.hasPlayer(playerName)) {
            this.playerNames.add(playerName);
            return new SuccessReport(true, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0438\u0433\u0440\u043e\u043a #c2%s#c3 \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d \u0432 \u0433\u0440\u0443\u043f\u043f\u0443 #c2%s", playerName, this.groupName));
        }
        return new SuccessReport(false, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u0433\u0440\u043e\u043a #c2%s#c3 \u0443\u0436\u0435 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0433\u0440\u0443\u043f\u043f\u0435 #c2%s", playerName, this.groupName));
    }
    
    protected SuccessReport removePlayerByName(final String playerName) {
        if (this.hasPlayer(playerName)) {
            this.removeIgnoreCase(playerName, this.playerNames);
            return new SuccessReport(true, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0438\u0433\u0440\u043e\u043a #c2%s#c3 \u0443\u0434\u0430\u043b\u0435\u043d \u0438\u0437 \u0433\u0440\u0443\u043f\u043f\u044b #c2%s", playerName, this.groupName));
        }
        return new SuccessReport(false, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u0433\u0440\u043e\u043a #c2%s#c3 \u043d\u0435 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0433\u0440\u0443\u043f\u043f\u0435 #c2%s", playerName, this.groupName));
    }
    
    private void removeIgnoreCase(final String playerName, final ArrayList<String> arr) {
        String toRemove = null;
        for (final String s : arr) {
            if (s.equalsIgnoreCase(playerName)) {
                toRemove = s;
                break;
            }
        }
        if (toRemove != null) {
            arr.remove(toRemove);
        }
    }
    
    protected boolean hasPlayer(final String playerName) {
        return mf.arrayListContainsIgnoreCase((ArrayList)this.playerNames, playerName);
    }
    
    protected String getColoredGroupName() {
        return String.format("%s%s%s", '§' + this.prefixColor, this.groupName, ChatColor.RESET);
    }
    
    protected String getFormattedGroupName() {
        return String.format("%s[%s%s%s]", ChatColor.DARK_GRAY, '§' + this.prefixColor, this.groupName, ChatColor.DARK_GRAY);
    }
    
    protected String getOnlinePlayersString() {
        final StringBuilder sb = new StringBuilder();
        final ArrayList<String> responses = new ArrayList<String>();
        for (int i = 0; i < this.playerNames.size(); ++i) {
            if (!shyAlisa.getInstance().alisa.moderatorsHandler.isModeratorHidden(this.playerNames.get(i)) && this.isPlayerNameOnline(this.playerNames.get(i))) {
                responses.add(String.format("#cg\u043e\u043d\u043b\u0430\u0439\u043d %s>> %s%s %s", ChatColor.GRAY, this.getFormattedGroupName(), '§' + this.playernameColor, this.playerNames.get(i)));
            }
        }
        sb.append(String.join("\n", responses));
        return sb.toString();
    }
    
    private boolean isPlayerNameOnline(final String playerName) {
        final Player p;
        return (p = Bukkit.getServer().getPlayer(playerName)) != null && p.isOnline();
    }
    
    public Map<String, Object> serialize() {
        final LinkedHashMap result = new LinkedHashMap();
        result.put("groupName", this.groupName);
        result.put("prefixColor", this.prefixColor);
        result.put("playernameColor", this.playernameColor);
        result.put("ID", this.ID);
        result.put("playerNames", this.playerNames);
        return (Map<String, Object>)result;
    }
    
    public static ModeratorsEntry deserialize(final Map<String, Object> args) {
        String groupName = "";
        String prefixColor = "";
        String playernameColor = "";
        ArrayList<String> playerNames = new ArrayList<String>();
        int ID = 0;
        if (args.containsKey("groupName")) {
            groupName = args.get("groupName");
        }
        if (args.containsKey("prefixColor")) {
            prefixColor = args.get("prefixColor");
        }
        if (args.containsKey("playernameColor")) {
            playernameColor = args.get("playernameColor");
        }
        if (args.containsKey("playerNames")) {
            playerNames = args.get("playerNames");
        }
        if (args.containsKey("ID")) {
            ID = args.get("ID");
        }
        return new ModeratorsEntry(ID, groupName, playerNames, prefixColor, playernameColor);
    }
}
