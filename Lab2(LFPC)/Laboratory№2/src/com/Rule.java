package com;

public class Rule {
    private final String to;
    private final String from;
    private final char value;
    public Rule( String to,char value,String from)
    {
        this.to=to;
        this.from=from;
        this.value=value;
    }
    public static Rule RuleCreation(String str)
    {
        String to;
        String from;
        char value;
        to = str.substring(2,3);
        value = str.charAt(4);
        from = str.substring(8,9);
        return new Rule(to,value,from);
        // to="0" value "b" from "0"
    }
    public char getValue(){ return value; }
    public String getTo(){
        return to;
    }
    public String getFrom(){
        return from;
    }
}
