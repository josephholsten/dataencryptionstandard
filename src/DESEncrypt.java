package edu.okstate.cs.des;
/***********************************************
 * This is a test version of the encryption code
 * for plaintext of a set size of 64bits
 * @author Jason
 */
public class DESEncrypt {
    
    public static void main(String[]args){
        //The hex key that the key sheduler uses to create
        //the sub keys
        String key="1112131415161718";
        
        //The binary Representation of the key
        String binaryKey[]=new String[8];
        
        //The plaintext to decrypted
        String plaintext=new String("testtest");
        
        //The binary representation of the plaintext 
        String binaryText[]=new String[8];
        
        //Convert the plaintext into its binary equivalent
         for(int i=0;i<plaintext.length();i++){
            String temp1=Integer.toString((int)plaintext.charAt(i),2);
            String temp2=new String();
            int tempcount=temp1.length();
            while(tempcount<8){
                temp2=temp2.concat("0");
                tempcount++;
            }
            temp1=temp2.concat(temp1);
            binaryText[i]=temp1;
           
         }
        
        //Convert the key into its binary equivalent
        int counter=0;
        for(int i=0;i<key.length();i+=2){
            String hexToConvert=key.substring(i,i+2);
            System.out.println(hexToConvert);
            int asciiVal=Integer.parseInt( hexToConvert.trim(), 16);
            String temp1=Integer.toString(asciiVal,2);
            String temp2=new String();
            int tempcount=temp1.length();
            while(tempcount<8){
                temp2=temp2.concat("0");
                tempcount++;
            }
            temp1=temp2.concat(temp1);
            binaryKey[counter]=temp1;
            
            counter++;
            
         }
        
        //Concatentate the binary forms of the key and plaintext
        //into a single text file
        String binFullText=new String();
        String binFullKey=new String();
        for(int i = 0;i<binaryText.length;i++){
          System.out.println(binaryText[i]);
           binFullText= binFullText.concat(binaryText[i]);
        }
        
        for(int i =0;i<binaryKey.length;i++){
           //System.out.println(binaryKey[i]);
           binFullKey= binFullKey.concat(binaryKey[i]);
        }
        
        //Debug print
        System.out.println(binFullText);
        System.out.println(binFullKey);
        
        //Convert the strings into arrays where each
        //Entry represents an individual bit;
        int binArrText[]=new int[64];
        int binArrKey[]=new int[64];
        for(int i=0;i<64;i++){
            binArrText[i]=(int)binFullText.charAt(i)-48;
            binArrKey[i]=(int)binFullKey.charAt(i)-48;
        }
        
        for(int i=0;i<64;i++)
            System.out.print(binArrKey[i]);
        
        int IParray[]=new int[64];
        //Initial permutation
        int IP[]={58, 50 ,42, 34 ,26 ,18, 10, 2,
                  60, 52, 44, 36, 28, 20, 12, 4,
                  62, 54, 46, 38, 30, 22, 14, 6,
                  64, 56, 48, 40, 32, 24, 16, 8,
                  57, 49, 4,1 ,33, 25, 17, 9, 1,
                  59, 51, 43, 35, 27, 19 ,11, 3,
                  61, 53 ,45 ,37, 29, 21, 13, 5,
                  63, 55, 47, 39, 31, 23, 15, 7};
        
        for(int i=0;i<IP.length-1;i++){
            System.out.println(i);
             IParray[i]=binArrText[IP[i]-1];
             
        }
        //Placeholder for String array where subkeys are stored
        String subkeys[]=new String[16];
        //key scheduler goes here
        for(int i=0; i<16; i++)
          subkeys[i] = "000000000000000000000000000000000000000000000000";
        
        /*/*Debug Print
        for(int i=0;i<IP.length-1;i++)
            System.out.print(IParray[i]);
        System.out.println();*/
        int roundNumber=0;
        //Create the left and right sides
        int L[]=new int[32];
        int R[]=new int[32];
        
        for(int i=0;i<32;i++){
            L[i]=IParray[i];
        }
        int Rcount=0;
        for (int i=32;i<64;i++){
            R[Rcount]=IParray[i];
            Rcount++;
        }    
        
        /*/Debug prints
        for(int i=0;i<L.length;i++)
            System.out.print(L[i]);
        System.out.println();
        
        for(int i=0;i<R.length;i++)
            System.out.print(R[i]);
        System.out.println();*/
        
        //The f function--------------------------------------------------------
        
        //The E function
        int E[]={32, 1, 2, 3, 4, 5,
                 4, 5, 6, 7, 8, 9,
                 8, 9, 10, 11, 12, 13,
                 12, 13, 14, 15, 16, 17,
                 16, 17, 18, 19, 20, 21,
                 20, 21, 22, 23, 24, 25,
                 24, 25, 26, 27, 28, 29,
                 28, 29, 30, 31, 32, 1
            
        };
        while(roundNumber<16){
            //Perform the E function on R
            int ER[]=new int[48];
            for(int i=0;i<48;i++)
                ER[i]=R[E[i]-1];
            
           
            int[] tempsubkeyarray = CipherFunctions.subkeyToArray(subkeys, roundNumber);
            
            // XOR subkey with bit-selection to get all b's
            String bs = CipherFunctions.xorWithSubkey(tempsubkeyarray, ER);
            
            //The final 32 bit string from all s functions
            String finals= Sbox.allSboxes(bs);
            
            //Now the s values are concatenated together, we P
            //function them
            
            //P function here
            
            //Now we swap the L and R
            int tempL[]=L;
            L=R;
            R=tempL;
            
            roundNumber++;
            
        }//while   
            
        }

}
