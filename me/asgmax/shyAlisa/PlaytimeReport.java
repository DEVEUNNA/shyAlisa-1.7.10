//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.configuration.serialization.*;
import java.util.*;

public class PlaytimeReport implements ConfigurationSerializable
{
    protected long time;
    protected HashMap<String, Integer> entries;
    
    public PlaytimeReport(final long time, final HashMap<String, Integer> entries) {
        this.time = time;
        this.entries = entries;
    }
    
    public Map<String, Object> serialize() {
        final LinkedHashMap result = new LinkedHashMap();
        result.put("time", this.time);
        result.put("entries", this.entries);
        return (Map<String, Object>)result;
    }
    
    public static PlaytimeReport deserialize(final Map<String, Object> args) {
        long time = -1L;
        HashMap<String, Integer> entries = new HashMap<String, Integer>();
        if (args.containsKey("time")) {
            time = args.get("time");
        }
        if (args.containsKey("entries")) {
            entries = args.get("entries");
        }
        return new PlaytimeReport(time, entries);
    }
}
