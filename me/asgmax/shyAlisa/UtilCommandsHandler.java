//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import java.net.*;
import java.text.*;
import java.util.*;
import org.bukkit.*;

public class UtilCommandsHandler
{
    protected shyAlisa context;
    protected ArrayList<String> toSpawnPlayerNames;
    protected String updateInfoURL;
    protected String pluginFilename;
    protected ArrayList<String> allowedFiles;
    private ArrayList<String> alisaHelpCommandStrings;
    
    protected UtilCommandsHandler(final shyAlisa context) {
        this.updateInfoURL = "https://pastebin.com/raw/JZBb7Fg5";
        this.pluginFilename = "shyAlisa.jar";
        this.context = context;
        this.toSpawnPlayerNames = (ArrayList<String>)context.config.getList("tospawn-playernames");
        this.allowedFiles = (ArrayList<String>)mf.readProjectFileLines("files-allowed-to-download.txt");
        this.alisaHelpCommandStrings = (ArrayList<String>)mf.readProjectFileLines("command-alisa-help.txt");
    }
    
    protected void handleGetNameCommand(final Player player, final Command cmd, final String[] args) {
        if (args.length >= 2) {
            final String targetString = args[1];
            OfflinePlayer op = null;
            try {
                final UUID uuid = UUID.fromString(targetString);
                op = Bukkit.getOfflinePlayer(uuid);
            }
            catch (Exception e) {
                this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0432\u0435\u0440\u043d\u044b\u0439 UUID");
                return;
            }
            if (op != null) {
                this.context.alisa.whisper(player, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0438\u0433\u0440\u043e\u043a \u0441 \u044d\u0442\u0438\u043c UUID: #c2%s", op.getName()));
            }
            else {
                this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u0433\u0440\u043e\u043a \u0441 \u044d\u0442\u0438\u043c UUID \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d");
            }
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435");
        }
    }
    
    protected void handleGetUUIDCommand(final Player player, final Command cmd, final String[] args) {
        if (args.length >= 2) {
            final String targetName = args[1];
            final OfflinePlayer op = Bukkit.getOfflinePlayer(targetName);
            if (op != null) {
                this.context.alisa.whisper(player, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: UUID \u0438\u0433\u0440\u043e\u043a\u0430 %s: #c2%s", op.getName(), op.getUniqueId().toString()));
            }
            else {
                this.context.alisa.whisper(player, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u0433\u0440\u043e\u043a \u0441 \u0438\u043c\u0435\u043d\u0435\u043c #c2%s#c3 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d (\u0438\u043c\u044f \u0447\u0443\u0432\u0441\u0442\u0432\u0438\u0442\u0435\u043b\u044c\u043d\u043e \u043a \u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0443)", targetName));
            }
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435");
        }
    }
    
    protected void handleToSpawnCommand(final Player player, final Command cmd, final String[] args) {
        if (args.length >= 2) {
            final String targetName = args[1];
            if (!mf.arrayListContainsIgnoreCase((ArrayList)this.toSpawnPlayerNames, targetName)) {
                final OfflinePlayer op = Bukkit.getOfflinePlayer(targetName);
                if (op != null) {
                    this.toSpawnPlayerNames.add(targetName);
                    this.context.config.set("tospawn-playernames", (Object)this.toSpawnPlayerNames);
                    this.context.alisa.whisper(player, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0438\u0433\u0440\u043e\u043a #c2%s#c3 \u0431\u0443\u0434\u0435\u0442 \u043e\u0442\u043f\u0440\u0430\u0432\u043b\u0435\u043d \u043d\u0430 \u0441\u043f\u0430\u0432\u043d \u043f\u0440\u0438 \u0441\u043b\u0435\u0434\u0443\u044e\u0449\u0435\u043c \u0437\u0430\u0445\u043e\u0434\u0435 \u0432 \u0438\u0433\u0440\u0443", op.getName()));
                }
                else {
                    this.context.alisa.whisper(player, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u0433\u0440\u043e\u043a #c2%s#c3 \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d (\u0438\u043c\u044f \u0447\u0443\u0432\u0441\u0442\u0432\u0438\u0442\u0435\u043b\u044c\u043d\u043e \u043a \u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0443)", targetName));
                }
            }
            else {
                this.context.alisa.whisper(player, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u0433\u0440\u043e\u043a #c2%s#c3 \u0443\u0436\u0435 \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0441\u043f\u0438\u0441\u043a\u0435 \u043d\u0430 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0430\u0446\u0438\u044e", targetName));
            }
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435");
        }
    }
    
    protected void handleCheckForUpdateCommand(final Player player) {
        final Update update = mf.getLatestPluginUpdateObject();
        if (update == null) {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u0438 \u043d\u043e\u043c\u0435\u0440\u0430 \u0438 \u0441\u0441\u044b\u043b\u043a\u0438 \u043f\u043e\u0441\u043b\u0435\u0434\u043d\u0435\u0439 \u0432\u0435\u0440\u0441\u0438\u0438 \u043e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u044f");
            return;
        }
        final String currentVersion = this.context.getDescription().getVersion();
        final String remoteVersion = update.version;
        String answer = "\u0442\u0435\u043a\u0443\u0449\u0430\u044f \u0432\u0435\u0440\u0441\u0438\u044f: #c2%s#c3, \u043f\u043e\u0441\u043b\u0435\u0434\u043d\u044f\u044f \u0432\u0435\u0440\u0441\u0438\u044f: #c2%s#c3";
        if (mf.versionCompare(currentVersion, remoteVersion) < 0) {
            answer += ". \u0414\u043b\u044f \u043e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u044f \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u0443 #c2/alisa getupdate";
        }
        this.context.alisa.whisper(player, String.format(answer, currentVersion, remoteVersion));
    }
    
    protected void handleGetUpdateCommand(final Player player) {
        final Update update = mf.getLatestPluginUpdateObject();
        if (update == null) {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u0438 \u043d\u043e\u043c\u0435\u0440\u0430 \u0438 \u0441\u0441\u044b\u043b\u043a\u0438 \u043f\u043e\u0441\u043b\u0435\u0434\u043d\u0435\u0439 \u0432\u0435\u0440\u0441\u0438\u0438 \u043e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u044f");
            return;
        }
        final String currentVersion = this.context.getDescription().getVersion();
        final String remoteVersion = update.version;
        if (mf.versionCompare(currentVersion, remoteVersion) < 0) {
            this.context.alisa.whisper(player, "\u043d\u043e\u0432\u0430\u044f \u0432\u0435\u0440\u0441\u0438\u044f \u043e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d\u0430, \u043d\u0430\u0447\u0438\u043d\u0430\u0435\u0442\u0441\u044f \u0441\u043a\u0430\u0447\u0438\u0432\u0430\u043d\u0438\u0435");
            if (mf.downloadFileToUpdateFolder(update.link, this.pluginFilename)) {
                this.context.alisa.whisper(player, "#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u043d\u043e\u0432\u0430\u044f \u0432\u0435\u0440\u0441\u0438\u044f \u0441\u043a\u0430\u0447\u0430\u043d\u0430 \u0438 \u0431\u0443\u0434\u0435\u0442 \u043e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0430 \u0432\u043e \u0432\u0440\u0435\u043c\u044f \u0440\u0435\u0441\u0442\u0430\u0440\u0442\u0430");
            }
            else {
                this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0441\u043a\u0430\u0447\u0438\u0432\u0430\u043d\u0438\u0435 \u043d\u043e\u0432\u043e\u0439 \u0432\u0435\u0440\u0441\u0438\u0438 \u043d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c");
            }
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043f\u043e\u0441\u043b\u0435\u0434\u043d\u044f\u044f \u0432\u0435\u0440\u0441\u0438\u044f \u043f\u043b\u0430\u0433\u0438\u043d\u0430 \u0443\u0436\u0435 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0430");
        }
    }
    
    protected void handleForceUpdateCommand(final Player player) {
        final Update update = mf.getLatestPluginUpdateObject();
        if (update == null) {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u0438 \u043d\u043e\u043c\u0435\u0440\u0430 \u0438 \u0441\u0441\u044b\u043b\u043a\u0438 \u043f\u043e\u0441\u043b\u0435\u0434\u043d\u0435\u0439 \u0432\u0435\u0440\u0441\u0438\u0438 \u043e\u0431\u043d\u043e\u0432\u043b\u0435\u043d\u0438\u044f");
            return;
        }
        this.context.alisa.whisper(player, "\u043d\u0430\u0447\u0438\u043d\u0430\u0435\u0442\u0441\u044f \u0441\u043a\u0430\u0447\u0438\u0432\u0430\u043d\u0438\u0435");
        if (mf.downloadFileToUpdateFolder(update.link, this.pluginFilename)) {
            this.context.alisa.whisper(player, "#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u043f\u043b\u0430\u0433\u0438\u043d \u043f\u0435\u0440\u0435\u043a\u0430\u0447\u0430\u043d \u0438 \u0431\u0443\u0434\u0435\u0442 \u043e\u0431\u043d\u043e\u0432\u043b\u0435\u043d \u0432\u043e \u0432\u0440\u0435\u043c\u044f \u0440\u0435\u0441\u0442\u0430\u0440\u0442\u0430");
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0441\u043a\u0430\u0447\u0438\u0432\u0430\u043d\u0438\u0435 \u043d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c");
        }
    }
    
    protected void handleGetFileCommand(final Player player, final String[] args) {
        if (args.length >= 3) {
            final String filename = args[2];
            final String url = args[1];
            if (this.isFileNameAllowed(filename)) {
                if (this.isValidUrl(url)) {
                    this.context.alisa.whisper(player, "\u043d\u0430\u0447\u0438\u043d\u0430\u0435\u0442\u0441\u044f \u0441\u043a\u0430\u0447\u0438\u0432\u0430\u043d\u0438\u0435 \u0444\u0430\u0439\u043b\u0430 #c2" + filename);
                    if (mf.downloadFileToPluginConfigFolder(url, filename)) {
                        this.context.alisa.whisper(player, "#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0444\u0430\u0439\u043b \u0441\u043a\u0430\u0447\u0430\u043d");
                        this.handleReloadQuestionsAndRulesFilesCommand(player);
                    }
                    else {
                        this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u0441\u043a\u0430\u0447\u0430\u0442\u044c \u0444\u0430\u0439\u043b");
                    }
                }
                else {
                    this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u0430\u044f \u0441\u0441\u044b\u043b\u043a\u0430");
                }
            }
            else {
                this.context.alisa.whisper(player, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u043c\u044f \u0444\u0430\u0439\u043b\u0430 #c2%s#c3 \u043d\u0435 \u0432\u0445\u043e\u0434\u0438\u0442 \u0432 \u0441\u043f\u0438\u0441\u043e\u043a \u0440\u0430\u0437\u0440\u0435\u0448\u0435\u043d\u043d\u044b\u0445 \u0434\u043b\u044f \u0441\u043a\u0430\u0447\u0438\u0432\u0430\u043d\u0438\u044f", filename));
            }
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435");
        }
    }
    
    private boolean isFileNameAllowed(final String filename) {
        return this.allowedFiles.contains(filename);
    }
    
    private boolean isValidUrl(final String url) {
        try {
            final URL url2 = new URL(url);
        }
        catch (MalformedURLException e) {
            return false;
        }
        return true;
    }
    
    protected void handleReloadQuestionsAndRulesFilesCommand(final Player player) {
        this.context.messageHandler.reloadQuestions();
        try {
            this.context.alisa.fillExtraServerRules();
        }
        catch (Exception e) {
            e.printStackTrace();
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043f\u0440\u043e\u0438\u0437\u043e\u0448\u043b\u0430 \u043e\u0448\u0438\u0431\u043a\u0430 \u043f\u0440\u0438 \u043f\u0435\u0440\u0435\u0437\u0430\u0433\u0440\u0443\u0437\u043a\u0435 \u0444\u0430\u0439\u043b\u0430 \u0441 \u0434\u043e\u043f. \u043f\u0440\u0430\u0432\u0438\u043b\u0430\u043c\u0438 \u0441\u0435\u0440\u0432\u0435\u0440\u0430");
        }
        final int totalQuestions = this.context.messageHandler.processorQuestions.getQuestionsCount();
        this.context.alisa.whisper(player, "#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0432\u043e\u043f\u0440\u043e\u0441\u044b \u0438 \u0434\u043e\u043f. \u043f\u0440\u0430\u0432\u0438\u043b\u0430 \u043f\u0435\u0440\u0435\u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d\u044b. \u0412\u0441\u0435\u0433\u043e \u0432\u043e\u043f\u0440\u043e\u0441\u043e\u0432: " + totalQuestions);
    }
    
    protected void handlePlaytimeReportCommand(final Player player) {
        final PlaytimeReport report1 = (PlaytimeReport)this.context.config.getObject("report1");
        final PlaytimeReport report2 = (PlaytimeReport)this.context.config.getObject("report2");
        if (report1 != null && report2 != null) {
            final StringBuilder sb = new StringBuilder("\u041e\u0442\u0447\u0435\u0442 \u043f\u043e \u043e\u043d\u043b\u0430\u0439\u043d\u0443 \u043c\u043e\u0434\u0435\u0440\u0430\u0442\u043e\u0440\u043e\u0432:\n");
            final DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            final Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(report1.time);
            final String date1 = formatter.format(calendar.getTime());
            calendar.setTimeInMillis(report2.time);
            final String date2 = formatter.format(calendar.getTime());
            final float daysPassed = (report2.time - report1.time) / 1000.0f / 60.0f / 60.0f / 24.0f;
            sb.append(ChatColor.GRAY);
            sb.append(String.format("%s -> %s (\u043f\u0435\u0440\u0438\u043e\u0434 %.2f \u0434\u043d\u0435\u0439)\n", date1, date2, daysPassed));
            sb.append("[\u0434\u043e\u043b\u0436\u043d\u043e\u0441\u0442\u044c] \u0438\u043c\u044f : \u043e\u0431\u0449\u0438\u0439 - \u0437\u0430 \u043f\u0435\u0440\u0438\u043e\u0434 - \u0441\u0440\u0435\u0434\u043d\u0438\u0439\n");
            for (final ModeratorsEntry me : this.context.alisa.moderatorsHandler.groups) {
                if (me.ID < 100) {
                    for (final String name : me.playerNames) {
                        Integer totalMinutes = null;
                        Integer lastWeekMinutes = null;
                        Float averageMinutes = null;
                        if (report2.entries.containsKey(name)) {
                            totalMinutes = report2.entries.get(name);
                            if (report1.entries.containsKey(name)) {
                                lastWeekMinutes = report2.entries.get(name) - report1.entries.get(name);
                                averageMinutes = lastWeekMinutes / daysPassed;
                            }
                        }
                        final String totalHours = (totalMinutes != null) ? String.format("%.2f", totalMinutes / 60.0f) : "?";
                        final String lastWeekHours = (lastWeekMinutes != null) ? String.format("%.2f", lastWeekMinutes / 60.0f) : "?";
                        final String averageHours = (averageMinutes != null) ? String.format("%.2f", averageMinutes / 60.0f) : "?";
                        sb.append(String.format("%s[%s%s] %s#c3 : #cw%s#c3 - #cw%s#c3 - #cw%s#c3\n", ChatColor.DARK_GRAY, me.getColoredGroupName(), ChatColor.DARK_GRAY, '§' + me.playernameColor + name, totalHours, lastWeekHours, averageHours));
                    }
                }
            }
            this.context.alisa.whisper(player, sb.toString());
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043f\u0440\u043e\u0448\u043b\u043e \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0432\u0440\u0435\u043c\u0435\u043d\u0438 \u0434\u043b\u044f \u0441\u043e\u0441\u0442\u0430\u0432\u043b\u0435\u043d\u0438\u044f \u043e\u0442\u0447\u0435\u0442\u043e\u0432 \u043e\u0431 \u043e\u043d\u043b\u0430\u0439\u043d\u0435");
        }
    }
    
    protected void handleStatsCommand(final Player player) {
        this.context.alisa.saveStatistics();
        final StringBuilder sb = new StringBuilder("\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043a\u0430:\n");
        sb.append(String.format("#c3\u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0439 \u0432 \u0447\u0430\u0442: #cw%d\n", this.context.alisa.statistics.chatMessages));
        sb.append(String.format("#c3\u043e\u0442\u0432\u0435\u0442\u043e\u0432 \u043d\u0430 \u0432\u043e\u043f\u0440\u043e\u0441\u044b: #cw%d\n", this.context.alisa.statistics.answers));
        sb.append(String.format("#c3\u0432\u0430\u0440\u043d\u043e\u0432: #cw%d\n", this.context.alisa.statistics.warns));
        sb.append(String.format("#c3\u043c\u0443\u0442\u043e\u0432: #cw%d\n", this.context.alisa.statistics.mutes));
        sb.append(String.format("#c3\u043e\u0431\u0449\u0430\u044f \u0434\u043b\u0438\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u044c \u043c\u0443\u0442\u043e\u0432: #cw%.2f #c3\u043c\u0438\u043d\u0443\u0442\n", this.context.alisa.statistics.mutesDuration / 60.0f));
        sb.append(String.format("#c3\u043d\u0430\u0447\u0430\u0442\u043e \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0439: #cw%d\n", this.context.alisa.statistics.totalVotesStarted));
        sb.append(String.format("#c3\u0443\u0441\u043f\u0435\u0448\u043d\u044b\u0445 \u0433\u043e\u043b\u043e\u0441\u043e\u0432\u0430\u043d\u0438\u0439: #cw%d\n", this.context.alisa.statistics.successfulVotes));
        sb.append(String.format("#c3\u043a\u043e\u043c\u0430\u043d\u0434 /mods \u0438 /inf: #cw%d\n", this.context.alisa.statistics.modsAndInfCommands));
        sb.append(String.format("#c3\u043f\u043b\u0435\u0439\u0442\u0430\u0439\u043c %s: #cw%.2f #c3\u0447\u0430\u0441\u043e\u0432\n", this.context.config.getString("name2"), this.context.alisa.statistics.onlineSeconds / 60.0f / 60.0f));
        this.context.alisa.whisper(player, sb.toString());
    }
    
    protected void handleRemoveFileCommand(final Player player, final String[] args) {
        if (args.length >= 2) {
            final String filename = args[1];
            if (this.isFileNameAllowed(filename)) {
                if (mf.removeFile(filename)) {
                    this.context.alisa.whisper(player, "#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0444\u0430\u0439\u043b \u0443\u0434\u0430\u043b\u0435\u043d");
                    this.handleReloadQuestionsAndRulesFilesCommand(player);
                }
                else {
                    this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0444\u0430\u0439\u043b \u043d\u0435 \u0443\u0434\u0430\u043b\u0435\u043d. \u0412\u043e\u0437\u043c\u043e\u0436\u043d\u043e, \u0435\u0433\u043e \u0438 \u043d\u0435 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u043e\u0432\u0430\u043b\u043e");
                }
            }
            else {
                this.context.alisa.whisper(player, String.format("#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u043c\u044f \u0444\u0430\u0439\u043b\u0430 #c2%s#c3 \u043d\u0435 \u0432\u0445\u043e\u0434\u0438\u0442 \u0432 \u0441\u043f\u0438\u0441\u043e\u043a \u0440\u0430\u0437\u0440\u0435\u0448\u0435\u043d\u043d\u044b\u0445 \u0434\u043b\u044f \u0441\u043a\u0430\u0447\u0438\u0432\u0430\u043d\u0438\u044f \u0438\u043b\u0438 \u0443\u0434\u0430\u043b\u0435\u043d\u0438\u044f. \u041f\u0440\u043e\u0432\u0435\u0440\u044c \u0438\u043c\u044f \u0444\u0430\u0439\u043b\u0430", filename));
            }
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435");
        }
    }
    
    protected void handleReloadConfigCommand(final Player player) {
        this.context.reloadConfig();
        this.context.alisa.whisper(player, "#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u043a\u043e\u043d\u0444\u0438\u0433 \u043f\u0435\u0440\u0435\u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d. \u041d\u0435\u043a\u043e\u0442\u043e\u0440\u044b\u0435 \u0438\u0437\u043c\u0435\u043d\u0435\u043d\u0438\u044f \u0432\u0441\u0442\u0443\u043f\u044f\u0442 \u0432 \u0441\u0438\u043b\u0443 \u0442\u043e\u043b\u044c\u043a\u043e \u043f\u043e\u0441\u043b\u0435 \u0440\u0435\u0441\u0442\u0430\u0440\u0442\u0430");
    }
    
    protected void handleAlisaHelpCommand(final Player player) {
        final StringBuilder sb = new StringBuilder("#c2\u0421\u043f\u0438\u0441\u043e\u043a \u0430\u0434\u043c\u0438\u043d\u0441\u043a\u0438\u0445 \u043a\u043e\u043c\u0430\u043d\u0434#c3:\n");
        for (final String s : this.alisaHelpCommandStrings) {
            sb.append(s).append("\n");
        }
        this.context.alisa.whisper(player, sb.toString());
    }
    
    protected void handleRetranslateCommand(final Player player, final String[] args) {
        if (args.length >= 2) {
            if (!args[1].isEmpty()) {
                this.context.alisa.say(args[1].replaceAll("&", Character.toString('§')));
            }
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0441\u043e\u043e\u0431\u0449\u0435\u043d\u0438\u0435 \u043f\u0443\u0441\u0442\u043e");
        }
    }
    
    protected void handleTpCommand(final Player player, final String[] args) {
        if (args.length >= 2) {
            if (!args[1].isEmpty()) {
                final Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    player.teleport(target.getLocation());
                    this.context.alisa.whisper(player, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442 \u043a \u0438\u0433\u0440\u043e\u043a\u0443 #c2%s", target.getName()));
                }
                else {
                    this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0438\u0433\u0440\u043e\u043a \u043d\u0435 \u043e\u043d\u043b\u0430\u0439\u043d");
                }
            }
        }
        else {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435 \u0443\u043a\u0430\u0437\u0430\u043d\u043e \u0438\u043c\u044f");
        }
    }
    
    protected void handleLoadChunksCommand(final Player player, final String[] args) {
        Integer r = null;
        try {
            r = Integer.parseInt(args[1]);
        }
        catch (Exception e) {
            e.printStackTrace();
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0432\u0435\u0440\u043d\u043e \u0443\u043a\u0430\u0437\u0430\u043d \u043f\u0430\u0440\u0430\u043c\u0435\u0442\u0440 \u0440\u0430\u0434\u0438\u0443\u0441\u0430");
            return;
        }
        if (r < 1) {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0440\u0430\u0434\u0438\u0443\u0441 \u043d\u0435 \u043c\u043e\u0436\u0435\u0442 \u0431\u044b\u0442\u044c \u043c\u0435\u043d\u044c\u0448\u0435 1");
            return;
        }
        if (r > 6) {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0440\u0430\u0434\u0438\u0443\u0441 \u043d\u0435 \u043c\u043e\u0436\u0435\u0442 \u0431\u044b\u0442\u044c \u0431\u043e\u043b\u044c\u0448\u0435 6");
            return;
        }
        final Chunk c = player.getWorld().getChunkAt(player.getLocation());
        final int chunkX = c.getX();
        final int chunkZ = c.getZ();
        final ArrayList<String> res = new ArrayList<String>();
        for (int x = chunkX - (r - 1); x <= chunkX + (r - 1); ++x) {
            for (int z = chunkZ - (r - 1); z <= chunkZ + (r - 1); ++z) {
                this.context.alisa.chunkLoader.addChunk(c.getWorld().getChunkAt(x, z));
                res.add(String.format("[#c2%d#c3,#c2%d#c3]", x, z));
            }
        }
        this.context.alisa.whisper(player, "#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0434\u043e\u0431\u0430\u0432\u043b\u0435\u043d\u043e \u0432 \u043f\u0440\u043e\u0433\u0440\u0443\u0437\u043a\u0443 #c2" + res.size() + "#c3 \u0447\u0430\u043d\u043a\u043e\u0432: " + String.join(", ", res) + " \u0432 \u043c\u0438\u0440\u0435 #c2" + c.getWorld().getName());
        this.context.alisa.whisper(player, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u0432\u0441\u0435\u0433\u043e \u0441\u0435\u0439\u0447\u0430\u0441 \u043f\u0440\u043e\u0433\u0440\u0443\u0436\u0430\u0435\u0442\u0441\u044f #c2%d#c3 \u0447\u0430\u043d\u043a\u043e\u0432", this.context.alisa.chunkLoader.loadedChunks.size()));
    }
    
    protected void handleUnloadChunksCommand(final Player player) {
        if (!this.context.alisa.chunkLoader.isActive) {
            this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0438\u043a\u0430\u043a\u0438\u0435 \u0447\u0430\u043d\u043a\u0438 \u0432 \u0434\u0430\u043d\u043d\u044b\u0439 \u043c\u043e\u043c\u0435\u043d\u0442 \u043d\u0435 \u043f\u0440\u043e\u0433\u0440\u0443\u0436\u0430\u044e\u0442\u0441\u044f");
        }
        else {
            final int num = this.context.alisa.chunkLoader.loadedChunks.size();
            this.context.alisa.chunkLoader.setActive(false);
            this.context.alisa.whisper(player, String.format("#cg\u0423\u0441\u043f\u0435\u0448\u043d\u043e#c3: \u043f\u0440\u043e\u0433\u0440\u0443\u0437\u043a\u0430 #c2%d#c3 \u0447\u0430\u043d\u043a\u043e\u0432 \u043e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u0430", num));
        }
    }
}
