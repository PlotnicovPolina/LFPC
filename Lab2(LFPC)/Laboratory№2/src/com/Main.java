package com;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Rule> RuleListOfNFA = new ArrayList();
        ArrayList<Character> Value = new ArrayList();
        String path = "F:\\USERS\\Polina\\Рабочий стол\\Работы\\Lab2(LFPC)\\Lab2(LFPC).txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (i == 0) {
                for (int j = 0; j < line.length(); j++) {
                    char ch = line.charAt(j);
                    if (ch == '{' || ch == ',') {
                        Value.add(line.charAt(j + 1));
                    }
                }
            } else {
                Rule rule = Rule.RuleCreation(line);
                RuleListOfNFA.add(rule);
            }
            i++;
        }
        scanner.close();
        NFA_DFA ND = new NFA_DFA();
        ArrayList<Rule> RuleListOfDFA = ND.NFA_conversion(RuleListOfNFA,Value);
        for (Rule ruleOfDFA : RuleListOfDFA) {
            System.out.println("(q"+ruleOfDFA.getTo() + "," + ruleOfDFA.getValue() + ")=q" + ruleOfDFA.getFrom());
        }
    }
}
