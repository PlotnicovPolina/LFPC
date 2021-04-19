package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static HashMap<Character,String> ChomskyRule=new HashMap<>();
    static ArrayList<String> grammar = new ArrayList<>();
    static ArrayList<String> keys=new ArrayList<>();
    static ArrayList<String> alphabet=new ArrayList<>();
    public static void main(String[] args) throws FileNotFoundException {
        String path = "F:\\USERS\\Polina\\Рабочий стол\\Работы\\Lab3(LFPC)\\Lab3.txt";
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine()) {
            grammar.add(scanner.nextLine());
            //System.out.println(grammar.get(grammar.size()-1));
        }
        scanner.close();
        for(int i=0; i< grammar.size();i++){
            if(grammar.get(i).contains("?")) {
                char eps = grammar.get(i).charAt(0);
                grammar.remove(grammar.get(i));
                EpsilonElimination(eps);
            }
        }
        for(int i=0; i< grammar.size();i++) {
            if(grammar.get(i).matches("[A-Z]->[A-Z]")){
                char first=grammar.get(i).charAt(0);
                char last=grammar.get(i).charAt(3);
                grammar.remove(grammar.get(i));
                UnitProduction(first,last);
            }
        }
        KeyNotation('S');
        keys.add("S");
        for (int i=0; i< grammar.size();i++){
            if (!keys.contains(Character.toString(grammar.get(i).charAt(0)))){
                grammar.remove(grammar.get(i));
            }
        }
        CreateAlphabet();
        for(int i=0;i<keys.size();i++){
            if(alphabet.contains(keys.get(i))){
                alphabet.remove(keys.get(i));
            }
        }
        ChomskyNormalForm();
        for (String str:grammar){
            System.out.println(str);
        }
    }
    public static void EpsilonElimination(char eps) {
        for (int i = 0; i < grammar.size(); i++) {
            if (grammar.get(i).contains(Character.toString(eps))) {
                String epsStr=grammar.get(i);
                int num=NumContains(epsStr,Character.toString(eps));
                if(epsStr.startsWith(Character.toString(eps)))
                {
                    if (num>1){
                        String epsFirst= epsStr.substring(1);
                        epsFirst= epsFirst.replaceAll(Character.toString(eps),"");
                        grammar.add(eps +epsFirst);
                    }
                    else continue;
                }
                else grammar.add(epsStr.replaceAll(Character.toString(eps),""));
            }
        }
        for (int i = 0; i < grammar.size(); i++){
            if(grammar.get(i).length()==3){
                char newEps  =grammar.get(i).charAt(0);
                grammar.remove(grammar.get(i));
                EpsilonElimination(newEps);
            }
        }
    }
    public static int NumContains(String str,String rep){
        String[] split = str.split(rep);
        if (str.endsWith(rep))  return split.length;
        return split.length - 1;
    }

    public static void UnitProduction(char first, char last){
        for (int i=0;i<grammar.size();i++){
            if (grammar.get(i).charAt(0)==last){
                grammar.add(Character.toString(first).concat("->"+grammar.get(i).substring(3)));
            }
        }
    }
    public static void KeyNotation (char k){
        for(int i=0;i<grammar.size();i++)
        {
            if (grammar.get(i).charAt(0)==k){
            ArrayList<String> spl = new ArrayList(Arrays.asList(grammar.get(i).substring(3).split("[^A-Z]*")));
            spl.removeAll(Collections.singleton(""));
                for (String splStr:spl){
                    if ((!spl.isEmpty())&&(!keys.contains(splStr))) {
                        keys.add(splStr);
                        KeyNotation(splStr.charAt(0));
                    }
                }
            }
        }
    }
    public static void ChomskyNormalForm(){
        for(int i=0;i< grammar.size();i++){
            String  gr= grammar.get(i);
            String str=gr.substring(3);
            if (str.matches("(?:([a-z])|([A-Z]))*")) {
                for (int j = 0; j < str.length(); j++) {
                    if(Character.isLowerCase(str.charAt(j))&& (str.length()>1)) {
                        char low=str.charAt(j);
                        if(ChomskyRule.containsKey(low)){
                            grammar.add(gr.replace(Character.toString(low),ChomskyRule.get(low)));
                        }
                        else{
                            String alpha=alphabet.get(0);
                            alphabet.remove(0);
                            grammar.add(alpha.concat("->"+low));
                            grammar.add(gr.replace(low,alpha.charAt(0)));
                            ChomskyRule.put(low,alpha);
                        }
                        grammar.remove(grammar.get(i));
                        i--;
                    }
                }
            }
        }
    }
    public static void CreateAlphabet(){
        for (char i='A';i<='Z';i++) {
            alphabet.add(Character.toString(i));
        }
    }
}