//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import java.util.*;
import me.asgmax.shyAlisa.checkers.*;

public class Settings
{
    private shyAlisa context;
    private HashMap<String, IArgumentChecker> mcps;
    
    public Settings(final shyAlisa context) {
        this.mcps = new HashMap<String, IArgumentChecker>();
        this.context = context;
        this.registerMCPs();
    }
    
    protected void handleSetCommand(final Player sender, final Command cmd, final String[] args0) {
        final ArrayList<String> providedArgs = new ArrayList<String>(Arrays.asList(args0));
        providedArgs.remove(0);
        if (providedArgs.size() > 0) {
            final String propertyName = providedArgs.get(0).toLowerCase();
            providedArgs.remove(0);
            if (this.mcps.containsKey(propertyName)) {
                final IArgumentChecker requiredArg = this.mcps.get(propertyName);
                if (providedArgs.size() > 0) {
                    if (this.checkArg(requiredArg, providedArgs)) {
                        final Object resultingArg = requiredArg.getFixedClassedArg((ArrayList)providedArgs);
                        this.setConfigPropertyWithReport(propertyName, resultingArg, requiredArg, sender);
                    }
                    else {
                        this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u043e \u0432\u0432\u0435\u0434\u0435\u043d(\u044b) \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442(\u044b) \u043a \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0435 #c2%s#c3", propertyName));
                    }
                }
                else {
                    this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0435 #c2%s#c3", propertyName));
                }
            }
            else {
                this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438 #c2%s#c3 \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442", propertyName));
            }
        }
        else {
            this.context.alisa.whisper(sender, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u043e \u0432\u0432\u0435\u0434\u0435\u043d(\u044b) \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442(\u044b) \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435");
        }
    }
    
    protected void handleReadCommand(final Player sender, final Command cmd, final String[] args) {
        if (args.length >= 2) {
            if (this.context.config.exists(args[1])) {
                final String result = this.context.config.getString(args[1]);
                this.context.alisa.whisper(sender, String.format("\u0422\u0435\u043a\u0443\u0449\u0435\u0435 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435 \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438 #c2%s#c3: #c2%s", args[1], result));
            }
            else {
                this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0438 #c2%s#c3 \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442", args[1]));
            }
        }
        else {
            this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435", new Object[0]));
        }
    }
    
    protected void handleModsCommand(final Player sender, final Command cmd, final String[] args0) {
        final ArrayList<String> providedArgs = new ArrayList<String>(Arrays.asList(args0));
        providedArgs.remove(0);
        if (providedArgs.size() >= 1) {
            if (providedArgs.get(0).equalsIgnoreCase("add")) {
                providedArgs.remove(0);
                this.handleModsAddCommand(sender, providedArgs);
            }
            else if (providedArgs.get(0).equalsIgnoreCase("remove")) {
                providedArgs.remove(0);
                this.handleModsRemoveCommand(sender, providedArgs);
            }
            else if (providedArgs.get(0).equalsIgnoreCase("list")) {
                this.context.alisa.whisper(sender, this.context.alisa.moderatorsHandler.getAllModsListString());
            }
            else if (providedArgs.get(0).equalsIgnoreCase("creategroup")) {
                providedArgs.remove(0);
                this.handleModsCreateGroupCommand(sender, providedArgs);
            }
            else if (providedArgs.get(0).equalsIgnoreCase("removegroup")) {
                providedArgs.remove(0);
                this.handleModsRemoveGroupCommand(sender, providedArgs);
            }
            else if (providedArgs.get(0).equalsIgnoreCase("editgroup")) {
                providedArgs.remove(0);
                this.handleEditGroupCommand(sender, providedArgs);
            }
            else {
                this.context.alisa.whisper(sender, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0442\u0430\u043a\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u044b \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u0435\u0442");
            }
        }
        else {
            this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435", new Object[0]));
        }
    }
    
    private void handleEditGroupCommand(final Player sender, final ArrayList<String> providedArgs) {
        if (providedArgs.size() >= 4) {
            final String newName = providedArgs.get(1).replace("_", " ");
            final Integer ID;
            if ((ID = this.getIntFromString(providedArgs.get(0))) != null) {
                final SuccessReport sr = this.context.alisa.moderatorsHandler.editGroup((int)ID, newName, (String)providedArgs.get(2), (String)providedArgs.get(3));
                this.context.alisa.whisper(sender, sr.message);
            }
            else {
                this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: #c2%s#c3 \u043d\u0435 \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u043a\u043e\u0440\u0440\u0435\u043a\u0442\u043d\u044b\u043c ID \u0433\u0440\u0443\u043f\u043f\u044b", providedArgs.get(0)));
            }
        }
        else {
            this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435", new Object[0]));
        }
    }
    
    private void handleModsRemoveGroupCommand(final Player sender, final ArrayList<String> providedArgs) {
        if (providedArgs.size() >= 1) {
            final Integer ID;
            if ((ID = this.getIntFromString(providedArgs.get(0))) != null) {
                final SuccessReport sr = this.context.alisa.moderatorsHandler.removeModsGroup((int)ID);
                this.context.alisa.whisper(sender, sr.message);
            }
            else {
                this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: #c2%s#c3 \u043d\u0435 \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u043a\u043e\u0440\u0440\u0435\u043a\u0442\u043d\u044b\u043c ID \u0434\u043b\u044f \u0433\u0440\u0443\u043f\u043f\u044b", providedArgs.get(0)));
            }
        }
        else {
            this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435", new Object[0]));
        }
    }
    
    private void handleModsCreateGroupCommand(final Player sender, final ArrayList<String> providedArgs) {
        if (providedArgs.size() >= 2) {
            final String groupName = providedArgs.get(1).replace("_", " ");
            String prefixColor;
            String nameColor;
            if (providedArgs.size() >= 4) {
                prefixColor = providedArgs.get(2);
                nameColor = providedArgs.get(3);
            }
            else {
                prefixColor = "f";
                nameColor = "f";
            }
            final Integer ID;
            if ((ID = this.getIntFromString(providedArgs.get(0))) != null) {
                final SuccessReport sr = this.context.alisa.moderatorsHandler.addModsGroup(groupName, (int)ID, prefixColor, nameColor);
                this.context.alisa.whisper(sender, sr.message);
            }
            else {
                this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: #c2%s#c3 \u043d\u0435 \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u043a\u043e\u0440\u0440\u0435\u043a\u0442\u043d\u044b\u043c ID \u0434\u043b\u044f \u0433\u0440\u0443\u043f\u043f\u044b", providedArgs.get(0)));
            }
        }
        else {
            this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435", new Object[0]));
        }
    }
    
    private void handleModsRemoveCommand(final Player sender, final ArrayList<String> providedArgs) {
        if (providedArgs.size() >= 1) {
            final SuccessReport sr = this.context.alisa.moderatorsHandler.removePlayerFromAllGroups((String)providedArgs.get(0));
            this.context.alisa.whisper(sender, sr.message);
        }
        else {
            this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435", new Object[0]));
        }
    }
    
    private void handleModsAddCommand(final Player sender, final ArrayList<String> providedArgs) {
        if (providedArgs.size() >= 2) {
            final Integer ID;
            if ((ID = this.getIntFromString(providedArgs.get(0))) != null) {
                final SuccessReport sr = this.context.alisa.moderatorsHandler.addPlayerToGroup((int)ID, (String)providedArgs.get(1));
                this.context.alisa.whisper(sender, sr.message);
            }
            else {
                this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: #c2%s#c3 \u043d\u0435 \u044f\u0432\u043b\u044f\u0435\u0442\u0441\u044f \u043a\u043e\u0440\u0440\u0435\u043a\u0442\u043d\u044b\u043c ID \u0433\u0440\u0443\u043f\u043f\u044b", providedArgs.get(0)));
            }
        }
        else {
            this.context.alisa.whisper(sender, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435", new Object[0]));
        }
    }
    
    private void setConfigPropertyWithReport(final String key, final Object value, final IArgumentChecker requiredArg, final Player player) {
        this.setConfigProperty(key, value, requiredArg);
        this.context.alisa.whisper(player, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u043d\u0430\u0441\u0442\u0440\u043e\u0439\u043a\u0430 #c2%s#c3 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0430 \u0432 \u0437\u043d\u0430\u0447\u0435\u043d\u0438\u0435 #c2%s", key, value));
    }
    
    private void setConfigProperty(final String key, final Object value, final IArgumentChecker requiredArg) {
        this.context.config.set(key, value);
    }
    
    private boolean checkArg(final IArgumentChecker requiredArg, final ArrayList<String> providedArgs) {
        return requiredArg.check((ArrayList)providedArgs);
    }
    
    private void registerMCPs() {
        this.registerMCP("tempmute.flood", (IArgumentChecker)new checkInt(1, 9000));
        this.registerMCP("tempmute.symbol-flood", (IArgumentChecker)new checkInt(1, 9000));
        this.registerMCP("tempmute.advertisement", (IArgumentChecker)new checkInt(1, 9000));
        this.registerMCP("tempmute.caps", (IArgumentChecker)new checkInt(1, 9000));
        this.registerMCP("tempmute.profanity", (IArgumentChecker)new checkInt(1, 9000));
        this.registerMCP("tempmute.double-warn", (IArgumentChecker)new checkInt(1, 9000));
        this.registerMCP("cooldown.votesun-global", (IArgumentChecker)new checkInt(1, 90000));
        this.registerMCP("cooldown.votesun-personal", (IArgumentChecker)new checkInt(1, 90000));
        this.registerMCP("cooldown.voteday-global", (IArgumentChecker)new checkInt(1, 90000));
        this.registerMCP("cooldown.voteday-personal", (IArgumentChecker)new checkInt(1, 90000));
        this.registerMCP("success-ratio.votesun", (IArgumentChecker)new checkFloat(1.0f, 90000.0f));
        this.registerMCP("success-ratio.voteday", (IArgumentChecker)new checkFloat(1.0f, 90000.0f));
        this.registerMCP("success-advantage.voteday", (IArgumentChecker)new checkInt(1, 90000));
        this.registerMCP("success-advantage.votesun", (IArgumentChecker)new checkInt(1, 90000));
        this.registerMCP("chat-colors", (IArgumentChecker)new checkStringArrayFixedSize(3));
        this.registerMCP("name1", (IArgumentChecker)new checkString());
        this.registerMCP("name2", (IArgumentChecker)new checkString());
        this.registerMCP("name-color", (IArgumentChecker)new checkString());
        this.registerMCP("prefix", (IArgumentChecker)new checkString());
        this.registerMCP("prefix-color", (IArgumentChecker)new checkString());
        this.registerMCP("silent", (IArgumentChecker)new checkBoolean());
        this.registerMCP("main-world", (IArgumentChecker)new checkString());
        this.registerMCP("debug", (IArgumentChecker)new checkBoolean());
    }
    
    protected void registerMCP(final String propertyKey, final IArgumentChecker argumentChecker) {
        this.mcps.put(propertyKey, argumentChecker);
    }
    
    private Integer getIntFromString(final String s) {
        Integer result;
        try {
            result = Integer.parseInt(s);
        }
        catch (Exception e) {
            return null;
        }
        return result;
    }
}
