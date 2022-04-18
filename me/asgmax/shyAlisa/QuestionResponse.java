//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import java.util.*;

public class QuestionResponse
{
    String response;
    ArrayList<ArrayList<String>> questions;
    
    public QuestionResponse(final String response, final ArrayList<ArrayList<String>> questions) {
        this.response = response;
        this.questions = questions;
    }
    
    public QuestionResponse() {
        this.questions = new ArrayList<ArrayList<String>>();
    }
}
