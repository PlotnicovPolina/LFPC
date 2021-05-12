package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Matrix {
    private final ArrayList<Rule> RuleList;
    private final ArrayList<Rule> FirstList;
    private final ArrayList<Rule> LastList;
    private final ArrayList<Character> sym;

    public Matrix (ArrayList<Rule> RuleList, ArrayList<Rule> FirstList, ArrayList<Rule> LastList, ArrayList sym){
        this.RuleList=RuleList;
        this.FirstList=FirstList;
        this.LastList=LastList;
        this.sym = sym;
    }

    public char[][] MatrixCreation(){
        int length = sym.size();

        char [][] matrix = new char[length][length];
        for (char[] row: matrix) {
            Arrays.fill(row, ' ');
        }

        int $=sym.indexOf('$');
        for (int i = 0; i < matrix.length ; i++) {
            matrix [i][$]='>';
        }
        for (int i = 0; i < matrix.length ; i++) {
            matrix [$][i]='<';
        }

        FirstRule(matrix,sym);
        SecondRule(matrix,sym);
        ThirdRule(matrix,sym);

        return matrix;
    }

    private void FirstRule(char[][] matrix, ArrayList <Character> sym){
        System.out.println("First rule:");
        for(Rule rule:RuleList) {
            if (rule.getValue().length()>1){
                int begin=0;
                int end= 1;
                while (end != rule.getValue().length())
                {
                    int frs=sym.indexOf(rule.getValue().charAt(begin));
                    int snd=sym.indexOf(rule.getValue().charAt(end));
                    matrix [frs][snd]='=';
                    System.out.println(rule.getValue().charAt(begin) + "=" + rule.getValue().charAt(end));
                    begin++;
                    end++;
                }
            }
        }
    }

    private void SecondRule(char[][] matrix, ArrayList <Character> sym){
        System.out.println("Second rule:");
        for(Rule rule:RuleList) {
            if (rule.getValue().length() > 1) {
                int begin=0;
                int end= 1;
                while (end != rule.getValue().length())
                {
                    char f=rule.getValue().charAt(begin);
                    char s=rule.getValue().charAt(end);
                    if(((f >= 'a' && f <= 'z')||(f >= 'A' && f <= 'Z'))&&(s >= 'A' && s <= 'Z'))
                    {
                        int frs= sym.indexOf(f);
                        for(Rule firstList:FirstList){
                            if(firstList.getKey()==s){
                                for (int i = 0; i < firstList.getValue().length() ; i++) {
                                    int snd= sym.indexOf(firstList.getValue().charAt(i));
                                    matrix [frs][snd]='<';
                                    System.out.println(f + "<" + firstList.getValue().charAt(i));
                                }
                            }
                        }
                    }
                    begin++;
                    end++;
                }
            }
        }
    }

    private void ThirdRule(char[][] matrix, ArrayList <Character> sym){
        System.out.println("Third rule:");
        for(Rule rule:RuleList) {
            if (rule.getValue().length() > 1) {
                int begin=0;
                int end= 1;
                while (end != rule.getValue().length())
                {
                    char f=rule.getValue().charAt(begin);
                    char s=rule.getValue().charAt(end);
                    if((f >= 'A' && f <= 'Z')&&(s >= 'a' && s <= 'z'))
                    {
                        int snd= sym.indexOf(s);
                        for(Rule lastList:LastList){
                            if(lastList.getKey()==f){
                                for (int i = 0; i < lastList.getValue().length() ; i++) {
                                    int frs= sym.indexOf(lastList.getValue().charAt(i));
                                    matrix [frs][snd]='>';

                                    System.out.println( lastList.getValue().charAt(i) + ">" + s);
                                }
                            }
                        }
                    }
                    if((f >= 'A' && f <= 'Z')&&(s >= 'a' && s <= 'z'))
                    {
                        for(Rule firstList:FirstList) {
                            if (firstList.getKey() == s) {
                                for (int i = 0; i < firstList.getValue().length(); i++) {
                                    if( firstList.getValue().charAt(i)>='a' && firstList.getValue().charAt(i)<='z'){
                                        int snd = sym.indexOf(firstList.getValue().charAt(i));
                                        for(Rule lastList:LastList){
                                            if(lastList.getKey()==f){
                                                for (int j = 0; j < lastList.getValue().length() ; j++) {
                                                    int frs= sym.indexOf(lastList.getValue().charAt(j));
                                                    matrix [frs][snd]='>';
                                                    System.out.println( lastList.getValue().charAt(j) + ">" + firstList.getValue().charAt(i));
                                                }
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                    begin++;
                    end++;
                }
            }
        }
    }


}

