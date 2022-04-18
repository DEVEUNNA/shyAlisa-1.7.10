//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import java.util.*;
import org.bukkit.*;

public class ChunkLoader
{
    boolean isActive;
    protected HashSet<Chunk> loadedChunks;
    shyAlisa context;
    
    public ChunkLoader(final shyAlisa context) {
        this.context = context;
        this.isActive = false;
        this.loadedChunks = new HashSet<Chunk>();
    }
    
    protected void setActive(final boolean setactive) {
        if (setactive) {
            this.context.registerListenerChunkUnload();
            this.isActive = true;
        }
        else {
            this.context.unregisterListenerChunkUnload();
            this.loadedChunks = new HashSet<Chunk>();
            this.isActive = false;
        }
    }
    
    protected void addChunk(final Chunk chunk) {
        this.loadedChunks.add(chunk);
        if (!this.isActive) {
            this.setActive(true);
        }
    }
    
    protected boolean shouldChunkBeKeptLoaded(final Chunk testedChunk) {
        return this.loadedChunks.contains(testedChunk);
    }
}
