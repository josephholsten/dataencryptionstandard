package edu.okstate.cs.des;

public class LookupTable {
  static public int[] applyLookup(int[] input, int[] table) {
    int[] result = new int[table.length];
    for(int i=0; i<table.length; i++){
      result[i] = input[table[i]-1];
    }
    return result; 
  }
}