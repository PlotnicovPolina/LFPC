package com.company;

public class Index {
    private final int begin;
    private final int end;
    private final int  length;
    public Index(int begin, int end){
        this.begin=begin;
        this.end=end;
        this.length=end - begin;
    }

    public int getLength(){ return length;}
    public int getBegin(){ return begin;}
    public int getEnd() { return end;}
}
