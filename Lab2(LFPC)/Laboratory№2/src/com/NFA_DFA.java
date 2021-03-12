package com;

import java.util.ArrayList;
import java.util.Collections;

public class NFA_DFA {
    static ArrayList<Rule> RuleListOfDFA = new ArrayList();
    public ArrayList<Rule> NFA_conversion(ArrayList<Rule> RuleListOfNFA,ArrayList<Character> Value) {
        addState("0", RuleListOfNFA,Value);
        return RuleListOfDFA;
    }
    static void addState(String to, ArrayList<Rule> RuleListOfNFA,ArrayList <Character> Value){
        ArrayList<String > SetOfTo = ToSeparate(to);
        ArrayList<String > FinalSetOfFrom = new ArrayList<>();
        for(char valueCurrent:Value) {
            ArrayList<String > SetOfFrom = new ArrayList();
             for (String toTest:SetOfTo)
                {
                    for (Rule ruleNFA : RuleListOfNFA) {
                    String toNFA = ruleNFA.getTo();
                    char valueNFA = ruleNFA.getValue();
                    if (toNFA.equals(toTest) && valueNFA == valueCurrent) {
                        String fromNFA = ruleNFA.getFrom();
                        SetOfFrom.add(fromNFA);
                    }
                }
            }
            Collections.sort(SetOfFrom);
            String fromNew=SortAndConcat(SetOfFrom);
            if(fromNew.equals(""))break;
            else{
                RuleListOfDFA.add(new Rule(to,valueCurrent,fromNew));
                FinalSetOfFrom.add(fromNew);
            }
        }
        for (String from: FinalSetOfFrom) {
                if (RuleListOfDFA.stream().noneMatch(p -> p.getTo().equals(from))) { addState(from, RuleListOfNFA,Value); }
        }
    }
    static ArrayList<String>  ToSeparate(String to)
    {
        ArrayList<String > tfState = new ArrayList();
        for(int i=0;i<to.length();i++){
           int tmp=i+1;
           tfState.add(to.substring(i,tmp));
        }
        return tfState;
    }
    static String  SortAndConcat(ArrayList<String> SetOfString) {
        Collections.sort(SetOfString);
        String str="";
        for (String stringTest:SetOfString)
        {
            if (!str.contains(stringTest)) {
                str = str.concat(stringTest);
            }
        }
        return str;
    }

}