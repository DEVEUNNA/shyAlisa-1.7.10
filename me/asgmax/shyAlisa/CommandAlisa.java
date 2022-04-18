//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class CommandAlisa implements CommandExecutor
{
    private shyAlisa context;
    
    public CommandAlisa(final shyAlisa context) {
        this.context = context;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (player.isOp()) {
                if (args.length == 0) {
                    this.context.alisa.utilCommandsHandler.handleAlisaHelpCommand(player);
                }
                else if (args.length >= 1) {
                    if (args[0].equalsIgnoreCase("set")) {
                        this.context.settings.handleSetCommand(player, cmd, args);
                    }
                    else if (args[0].equalsIgnoreCase("read")) {
                        this.context.settings.handleReadCommand(player, cmd, args);
                    }
                    else if (args[0].equalsIgnoreCase("mods")) {
                        this.context.settings.handleModsCommand(player, cmd, args);
                    }
                    else if (args[0].equalsIgnoreCase("getname")) {
                        this.context.alisa.utilCommandsHandler.handleGetNameCommand(player, cmd, args);
                    }
                    else if (args[0].equalsIgnoreCase("getuuid")) {
                        this.context.alisa.utilCommandsHandler.handleGetUUIDCommand(player, cmd, args);
                    }
                    else if (args[0].equalsIgnoreCase("tospawn")) {
                        this.context.alisa.utilCommandsHandler.handleToSpawnCommand(player, cmd, args);
                    }
                    else if (args[0].equalsIgnoreCase("checkupdate")) {
                        this.context.alisa.utilCommandsHandler.handleCheckForUpdateCommand(player);
                    }
                    else if (args[0].equalsIgnoreCase("getupdate")) {
                        this.context.alisa.utilCommandsHandler.handleGetUpdateCommand(player);
                    }
                    else if (args[0].equalsIgnoreCase("forceupdate")) {
                        this.context.alisa.utilCommandsHandler.handleForceUpdateCommand(player);
                    }
                    else if (args[0].equalsIgnoreCase("getfile")) {
                        this.context.alisa.utilCommandsHandler.handleGetFileCommand(player, args);
                    }
                    else if (args[0].equalsIgnoreCase("reloadfiles")) {
                        this.context.alisa.utilCommandsHandler.handleReloadQuestionsAndRulesFilesCommand(player);
                    }
                    else if (args[0].equalsIgnoreCase("removefile")) {
                        this.context.alisa.utilCommandsHandler.handleRemoveFileCommand(player, args);
                    }
                    else if (args[0].equalsIgnoreCase("playtimereport")) {
                        this.context.alisa.utilCommandsHandler.handlePlaytimeReportCommand(player);
                    }
                    else if (args[0].equalsIgnoreCase("stats")) {
                        this.context.alisa.utilCommandsHandler.handleStatsCommand(player);
                    }
                    else if (args[0].equalsIgnoreCase("toggledetect") || args[0].equalsIgnoreCase("td")) {
                        this.context.alisa.moderatorsHandler.toggleDetect(player);
                    }
                    else if (args[0].equalsIgnoreCase("reloadconfig")) {
                        this.context.alisa.utilCommandsHandler.handleReloadConfigCommand(player);
                    }
                    else if (args[0].equalsIgnoreCase("==")) {
                        this.context.alisa.utilCommandsHandler.handleRetranslateCommand(player, args);
                    }
                    else if (args[0].equals("tp")) {
                        this.context.alisa.utilCommandsHandler.handleTpCommand(player, args);
                    }
                    else if (args[0].equalsIgnoreCase("loadchunks")) {
                        this.context.alisa.utilCommandsHandler.handleLoadChunksCommand(player, args);
                    }
                    else if (args[0].equalsIgnoreCase("unloadchunks")) {
                        this.context.alisa.utilCommandsHandler.handleUnloadChunksCommand(player);
                    }
                    else {
                        this.context.alisa.whisper(player, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u0442\u0430\u043a\u043e\u0439 \u043a\u043e\u043c\u0430\u043d\u0434\u044b \u043d\u0435\u0442");
                    }
                }
                else {
                    this.context.alisa.whisper(sender, "#cr\u041e\u0448\u0438\u0431\u043a\u0430#c3: \u043d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432 \u043a \u043a\u043e\u043c\u0430\u043d\u0434\u0435");
                }
            }
            return true;
        }
        sender.sendMessage("players only");
        return true;
    }
}
