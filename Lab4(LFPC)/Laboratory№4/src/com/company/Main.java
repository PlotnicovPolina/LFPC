package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static String input;
    static ArrayList<Rule> RuleList = new ArrayList();
    static ArrayList<Rule> FirstList = new ArrayList<>();
    static ArrayList<Rule> LastList = new ArrayList<>();
    static HashSet<Character> nonTerminalSymbols= new HashSet<>();
    static HashSet<Character> symbols= new HashSet<>();
    public static void main(String[] args) throws FileNotFoundException {
        String path = "D:\\Работы\\Lab4(LFPC)\\Lab4.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.startsWith("V")){
               for(int i= line.indexOf('[')+1; i<line.length()-1;i++)
               {
                   if(line.charAt(i)==',') continue;
                   symbols.add(line.charAt(i));
               }
               symbols.add('$');

            }
            else{
                Rule rule = Rule.RuleCreation(line);
                nonTerminalSymbols.add(rule.getKey());
                symbols.add(rule.getKey());
                RuleList.add(rule);
            }

        }
        scanner.close();

        First_Last listOfFirst = new First_Last(RuleList,nonTerminalSymbols);
        FirstList = listOfFirst.FirstListCreation();
        First_Last listOfLast = new First_Last(RuleList,nonTerminalSymbols);
        LastList = listOfLast.LastListCreation();

        ArrayList<Character> sym=new ArrayList<>();
        sym.addAll(symbols);

        Matrix matrix = new Matrix(RuleList,FirstList,LastList,sym);
        char[][] mainMatrix = matrix.MatrixCreation();
        for (int i = 0; i < mainMatrix.length; i++) {
            for (int j = 0; j < mainMatrix.length; j++) {
                System.out.print(mainMatrix[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("The words : ");
        Scanner scanIn = new Scanner(System.in);
        input = scanIn.nextLine();
        scanIn.close();

        input="$"+input+"$";
        Parser parser =  new Parser( RuleList, mainMatrix, sym, input);
        System.out.println(parser.Parsing());
    }
}
