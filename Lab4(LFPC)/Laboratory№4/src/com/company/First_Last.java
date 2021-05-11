package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class First_Last {
    private final ArrayList<Rule> RuleList;
    private final static ArrayList<Rule> FirstList  = new ArrayList();
    private final static ArrayList<Rule> LastList  = new ArrayList();
    private final HashSet<Character> nonTerminalSymbols;

    public First_Last(ArrayList<Rule> RuleList, HashSet nonTerminalSymbols){
        this.RuleList=RuleList;
        this.nonTerminalSymbols = nonTerminalSymbols;
    }

    public ArrayList<Rule> FirstListCreation(){
        HashSet <Character> firstNon= new HashSet<>();
        for(Rule rule:RuleList){
            for (char symbol: nonTerminalSymbols)
            {
                if(!firstNon.contains(symbol)){
                    if (rule.getKey() == symbol) {
                        FirstListCreation(symbol);
                        firstNon.add(symbol);
                        break;
                    }
                }
            }

        }
        return FirstList;
    }

    private void FirstListCreation(char key){
        String firstValue="";
        for(Rule rule:RuleList){
            if(rule.getKey()==key){
                char first=rule.getValue().charAt(0);
                if(!firstValue.contains(Character.toString(first))) {
                    if (first >= 'A' && first <= 'Z') {
                        firstValue=firstValue.concat(Character.toString(first));
                        firstValue = Search(firstValue, first);
                    } else {
                        firstValue=firstValue.concat(Character.toString(first));
                    }
                }
            }
            else continue;
        }
        if(!firstValue.isEmpty()) FirstList.add(new Rule(key,firstValue));
    }

    private  String Search (String firstValue, char key){
        for(Rule rule:RuleList){
            if(rule.getKey() == key){
                char first=rule.getValue().charAt(0);
                if(!firstValue.contains(Character.toString(first))) {
                    if (first >= 'A' && first <= 'Z') {
                        firstValue=firstValue.concat(Character.toString(first));
                        firstValue = Search(firstValue, first);
                    } else {
                        firstValue=firstValue.concat(Character.toString(first));
                    }
                }
            }
            else continue;
        }
        return firstValue;
    }

    public ArrayList<Rule> LastListCreation(){
        HashSet <Character> lastNon= new HashSet<>();
        for(Rule rule:RuleList){
            for (char symbol: nonTerminalSymbols)
            {
                if(!lastNon.contains(symbol)){
                    if (rule.getKey() == symbol) {
                        LastListCreation(symbol);
                        lastNon.add(symbol);
                        break;
                    }
                }
            }
        }
        return LastList;
    }

    private void LastListCreation(char key){
        String lastValue="";
        for(Rule rule:RuleList){
            if(rule.getKey()==key){
                char last=rule.getValue().charAt(rule.getValue().length()-1);
                if(!lastValue.contains(Character.toString(last))) {
                    if (last >= 'A' && last <= 'Z') {
                        lastValue=lastValue.concat(Character.toString(last));
                        lastValue = SearchLast(lastValue, last);
                    } else {
                        lastValue=lastValue.concat(Character.toString(last));
                    }
                }
            }
            else continue;
        }
        if(!lastValue.isEmpty()) LastList.add(new Rule(key,lastValue));
    }

    private  String SearchLast (String lastValue, char key){
        for(Rule rule:RuleList){
            if(rule.getKey() == key){
                char last=rule.getValue().charAt(rule.getValue().length()-1);
                if(!lastValue.contains(Character.toString(last))) {
                    if (last >= 'A' && last <= 'Z') {
                        lastValue=lastValue.concat(Character.toString(last));
                        lastValue = Search(lastValue, last);
                    } else {
                        lastValue=lastValue.concat(Character.toString(last));
                    }
                }
            }
            else continue;
        }
        return lastValue;
    }
}
