//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa.checkers;

import java.util.*;

public class checkInt implements IArgumentChecker
{
    private int rangeMin;
    private int rangeMax;
    
    public checkInt(final int rangeMin, final int rangeMax) {
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
    }
    
    @Override
    public boolean check(final ArrayList<String> args) {
        final Integer parsed;
        return (parsed = this.getIntFromString(args.get(0))) != null && parsed >= this.rangeMin && parsed <= this.rangeMax;
    }
    
    @Override
    public Object getFixedClassedArg(final ArrayList<String> args) {
        return this.getIntFromString(args.get(0));
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
