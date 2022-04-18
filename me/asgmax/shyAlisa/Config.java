//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.configuration.file.*;
import java.util.*;

public class Config
{
    private shyAlisa context;
    private FileConfiguration config;
    
    public Config(final shyAlisa context) {
        this.context = context;
        this.config = context.getConfig();
    }
    
    public String getString(final String key) {
        return this.config.getString(key);
    }
    
    public float getFloat(final String key) {
        return (float)this.config.getDouble(key);
    }
    
    public int getInt(final String key) {
        return this.config.getInt(key);
    }
    
    public boolean getBoolean(final String key) {
        return this.config.getBoolean(key, false);
    }
    
    public ArrayList<String> getList(final String key) {
        return (ArrayList<String>)this.config.getStringList(key);
    }
    
    public void set(final String key, final Object value) {
        this.config.set(key, value);
        this.context.saveConfig();
    }
    
    public Object getObject(final String key) {
        return this.config.get(key);
    }
    
    public boolean exists(final String key) {
        return this.config.contains(key);
    }
}
