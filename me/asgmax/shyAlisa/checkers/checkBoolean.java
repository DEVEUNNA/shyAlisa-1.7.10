//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa.checkers;

import java.util.*;

public class checkBoolean implements IArgumentChecker
{
    @Override
    public boolean check(final ArrayList<String> args) {
        return args.get(0).equalsIgnoreCase("true") || args.get(0).equalsIgnoreCase("false");
    }
    
    @Override
    public Object getFixedClassedArg(final ArrayList<String> args) {
        return this.getBooleanFromString(args.get(0));
    }
    
    private Boolean getBooleanFromString(final String s) {
        if (s.equalsIgnoreCase("true")) {
            return true;
        }
        if (s.equalsIgnoreCase("false")) {
            return false;
        }
        return null;
    }
}
