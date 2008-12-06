package edu.okstate.cs.des;
/***********************************************
 * This is a test version of the encryption code
 * for plaintext of a set size of 64bits
 * @author Jason
 */
public class DESDecrypt {
    
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
            
        CipherFunction.desEncrypt(binArrText, binArrKey);
      }
}