package edu.okstate.cs.des;

public class LookupTable {
  /**
   * Return a binary int array by mapping input
   * bits to output bits from the lookup table
   */
  static protected int[] applyLookup(int[] input, int[] table) {
    int[] result = new int[table.length];
    for(int i=0; i<table.length; i++){
      result[i] = input[table[i]-1];
    }
    return result; 
  }
}