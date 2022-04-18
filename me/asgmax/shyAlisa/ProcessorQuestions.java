//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\korol\Desktop\Minecraft-Deobfuscator3000-master\1.7.10 stable mappings"!

//Decompiled by Procyon!

package me.asgmax.shyAlisa;

import org.bukkit.entity.*;
import java.util.*;

public class ProcessorQuestions implements IProcessor
{
    private shyAlisa context;
    private ArrayList<QuestionResponse> questionResponses;
    
    public ProcessorQuestions(final shyAlisa context) {
        this.context = context;
        this.fillQuestionsResponses();
    }
    
    public boolean processMessage(final Player player, String message) {
        message = message.toLowerCase();
        for (final QuestionResponse qr : this.questionResponses) {
            if (this.questionResponsePassed(qr, message) && this.questionCooldownExpired(qr)) {
                this.answerQuestionResponse(player, qr);
                this.triggerQuestionCooldown(qr);
                return false;
            }
        }
        return false;
    }
    
    public int getQuestionsCount() {
        return this.questionResponses.size();
    }
    
    private boolean questionCooldownExpired(final QuestionResponse qr) {
        return this.context.alisa.cooldownsHandler.questionsCooldowns.isExpired(this.questionResponses.indexOf(qr));
    }
    
    private void triggerQuestionCooldown(final QuestionResponse qr) {
        this.context.alisa.cooldownsHandler.questionsCooldowns.trigger(this.questionResponses.indexOf(qr));
    }
    
    private void answerQuestionResponse(final Player player, final QuestionResponse qr) {
        this.context.alisa.say(String.format("#c2%s#c1, %s", player.getName(), qr.response));
        final Statistics statistics = this.context.alisa.statistics;
        ++statistics.answers;
    }
    
    private boolean questionResponsePassed(final QuestionResponse qr, final String message) {
        for (final ArrayList<String> block : qr.questions) {
            if (!this.questionBlockPassed(block, message)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean questionBlockPassed(final ArrayList<String> block, final String message) {
        for (final String word : block) {
            if (message.contains(word)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean questionBlockPassedOld(final ArrayList<String> block, final String message) {
        for (final String word : block) {
            if (message.contains(word)) {
                return true;
            }
        }
        return false;
    }
    
    protected void fillQuestionsResponses() {
        this.questionResponses = new ArrayList<QuestionResponse>();
        final ArrayList<String> temp = (ArrayList<String>)mf.readProjectFileLines("questions.txt");
        final ArrayList<Integer> questionIndexes = new ArrayList<Integer>();
        questionIndexes.add(0);
        for (int i = 0; i < temp.size(); ++i) {
            if (temp.get(i).equalsIgnoreCase("***")) {
                questionIndexes.add(i + 1);
            }
        }
        for (final Integer index : questionIndexes) {
            final QuestionResponse qr = this.getNextQuestion(temp, index);
            if (qr != null) {
                this.questionResponses.add(qr);
            }
        }
    }
    
    private QuestionResponse getNextQuestion(final ArrayList<String> arr, final int startingIndex) {
        if (startingIndex >= arr.size()) {
            return null;
        }
        final QuestionResponse result = new QuestionResponse();
        result.response = arr.get(startingIndex);
        for (int i = startingIndex + 1; i < arr.size() && !arr.get(i).contains("***"); ++i) {
            result.questions.add(new ArrayList<String>(Arrays.asList(arr.get(i).split(","))));
        }
        return result;
    }
}
