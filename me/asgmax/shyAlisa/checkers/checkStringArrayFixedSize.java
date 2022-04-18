//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa.checkers;

import java.util.*;

public class checkStringArrayFixedSize implements IArgumentChecker
{
    int size;
    
    public checkStringArrayFixedSize(final int size) {
        this.size = size;
    }
    
    @Override
    public boolean check(final ArrayList<String> args) {
        return args.size() == this.size;
    }
    
    @Override
    public Object getFixedClassedArg(final ArrayList<String> args) {
        return args;
    }
}
