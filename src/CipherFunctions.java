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

/*        public static String xorWithSubkey(tempsubkeyarray, ER) {
          //Now XOR the ER string with the subkey for the round
          for(int i=0;i<48;i++){
              if((tempsubkeyarray[i]==1&&ER[i]==0)||(tempsubkeyarray[i]==0&&ER[i]==1))
                   uberstring=uberstring.concat("1");   
              else
                  uberstring=uberstring.concat("0");
          }
          return uberstring;
        }*/
}