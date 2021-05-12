package com.company;

import java.util.*;
import java.lang.StringBuilder;

public class Parser {

    private final ArrayList<Rule> RuleList;
    private final ArrayList<Character> sym;
    private final char [][] matrix;
    private final String input;

    public Parser (ArrayList<Rule> RuleList, char[][] matrix,  ArrayList<Character> sym, String input ){
        this.RuleList=RuleList;
        this.matrix=matrix;
        this.sym = sym;
        this.input=input;
    }

    private StringBuilder StringRefactoring (){
        int begin=0;
        int end=1;
        int split=1;
        StringBuilder string= new StringBuilder(input);
        for (int i = 0; i < string.length()-1 ; i=i+2) {
            int frs=sym.indexOf(string.charAt(begin));
            int snd =sym.indexOf(string.charAt(end));
            char spl = matrix[frs][snd];
            string.insert(split,spl);
            begin=i+2;
            end=begin+1;
            split=begin+1;
        }
        return string;
    }

    public String Parsing(){
        StringBuilder string = new StringBuilder(input);
        string=StringRefactoring();
        return  Parsing(string, Minimum(string));
    }

    private String Parsing(StringBuilder input, String minimum){
        String tmpMin=minimum;
        String result;
        boolean bool =false;
        StringBuilder min = new StringBuilder();
        String[]stringArray = minimum.split("=");
        for(int i = 0; i < stringArray.length; i++) {
            min.append(stringArray[i]);
        }
        for (Rule rule:RuleList) {
            if(rule.getValue().equals(min.substring(2,min.length()-2))){
                min.replace(1,min.length()-1,Character.toString(rule.getKey()));
                int frs = sym.indexOf(min.charAt(0));
                int snd = sym.indexOf(rule.getKey());
                min.insert(1,matrix[frs][snd]);
                frs=sym.indexOf(min.charAt(min.length()-2));
                snd=sym.indexOf(min.charAt(min.length()-1));
                min.insert(min.length()-1,matrix[frs][snd]);
                bool=true;
                break;
            }
        }
        if(!bool) result = "Word is not valuable";
        int index = input.indexOf(tmpMin);
        input.replace(index,index+tmpMin.length(),min.toString());
        System.out.println(input);
        if(input.compareTo(new StringBuilder("$<S>$"))==0) { result = "Word is valuable"; }
        else { return Parsing(input, Minimum(input));}
        return result;
    }

    private String Minimum (StringBuilder string){
        ArrayList<Index> indices = new ArrayList();

        for (int i = 0; i <string.length() ; i++) {
            if(string.charAt(i)=='<'){
                int ind=i;
                for (int j = i+1; j < string.length(); j++) {
                    if (string.charAt(j)=='<') {
                        i=j;
                        i--;
                        break;
                    }
                    if(string.charAt(j)=='>'){
                        indices.add(new Index(ind,j));
                    }
                }
            }

        }

        Collections.sort(indices,Comparator.comparing(Index::getLength));
        int begin=indices.get(0).getBegin()-1;
        int end = indices.get(0).getEnd()+2;
        return string.substring(begin,end);
    }

}
