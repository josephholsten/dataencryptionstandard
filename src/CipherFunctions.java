package edu.okstate.cs.des;

public class CipherFunctions {
  //Turn subkey into arrary for ease of comaparion
  public static int[] subkeyToArray(String[] subkeys, int roundNumber){
    int tempsubkeyarray[]=new int[48];
    for(int i=0; i<48; i++) {
      String subkey = subkeys[roundNumber];
      tempsubkeyarray[i] = (int) subkey.charAt(i);
    }
    return tempsubkeyarray;
  }

  // XOR the ER string with the subkey for the round
  public static String xorWithSubkey(int[] tempsubkeyarray, int[] er) {
    String result = new String();
    
    for(int i=0; i<48; i++){
      if((tempsubkeyarray[i]==1 && er[i]==0)||(tempsubkeyarray[i]==0 && er[i]==1))
        result = result.concat("1");   
      else
       result = result.concat("0");
    }
    return result;
  }
}
