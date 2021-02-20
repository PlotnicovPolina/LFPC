package com;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Map<Character, ArrayList> grammar = new HashMap<>();
    static String input;
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Rule> RuleList = new ArrayList();
        String path = "F:\\USERS\\Polina\\Рабочий стол\\Работы\\Lab1(LFPC)\\Lab1(LFPC).txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Rule rule=Rule.RuleCreation(line);
            RuleList.add(rule);
        }
        scanner.close();
        System.out.println("The words : ");
        Scanner scanIn = new Scanner(System.in);
        input = scanIn.nextLine();
        scanIn.close();
        System.out.println(input);

        for(Rule rule:RuleList){
            if (grammar.containsKey(rule.getKey())) continue;
            else {
                char name=rule.getKey();
                ArrayList<String> values = new ArrayList();
                for (Rule test:RuleList){
                    if (test.getKey()==name){
                        values.add(test.getValue());
                    }
                }
                grammar.put(name,values);
            }
        }
        Test('S',0);
    }
    public static void Test(char chKey,int index){
        ArrayList <String> grammarTest=grammar.get(chKey);
        char ch = input.charAt(index);
        for(String testValue:grammarTest){
            if (testValue.charAt(0)==ch){
                if (testValue.length()>1){
                char newKey= testValue.charAt(1);
                    if (grammar.containsKey(newKey)) Test(newKey,index+1);
                    else System.out.println("Bad"); return;
                }
                else{
                    if(index==input.length()-1){ //-1
                        System.out.println("Good"); return;
                    }
                    else {System.out.println("Bad"); return;}
                }
            }
        }
        System.out.println("Bad"); return;
    }
}
