//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa.checkers;

import java.util.*;

public class checkFloat implements IArgumentChecker
{
    private float rangeMin;
    private float rangeMax;
    
    public checkFloat(final float rangeMin, final float rangeMax) {
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
    }
    
    @Override
    public boolean check(final ArrayList<String> args) {
        final Float parsed;
        return (parsed = this.getFloatFromString(args.get(0))) != null && parsed >= this.rangeMin && parsed <= this.rangeMax;
    }
    
    @Override
    public Object getFixedClassedArg(final ArrayList<String> args) {
        return this.getFloatFromString(args.get(0));
    }
    
    private Float getFloatFromString(final String s) {
        Float result;
        try {
            result = Float.parseFloat(s);
        }
        catch (Exception e) {
            return null;
        }
        return result;
    }
}
