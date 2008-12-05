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
            
           
            String uberstring=new String();
            //Obsolete...i think
            /*for(int i=0;i<48;i++){
                uberstring=uberstring.concat(Integer.toString(ER[i]));
            }
            System.out.println(uberstring);*/
            
            //Turn subkey into arrary for ease of comaparion
/*            int tempsubkeyarray[]=new int[48];
            for(int i=0; i<48; i++)
                tempsubkeyarray[i]=(int)subkeys[roundNumber].charAt(i);
*/
            int[] tempsubkeyarray = CipherFunctions.subkeyToArray(subkeys, roundNumber);
            
            //Now XOR the ER string with the subkey for the round
            for(int i=0;i<48;i++){
                if((tempsubkeyarray[i]==1&&ER[i]==0)||(tempsubkeyarray[i]==0&&ER[i]==1))
                     uberstring=uberstring.concat("1");   
                else
                    uberstring=uberstring.concat("0");
            }
            
            //The final 32 bit string from all s functions
            String finals=new String();
            //The s functions
            
            int s1[][]={
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6 ,2, 11, 15, 12, 9, 7 ,3, 10, 5, 0},
                {15, 12, 8 ,2 ,4 ,9 ,1, 7 ,5 ,11 ,3, 14, 10, 0 ,6, 13}
                
            };
            //Create a substring of 6bits
            String string1=uberstring.substring(0,5);
            
            //Create the two table values x,y for the x axis, y axis 
            //respectively
            
            String tempx,tempy=new String();
            
            //The first and last bits
            tempx=string1.substring(0,1);
            tempx=tempx.concat(string1.substring(4,5));
            
            tempy=string1.substring(1,4);
            
            
            int x,y;
            x=Integer.parseInt(tempx,2);
            y=Integer.parseInt(tempx,2);
            
            //Look up the four bit value
            int lookup=s1[x][y];
            
            //Concatenate the lookupvalue to finals
            finals=finals.concat(Integer.toBinaryString(lookup));
            
            
            
               
            
            int s2[][]={
                {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9} 
            };
                        //Create a substring of 6bits
            String string2=uberstring.substring(6,11);
            
            //Create the two table values x,y for the x axis, y axis 
            //respectively
            
            tempx=new String();
            tempy=new String();
            
            //The first and last bits
            tempx=string2.substring(0,1);
            tempx=tempx.concat(string2.substring(4,5));
            
            tempy=string2.substring(1,4);
            
            
            
            x=Integer.parseInt(tempx,2);
            y=Integer.parseInt(tempx,2);
            
            //Look up the four bit value
            lookup=s2[x][y];
            
            //Concatenate the lookupvalue to finals
            finals=finals.concat(Integer.toBinaryString(lookup));
            
            int s3[][]={
                {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
            };
            String string3=uberstring.substring(12,17);
            
            //Create the two table values x,y for the x axis, y axis 
            //respectively
            
            tempx=new String();
            tempy=new String();
            
            //The first and last bits
            tempx=string3.substring(0,1);
            tempx=tempx.concat(string3.substring(4,5));
            
            tempy=string3.substring(1,4);
            
            
            
            x=Integer.parseInt(tempx,2);
            y=Integer.parseInt(tempx,2);
            
            //Look up the four bit value
            lookup=s3[x][y];
            
            //Concatenate the lookupvalue to finals
            finals=finals.concat(Integer.toBinaryString(lookup));
            
            int s4[][]={
                {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                {13, 8, 11, 5, 6, 15, 0, 3 ,4 ,7 ,2 ,12, 1, 10, 14, 9},
                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14},
            };
            
                        String string4=uberstring.substring(18,23);
            
            //Create the two table values x,y for the x axis, y axis 
            //respectively
            
            tempx=new String();
            tempy=new String();
            
            //The first and last bits
            tempx=string4.substring(0,1);
            tempx=tempx.concat(string4.substring(4,5));
            
            tempy=string4.substring(1,4);
            
            
            
            x=Integer.parseInt(tempx,2);
            y=Integer.parseInt(tempx,2);
            
            //Look up the four bit value
            lookup=s4[x][y];
            
            //Concatenate the lookupvalue to finals
            finals=finals.concat(Integer.toBinaryString(lookup));
            
            
            int s5[][]={
                {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
            };
                        String string5=uberstring.substring(23,28);
            
            //Create the two table values x,y for the x axis, y axis 
            //respectively
            
            tempx=new String();
            tempy=new String();
            
            //The first and last bits
            tempx=string5.substring(0,1);
            tempx=tempx.concat(string5.substring(4,5));
            
            tempy=string5.substring(1,4);
            
            
            
            x=Integer.parseInt(tempx,2);
            y=Integer.parseInt(tempx,2);
            
            //Look up the four bit value
            lookup=s5[x][y];
            
            //Concatenate the lookupvalue to finals
            finals=finals.concat(Integer.toBinaryString(lookup));
            
            
            int s6[][]={
                {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13} 
            };
                        String string6=uberstring.substring(29,34);
            
            //Create the two table values x,y for the x axis, y axis 
            //respectively
            
            tempx=new String();
            tempy=new String();
            
            //The first and last bits
            tempx=string6.substring(0,1);
            tempx=tempx.concat(string6.substring(4,5));
            
            tempy=string6.substring(1,4);
            
            
            
            x=Integer.parseInt(tempx,2);
            y=Integer.parseInt(tempx,2);
            
            //Look up the four bit value
            lookup=s6[x][y];
            
            //Concatenate the lookupvalue to finals
            finals=finals.concat(Integer.toBinaryString(lookup));
            
            int s7[][]={
                {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6 ,8, 0, 5, 9, 2},
                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
            };
            
                        String string7=uberstring.substring(35,40);
            
            //Create the two table values x,y for the x axis, y axis 
            //respectively
            
            tempx=new String();
            tempy=new String();
            
            //The first and last bits
            tempx=string7.substring(0,1);
            tempx=tempx.concat(string7.substring(4,5));
            
            tempy=string7.substring(1,4);
            
            
            
            x=Integer.parseInt(tempx,2);
            y=Integer.parseInt(tempx,2);
            
            //Look up the four bit value
            lookup=s7[x][y];
            
            //Concatenate the lookupvalue to finals
            finals=finals.concat(Integer.toBinaryString(lookup));
            
            
            int s8[][]={
                {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5 ,0 ,12, 7},
                {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
            };
            
                        String string8=uberstring.substring(6,12);
            
            //Create the two table values x,y for the x axis, y axis 
            //respectively
            
            tempx=new String();
            tempy=new String();
            
            //The first and last bits
            tempx=string8.substring(0,1);
            tempx=tempx.concat(string8.substring(4,5));
            
            tempy=string1.substring(1,4);
            
            
            
            x=Integer.parseInt(tempx,2);
            y=Integer.parseInt(tempx,2);
            
            //Look up the four bit value
            lookup=s8[x][y];
            
            //Concatenate the lookupvalue to finals
            finals=finals.concat(Integer.toBinaryString(lookup));
            
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
