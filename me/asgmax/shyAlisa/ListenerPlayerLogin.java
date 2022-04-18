//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import java.util.*;
import org.bukkit.event.*;

public class ListenerPlayerLogin implements Listener
{
    private shyAlisa context;
    
    public ListenerPlayerLogin(final shyAlisa context) {
        this.context = context;
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player joinedPlayer = event.getPlayer();
        this.context.alisa.addKnownPlayer(joinedPlayer.getName());
        String toRemove = null;
        for (final String name : this.context.alisa.utilCommandsHandler.toSpawnPlayerNames) {
            if (joinedPlayer.getName().equalsIgnoreCase(name)) {
                toRemove = name;
                joinedPlayer.teleport(joinedPlayer.getWorld().getSpawnLocation().add(0.0, 0.5, 0.0));
                this.context.config.set("tospawn-playernames", (Object)this.context.alisa.utilCommandsHandler.toSpawnPlayerNames);
            }
        }
        if (toRemove != null) {
            this.context.alisa.utilCommandsHandler.toSpawnPlayerNames.remove(toRemove);
            this.context.config.set("tospawn-playernames", (Object)this.context.alisa.utilCommandsHandler.toSpawnPlayerNames);
        }
    }
}
