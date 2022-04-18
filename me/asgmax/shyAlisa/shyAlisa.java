//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.plugin.java.*;
import java.util.logging.*;
import com.rogue.playtime.*;
import org.bukkit.command.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.configuration.serialization.*;

public class shyAlisa extends JavaPlugin
{
    private Logger log;
    private static shyAlisa instance;
    private MyChatListener listenerChat;
    private ListenerPlayerLogin listenerPlayerLogin;
    protected MessageHandler messageHandler;
    protected Config config;
    protected Alisa alisa;
    protected Settings settings;
    protected Playtime pluginPlaytime;
    protected ListenerChunkUnload listenerChunkUnload;
    long startTime;
    
    public shyAlisa() {
        this.log = this.getLogger();
    }
    
    public void onEnable() {
        shyAlisa.instance = this;
        this.log.info("shyalisa enabled");
        this.registerConfig();
        this.config = new Config(this);
        try {
            this.alisa = new Alisa(this);
        }
        catch (Exception e) {
            this.log("ERROR CREATING ALISA INSTANCE, disabling plugin");
            this.log(e.toString());
            e.printStackTrace();
            this.disable();
            return;
        }
        this.messageHandler = new MessageHandler(this);
        this.registerListeners();
        this.registerCommands();
        this.settings = new Settings(this);
        this.pluginPlaytime = (Playtime)Bukkit.getPluginManager().getPlugin("Playtime");
        if (this.needNewPlaytimeReport()) {
            this.pushNewPlaytimeReport();
            this.log("new playtime report generated");
        }
        this.startTime = System.currentTimeMillis();
    }
    
    public void onDisable() {
        this.alisa.saveStatistics();
    }
    
    public static shyAlisa getInstance() {
        return shyAlisa.instance;
    }
    
    public void registerCommands() {
        this.getCommand("inf").setExecutor((CommandExecutor)new CommandInf(this));
        this.getCommand("mods").setExecutor((CommandExecutor)new CommandMods(this));
        this.getCommand("alisa").setExecutor((CommandExecutor)new CommandAlisa(this));
        this.getCommand("votesun").setExecutor((CommandExecutor)new CommandVotesun(this));
        this.getCommand("voteday").setExecutor((CommandExecutor)new CommandVoteday(this));
        this.getCommand("yes").setExecutor((CommandExecutor)new CommandYes(this));
        this.getCommand("no").setExecutor((CommandExecutor)new CommandNo(this));
        this.getCommand("colors").setExecutor((CommandExecutor)new CommandColors(this));
        this.getCommand("ahelp").setExecutor((CommandExecutor)new CommandAhelp(this));
        this.getCommand("server").setExecutor((CommandExecutor)new CommandServer(this));
        this.getCommand("aseen").setExecutor((CommandExecutor)new CommandAseen(this));
    }
    
    public void log(final String msg) {
        this.log.info(msg);
    }
    
    protected void registerListenerChunkUnload() {
        this.listenerChunkUnload = new ListenerChunkUnload(this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this.listenerChunkUnload, (Plugin)this);
    }
    
    protected void unregisterListenerChunkUnload() {
        HandlerList.unregisterAll((Listener)this.listenerChunkUnload);
        this.listenerChunkUnload = null;
    }
    
    private void registerListeners() {
        if (this.config.getBoolean("essChat")) {
            this.listenerChat = (MyChatListener)new ListenerChatEssChat(this);
            this.log("using essentials chat");
        }
        else {
            this.listenerChat = (MyChatListener)new ListenerChat(this);
            this.log("not using essentials chat");
        }
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this.listenerChat, (Plugin)this);
        this.listenerPlayerLogin = new ListenerPlayerLogin(this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this.listenerPlayerLogin, (Plugin)this);
    }
    
    protected void say(final String msg) {
        Bukkit.broadcastMessage(msg);
    }
    
    protected void d(final String msg) {
        Bukkit.broadcastMessage(msg);
    }
    
    protected void debug(final String msg) {
        this.log("(debug): " + msg);
    }
    
    private void registerConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }
    
    protected void disable() {
        Bukkit.getPluginManager().disablePlugin((Plugin)this);
    }
    
    public World getMainWorld() {
        return Bukkit.getWorld(this.config.getString("main-world"));
    }
    
    public World getWorld(final String name) {
        return Bukkit.getWorld(name);
    }
    
    public Playtime getPlaytime() {
        return this.pluginPlaytime;
    }
    
    private void pushNewPlaytimeReport() {
        final PlaytimeReport oldReport = (PlaytimeReport)this.config.getObject("report2");
        this.config.set("report1", (Object)oldReport);
        this.config.set("report2", (Object)this.newPlaytimeReport());
    }
    
    private PlaytimeReport newPlaytimeReport() {
        final HashMap<String, Integer> entries = new HashMap<String, Integer>();
        for (final ModeratorsEntry me : this.alisa.moderatorsHandler.groups) {
            for (final String playerName : me.playerNames) {
                final int tempPlaytime = mf.getPlayerPlaytime(playerName);
                entries.put(playerName, tempPlaytime);
            }
        }
        return new PlaytimeReport(System.currentTimeMillis(), (HashMap)entries);
    }
    
    private boolean needNewPlaytimeReport() {
        final PlaytimeReport oldReport = (PlaytimeReport)this.config.getObject("report2");
        if (mf.getDayOfWeek() != 2) {
            this.log("need new playtime report: false (wrong day of the week)");
            return false;
        }
        if (oldReport == null) {
            this.log("need new playtime report: true (old report == null)");
            return true;
        }
        if (System.currentTimeMillis() - oldReport.time > 172800000L) {
            this.log("need new playtime report: true");
            return true;
        }
        this.log("need new playtime report: false (less than 2 days passed since last report)");
        return false;
    }
    
    static {
        ConfigurationSerialization.registerClass((Class)ModeratorsEntry.class, "ModeratorsEntry");
        ConfigurationSerialization.registerClass((Class)PlaytimeReport.class, "PlaytimeReport");
        ConfigurationSerialization.registerClass((Class)Statistics.class, "Statistics");
    }
}
