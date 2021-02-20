package com;

public class Rule{
    private final String value;
    private final char key;

    public Rule( char key, String value)
    {
        this.key=key;
        this.value=value;
    }
    public static Rule RuleCreation(String str)
    {
        String value="";
        char key=0;
        key = str.charAt(0);
        value = str.substring(2);
        return new Rule(key, value);
    }
    public char getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }
}
