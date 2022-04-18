//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.event.player.*;
import org.bukkit.event.*;

public class ListenerChatEssChat implements MyChatListener
{
    private shyAlisa context;
    
    public ListenerChatEssChat(final shyAlisa context) {
        this.context = context;
    }
    
    @EventHandler(priority = EventPriority.LOW)
    @Override
    public void onChat(final AsyncPlayerChatEvent event) {
        if (!event.isCancelled()) {
            this.context.messageHandler.handleMessage(event.getPlayer(), event.getMessage());
        }
    }
}
