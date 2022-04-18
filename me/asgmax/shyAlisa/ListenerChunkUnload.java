//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.event.world.*;
import org.bukkit.event.*;

public class ListenerChunkUnload implements Listener
{
    private shyAlisa context;
    
    public ListenerChunkUnload(final shyAlisa context) {
        this.context = context;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChunkUnload(final ChunkUnloadEvent event) {
        if (this.context.alisa.chunkLoader.shouldChunkBeKeptLoaded(event.getChunk())) {
            event.setCancelled(true);
        }
    }
}
