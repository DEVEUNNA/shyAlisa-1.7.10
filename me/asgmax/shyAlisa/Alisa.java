//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.scheduler.*;
import org.bukkit.plugin.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_7_R4.entity.*;
import net.minecraft.server.v1_7_R4.*;
import net.md_5.bungee.api.chat.*;
import java.util.regex.*;
import java.util.*;

public class Alisa
{
    private shyAlisa context;
    private String muteCommand;
    private String warnCommand;
    private Random rand;
    private HashMap<AnswerReason, Answer> answers;
    private HashMap<AnswerReason, Answer> moderatorAnswers;
    private HashMap<PunishmentType, String> punishments;
    protected HashMap<String, ServerRule> serverRules;
    protected HashMap<String, ServerRule> serverRulesExtra;
    protected ModeratorsHandler moderatorsHandler;
    private ArrayList<String> helloAnswers;
    private ArrayList<String> byeAnswers;
    private ArrayList<String> persiki;
    protected CooldownsHandler cooldownsHandler;
    protected VoteHandler voteHandler;
    protected UtilCommandsHandler utilCommandsHandler;
    protected Statistics statistics;
    private long lastSavedStasisticsTimeStampMillis;
    protected ChunkLoader chunkLoader;
    protected HashSet<String> knownPlayerNames;
    
    protected Alisa(final shyAlisa context) {
        this.rand = new Random();
        this.answers = new HashMap<AnswerReason, Answer>();
        this.moderatorAnswers = new HashMap<AnswerReason, Answer>();
        this.punishments = new HashMap<PunishmentType, String>();
        this.serverRules = new HashMap<String, ServerRule>();
        this.knownPlayerNames = new HashSet<String>();
        this.context = context;
        this.createAnswers();
        this.createModeratorAnswers();
        this.createPunishments();
        this.fillServerRules();
        this.fillExtraServerRules();
        this.cooldownsHandler = new CooldownsHandler(this.context);
        this.moderatorsHandler = new ModeratorsHandler(this.context);
        this.helloAnswers = mf.readProjectFileLines("hello-answers.txt");
        this.byeAnswers = mf.readProjectFileLines("bye-answers.txt");
        this.persiki = mf.readProjectFileLines("persiki.txt");
        this.voteHandler = new VoteHandler(this.context);
        this.utilCommandsHandler = new UtilCommandsHandler(this.context);
        this.initializeStatistics();
        this.runStatisticsSaverTask();
        this.chunkLoader = new ChunkLoader(context);
    }
    
    private void runStatisticsSaverTask() {
        final BukkitRunnable br = new BukkitRunnable() {
            public void run() {
                Alisa.this.saveStatistics();
            }
        };
        br.runTaskTimerAsynchronously((Plugin)this.context, 2400L, 2400L);
    }
    
    protected void saveStatistics() {
        final Statistics statistics = this.statistics;
        statistics.onlineSeconds += (int)((System.currentTimeMillis() - this.lastSavedStasisticsTimeStampMillis) / 1000L);
        this.lastSavedStasisticsTimeStampMillis = System.currentTimeMillis();
        this.context.config.set("statistics", this.statistics);
    }
    
    private void initializeStatistics() {
        final Statistics st = (Statistics)this.context.config.getObject("statistics");
        this.lastSavedStasisticsTimeStampMillis = System.currentTimeMillis();
        if (st != null) {
            this.context.log("statistics restored");
            this.statistics = st;
        }
        else {
            this.context.log("statistics cant be restored, creating new");
            this.statistics = new Statistics(0, 0, 0, 0, 0, 0, 0, 0, 0);
        }
    }
    
    private String randomPersik() {
        return this.persiki.get(this.rand.nextInt(this.persiki.size()));
    }
    
    protected void sayHello(final Player player) {
        this.say(this.helloAnswers.get(this.rand.nextInt(this.helloAnswers.size())));
    }
    
    protected void sayBye(final Player player) {
        this.say(this.byeAnswers.get(this.rand.nextInt(this.byeAnswers.size())));
    }
    
    private void createAnswers() {
        this.answers.put(AnswerReason.CAPS, new Answer("answers-caps.txt", this));
        this.answers.put(AnswerReason.FLOOD, new Answer("answers-flood.txt", this));
        this.answers.put(AnswerReason.ADVERTISEMENT, new Answer("answers-advertisement.txt", this));
        this.answers.put(AnswerReason.PROFANITY, new Answer("answers-profanity.txt", this));
        this.answers.put(AnswerReason.DOUBLEWARN, new Answer("answers-doublewarn.txt", this));
        this.answers.put(AnswerReason.WARN, new Answer("answers-warn.txt", this));
    }
    
    private void createModeratorAnswers() {
        this.moderatorAnswers.put(AnswerReason.CAPS, new Answer("moderator-answers-caps.txt", this));
        this.moderatorAnswers.put(AnswerReason.FLOOD, new Answer("moderator-answers-flood.txt", this));
        this.moderatorAnswers.put(AnswerReason.ADVERTISEMENT, new Answer("moderator-answers-advertisement.txt", this));
        this.moderatorAnswers.put(AnswerReason.PROFANITY, new Answer("moderator-answers-profanity.txt", this));
    }
    
    private void createPunishments() {
        this.punishments.put(PunishmentType.MUTE, this.context.config.getString("mute-command"));
        this.punishments.put(PunishmentType.WARN, this.context.config.getString("warn-command"));
    }
    
    protected String getName() {
        return this.context.config.getString("name1").replace("&", "§");
    }
    
    protected String getName2() {
        return this.context.config.getString("name2").replace("&", "§");
    }
    
    private ArrayList<String> getChatColors() {
        return this.context.config.getList("chat-colors");
    }
    
    private String getNamePrefix() {
        return this.context.config.getString("prefix");
    }
    
    private String getNameColor() {
        return '§' + this.context.config.getString("name-color");
    }
    
    private String getPrefixColor() {
        return '§' + this.context.config.getString("prefix-color");
    }
    
    private int getDoubleWarnTempmuteDuration() {
        return this.context.config.getInt("tempmute.double-warn");
    }
    
    protected void whisper(final Player player, final String message) {
        player.sendMessage(this.formatMessageStringForWhisper(message));
    }
    
    protected void whisper(final CommandSender sender, final String message) {
        sender.sendMessage(this.formatMessageStringForWhisper(message));
    }
    
    private String formatMessageStringForWhisper(String message) {
        message = this.replaceChatColorCodes(message);
        message = ChatColor.GOLD + "[" + this.getNameColor() + this.getName() + ChatColor.GOLD + " -> " + ChatColor.RED + "\u042f" + ChatColor.GOLD + "] " + message;
        message = message.replaceAll("<persik>", this.randomPersik());
        return message;
    }
    
    private String formatMessageStringForBroadcast(String message) {
        message = "#c1" + message;
        message = this.replaceChatColorCodes(message);
        message = ChatColor.DARK_GRAY + "[" + this.getPrefixColor() + this.getNamePrefix() + ChatColor.DARK_GRAY + "] " + this.getNameColor() + this.getName() + ChatColor.WHITE + ": " + message;
        message = message.replaceAll("<persik>", this.randomPersik());
        return message;
    }
    
    public String replaceChatColorCodes(String message) {
        for (int i = 0; i < this.getChatColors().size(); ++i) {
            message = message.replace("#c" + (i + 1), '§' + this.getChatColors().get(i));
        }
        message = message.replace("#cr", '§' + this.context.config.getString("chat-color-warning"));
        message = message.replace("#cg", '§' + this.context.config.getString("chat-color-ok"));
        message = message.replace("#c0", ChatColor.GRAY + "");
        message = message.replace("#cw", ChatColor.WHITE + "");
        return message;
    }
    
    public void test(final Player p, final String[] args) {
    }
    
    public void test2(final Player p, final String[] args) {
    }
    
    public void punish(final PunishmentType punishmentType, final Player player, final int durationSeconds, final String reason, final AnswerReason answerReason) {
        switch (punishmentType) {
            case MUTE: {
                if (!this.moderatorsHandler.isModerator(player)) {
                    this.mute(player, durationSeconds, reason);
                    this.say("#c2" + player.getName() + "#c1, " + this.answers.get(answerReason).getRandomAnswer(player));
                    break;
                }
                this.say(this.moderatorAnswers.get(answerReason).getRandomAnswer(player));
                break;
            }
            case WARN: {
                if (this.moderatorsHandler.isModerator(player)) {
                    this.say(this.moderatorAnswers.get(answerReason).getRandomAnswer(player));
                    break;
                }
                if (this.cooldownsHandler.warnCooldowns.isExpired(player)) {
                    this.warn(player, reason);
                    this.say("#c2" + player.getName() + "#c1, " + this.answers.get(AnswerReason.WARN).getRandomAnswer(player));
                    this.cooldownsHandler.warnCooldowns.trigger(player);
                    break;
                }
                this.punish(PunishmentType.MUTE, player, this.getDoubleWarnTempmuteDuration(), reason + " (\u0440\u0435\u0446\u0438\u0434\u0438\u0432)", AnswerReason.DOUBLEWARN);
                this.cooldownsHandler.warnCooldowns.trigger(player);
                break;
            }
        }
    }
    
    public void say(final String message) {
        if (this.context.config.getBoolean("debug")) {
            this.context.log("[debug] [say] " + message);
        }
        if (!this.context.config.getBoolean("silent")) {
            new BukkitRunnable() {
                public void run() {
                    Bukkit.broadcastMessage(Alisa.this.formatMessageStringForBroadcast(message));
                    final Statistics statistics = Alisa.this.statistics;
                    ++statistics.chatMessages;
                }
            }.runTaskLater((Plugin)this.context, 2L);
        }
    }
    
    public void sayRaw(final TextComponent... args) {
        this.sayRawImplement(2, args);
    }
    
    public void sayRawJson(final String json) {
        if (!this.context.config.getBoolean("silent")) {
            new BukkitRunnable() {
                public void run() {
                    Alisa.this.context.log(json);
                    final IChatBaseComponent comp = ChatSerializer.a(json);
                    final PacketPlayOutChat packet = new PacketPlayOutChat(comp, true);
                    for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
                        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
                    }
                }
            }.runTaskLater((Plugin)this.context, 2L);
        }
    }
    
    public void sayRawDelayed(final TextComponent... args) {
        this.sayRawImplement(5, args);
    }
    
    private void sayRawImplement(final int delay, final TextComponent... args) {
        if (!this.context.config.getBoolean("silent")) {
            new BukkitRunnable() {
                public void run() {
                    final TextComponent res = Alisa.this.joinComponentsGlobal(args);
                    for (final Player p : Bukkit.getServer().getOnlinePlayers()) {
                        p.spigot().sendMessage((BaseComponent)res);
                    }
                    final Statistics statistics = Alisa.this.statistics;
                    ++statistics.chatMessages;
                }
            }.runTaskLater((Plugin)this.context, (long)delay);
        }
    }
    
    public TextComponent textComponent(final String text, final boolean beginning) {
        return new TextComponent(beginning ? this.formatMessageStringForBroadcast(text) : this.replaceChatColorCodes(text));
    }
    
    public TextComponent textComponent(final String text) {
        return this.textComponent(text, false);
    }
    
    public TextComponent clickComponent(final String text, final String command) {
        final TextComponent t1 = new TextComponent(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + this.replaceChatColorCodes(text) + ChatColor.GRAY + "]");
        t1.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command.replaceAll(Pattern.quote("#c") + ".", "")));
        return t1;
    }
    
    public TextComponent linkComponent(final String text, final String command) {
        final TextComponent t1 = new TextComponent(ChatColor.GRAY + "[" + ChatColor.GOLD + this.replaceChatColorCodes(text) + ChatColor.GRAY + "]");
        t1.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, command));
        return t1;
    }
    
    public TextComponent joinComponentsFormatted(final int reach, final TextComponent... args) {
        this.context.log("joincomponents formatted: " + reach);
        TextComponent result = null;
        if (reach == -1) {
            result = this.textComponent(this.formatMessageStringForWhisper(""));
        }
        else if (reach == 1) {
            result = this.textComponent(this.formatMessageStringForBroadcast(""));
        }
        else {
            result = this.textComponent("");
        }
        for (final TextComponent c : args) {
            result.addExtra((BaseComponent)c);
        }
        return result;
    }
    
    public TextComponent joinComponents(final TextComponent... args) {
        return this.joinComponentsFormatted(0, args);
    }
    
    public TextComponent joinComponentsGlobal(final TextComponent... args) {
        return this.joinComponentsFormatted(1, args);
    }
    
    public TextComponent joinComponentsWhisper(final TextComponent... args) {
        return this.joinComponentsFormatted(-1, args);
    }
    
    public void whisperRaw(final Player p, final TextComponent... args) {
        final TextComponent res = this.joinComponentsWhisper(args);
        p.spigot().sendMessage((BaseComponent)res);
    }
    
    public TextComponent addClickCommand(final TextComponent com, final String command) {
        com.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/" + command));
        return com;
    }
    
    private String getPunishmentCommand(final PunishmentType punishmentType) {
        return this.punishments.get(punishmentType);
    }
    
    private void executeCommand(final String command) {
        new BukkitRunnable() {
            public void run() {
                Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getServer().getConsoleSender(), command);
            }
        }.runTaskLater((Plugin)this.context, 1L);
    }
    
    private void mute(final Player player, final int durationSeconds, final String reason) {
        new BukkitRunnable() {
            public void run() {
                Alisa.this.executeCommand(String.format(Alisa.this.getPunishmentCommand(PunishmentType.MUTE), player.getName(), Integer.toString(durationSeconds) + " sec", reason));
                final Statistics statistics = Alisa.this.statistics;
                ++statistics.mutes;
                final Statistics statistics2 = Alisa.this.statistics;
                statistics2.mutesDuration += durationSeconds;
            }
        }.runTaskLater((Plugin)this.context, 1L);
    }
    
    private void warn(final Player player, final String reason) {
        new BukkitRunnable() {
            public void run() {
                Alisa.this.executeCommand(String.format(Alisa.this.getPunishmentCommand(PunishmentType.WARN), player.getName(), reason));
                final Statistics statistics = Alisa.this.statistics;
                ++statistics.warns;
            }
        }.runTaskLater((Plugin)this.context, 1L);
    }
    
    protected void fillServerRules() {
        final ArrayList<String> temp = mf.readProjectFileLines("server-rules.txt");
        final ArrayList<Integer> rulesIndexes = new ArrayList<Integer>();
        rulesIndexes.add(0);
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).equalsIgnoreCase("***")) {
                rulesIndexes.add(i + 1);
            }
        }
        for (final Integer index : rulesIndexes) {
            final ServerRule sr = this.getNextServerRule(temp, index);
            if (sr != null) {
                this.serverRules.put(sr.par, sr);
            }
        }
    }
    
    private ServerRule getNextServerRule(final ArrayList<String> arr, final int startingIndex) {
        if (startingIndex >= arr.size()) {
            return null;
        }
        final String par = arr.get(startingIndex);
        String description = (arr.size() > startingIndex + 1) ? arr.get(startingIndex + 1) : "---";
        description = description.replaceAll("<n>", "\n");
        String punishment = (arr.size() > startingIndex + 2) ? arr.get(startingIndex + 2) : "";
        if (punishment.contains("***")) {
            punishment = "";
        }
        return new ServerRule(par, description, punishment);
    }
    
    protected void fillExtraServerRules() {
        this.serverRulesExtra = new HashMap<String, ServerRule>();
        if (mf.fileExistsInDataFolder("server-rules-extra.txt")) {
            final ArrayList<String> temp = mf.readFileFromDataFolderToArray("server-rules-extra.txt");
            final ArrayList<Integer> rulesIndexes = new ArrayList<Integer>();
            rulesIndexes.add(0);
            for (int i = 0; i < temp.size(); ++i) {
                if (temp.get(i).equalsIgnoreCase("***")) {
                    rulesIndexes.add(i + 1);
                }
            }
            for (final Integer index : rulesIndexes) {
                final ServerRule sr = this.getNextServerRule(temp, index);
                if (sr != null) {
                    this.serverRulesExtra.put(sr.par, sr);
                }
            }
        }
    }
    
    protected void addKnownPlayer(final String playername) {
        this.knownPlayerNames.add(playername);
    }
    
    public enum PunishmentType
    {
        MUTE, 
        WARN, 
        BAN;
    }
    
    public enum AnswerReason
    {
        CAPS, 
        PROFANITY, 
        FLOOD, 
        ADVERTISEMENT, 
        WARN, 
        DOUBLEWARN;
    }
    
    private class Answer
    {
        ArrayList<String> answerStrings;
        Alisa context;
        
        private Answer(final String answersFilePath, final Alisa context) {
            this.context = context;
            this.answerStrings = mf.readProjectFileLines(answersFilePath);
        }
        
        protected String getRandomAnswer(final Player player) {
            return String.format(this.answerStrings.get(Alisa.this.rand.nextInt(this.answerStrings.size())), "#c2" + player.getName() + "#c1");
        }
    }
}
