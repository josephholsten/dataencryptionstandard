package edu.okstate.cs.des.tests;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import edu.okstate.cs.des.*;

/*
Known good KPC triple:
   key: 5B5A57676A56676E
 plain: 675A69675E5A6B5A
cipher: 974AFFBF86022D1F
from: http://www.unsw.adfa.edu.au/~lpb/src/DEScalc/lab_des.html

cipher trace:
setKey(5b5a57676a56676e)

encryptDES(675a69675e5a6b5a)
  IP:	L0=ffb2194d, R0=004df6fb
  Rnd1	f(R0=004df6fb, SK1=38 09 1b 26 2f 3a 27 0f ) = 746fc91a
  Rnd2	f(R1=8bddd057, SK2=28 09 19 32 1d 32 1f 2f ) = 7add38ae
  Rnd3	f(R2=7a90ce55, SK3=39 05 29 32 3f 2b 27 0b ) = a5e3f499
  Rnd4	f(R3=2e3e24ce, SK4=29 2f 0d 10 19 2f 1d 3f ) = c5403e1c
  Rnd5	f(R4=bfd0f049, SK5=03 25 1d 13 1f 3b 37 2a ) = 91a62c82
  Rnd6	f(R5=bf98084c, SK6=1b 35 05 19 3b 0d 35 3b ) = 6aeb6bc3
  Rnd7	f(R6=d53b9b8a, SK7=03 3c 07 09 13 3f 39 3e ) = 1e9f7513
  Rnd8	f(R7=a1077d5f, SK8=06 34 26 1b 3f 1d 37 38 ) = 59d1851c
  Rnd9	f(R8=8cea1e96, SK9=07 34 2a 09 37 3f 38 3c ) = 0fc4b474
  Rnd10	f(R9=aec3c92b, SK10=06 33 26 0c 3e 15 3f 38 ) = 8de55e67
  Rnd11	f(R10=010f40f1, SK11=06 02 33 0d 26 1f 28 3f ) = dced7991
  Rnd12	f(R11=722eb0ba, SK12=14 16 30 2c 3d 37 3a 34 ) = 898d0def
  Rnd13	f(R12=88824d1e, SK13=30 0a 36 24 2e 12 2f 3f ) = 34cee3c3
  Rnd14	f(R13=46e05379, SK14=34 0a 38 27 2d 3f 2a 17 ) = 6a4754b1
  Rnd15	f(R14=e2c519af, SK15=38 1b 18 22 1d 32 1f 37 ) = 5bac9dc6
  Rnd16	f(R15=1d4ccebf, SK16=38 0b 08 2e 3d 2f 0e 17 ) = e448c462
  FP:	L=974affbf, R=86022d1f
 returns 974affbf86022d1f

*/

public class DesTest {
	
	@Test
	public void shouldCalculateSimpleE() {
	  int[] input = {
	    0, 0, 0, 0,  0, 0, 0, 0,
	    0, 0, 0, 0,  0, 0, 0, 0,
	    0, 0, 0, 0,  0, 0, 0, 0,
	    0, 0, 0, 0,  0, 0, 0, 1
	  }; 
	  int[] expected = {
	    1, 0, 0,  0, 0, 0,
	    0, 0, 0,  0, 0, 0,
	    0, 0, 0,  0, 0, 0,
	    0, 0, 0,  0, 0, 0,
	    
	    0, 0, 0,  0, 0, 0,
	    0, 0, 0,  0, 0, 0,
	    0, 0, 0,  0, 0, 0,
	    0, 0, 0,  0, 1, 0
	  };
	  int[] result = BitSelection.call(input);
	  
    for (int i = 0; i < 48; i++)
      assertEquals("i:"+ i, expected[i], result[i]);
	}

  @Test
  public void shouldCalculateFullEncryption() {
    // 5B5A57676A56676E
    int[] key = {
      0, 1, 0, 1,  1, 0, 1, 1,
      0, 1, 0, 1,  1, 0, 1, 0,
      0, 1, 0, 1,  0, 1, 1, 1,
      0, 1, 1, 0,  0, 1, 1, 1,
    
      0, 1, 1, 0,  1, 0, 1, 0,
      0, 1, 0, 1,  0, 1, 1, 0,
      0, 1, 1, 0,  0, 1, 1, 1,
      0, 1, 1, 0,  1, 1, 1, 0
    };
    // 675A69675E5A6B5A
    int[] plaintext = {
      0, 1, 1, 0, 0, 1, 1, 1,
      0, 1, 0, 1, 1, 0, 1, 0,
      0, 1, 1, 0, 1, 0, 0, 1,
      0, 1, 1, 0, 0, 1, 1, 1,
      0, 1, 0, 1, 1, 1, 1, 0,
      0, 1, 0, 1, 1, 0, 1, 0,
      0, 1, 1, 0, 1, 0, 1, 1,
      0, 1, 0, 1, 1, 0, 1, 0
    };
    
    // 746fc91a
    int[] expected = {
      1, 0, 0, 1, 0, 1, 1, 1,
      0, 1, 0, 0, 1, 0, 1, 0,
      1, 1, 1, 1, 1, 1, 1, 1,
      1, 0, 1, 1, 1, 1, 1, 1,
      1, 0, 0, 0, 0, 1, 1, 0,
      0, 0, 0, 0, 0, 0, 1, 0,
      0, 0, 1, 0, 1, 1, 0, 1,
      0, 0, 0, 1, 1, 1, 1, 1
    };
    
    assertArrayEquals(expected, CipherFunction.desEncrypt(plaintext, key));
  }
  
  @Test
  public void shouldCalculateFullDecryption() {
    // 5B5A57676A56676E
    int[] key = {
      0, 1, 0, 1,  1, 0, 1, 1,
      0, 1, 0, 1,  1, 0, 1, 0,
      0, 1, 0, 1,  0, 1, 1, 1,
      0, 1, 1, 0,  0, 1, 1, 1,
    
      0, 1, 1, 0,  1, 0, 1, 0,
      0, 1, 0, 1,  0, 1, 1, 0,
      0, 1, 1, 0,  0, 1, 1, 1,
      0, 1, 1, 0,  1, 1, 1, 0
    };
    // 746fc91a
    int[] ciphertext = {
      1, 0, 0, 1, 0, 1, 1, 1,
      0, 1, 0, 0, 1, 0, 1, 0,
      1, 1, 1, 1, 1, 1, 1, 1,
      1, 0, 1, 1, 1, 1, 1, 1,
      1, 0, 0, 0, 0, 1, 1, 0,
      0, 0, 0, 0, 0, 0, 1, 0,
      0, 0, 1, 0, 1, 1, 0, 1,
      0, 0, 0, 1, 1, 1, 1, 1
    };
    // 675A69675E5A6B5A
    int[] expected = {
      0, 1, 1, 0, 0, 1, 1, 1,
      0, 1, 0, 1, 1, 0, 1, 0,
      0, 1, 1, 0, 1, 0, 0, 1,
      0, 1, 1, 0, 0, 1, 1, 1,
      0, 1, 0, 1, 1, 1, 1, 0,
      0, 1, 0, 1, 1, 0, 1, 0,
      0, 1, 1, 0, 1, 0, 1, 1,
      0, 1, 0, 1, 1, 0, 1, 0
    };
    
    assertArrayEquals(expected, CipherFunction.desDecrypt(ciphertext, key));
  }

  @Test
  public void shouldCalculateRoundEndToEnd() {
    // 5B5A57676A56676E
    int[] key = {
      0, 1, 0, 1,  1, 0, 1, 1,
      0, 1, 0, 1,  1, 0, 1, 0,
      0, 1, 0, 1,  0, 1, 1, 1,
      0, 1, 1, 0,  0, 1, 1, 1,
      0, 1, 1, 0,  1, 0, 1, 0,
      0, 1, 0, 1,  0, 1, 1, 0,
      0, 1, 1, 0,  0, 1, 1, 1,
      0, 1, 1, 0,  1, 1, 1, 0
    };
    
    int[][] subkeys = KeySequence.getEncryptKeys(key);
    
        
    // f(R0=004df6fb, SK1=38 09 1b 26 2f 3a 27 0f ) = 746fc91a

    // 004df6fb
    int[] r = {
      0, 0, 0, 0,  0, 0, 0, 0,
      0, 1, 0, 0,  1, 1, 0, 1,
      1, 1, 1, 1,  0, 1, 1, 0,
      1, 1, 1, 1,  1, 0, 1, 1
    };
    int[] finalInts = CipherFunction.roundCipher(r, subkeys[0]);
    // 746fc91a
    int[] expected = {
      0, 1, 1, 1,  0, 1, 0, 0,
      0, 1, 1, 0,  1, 1, 1, 1,
      1, 1, 0, 0,  1, 0, 0, 1,
      0, 0, 0, 1,  1, 0, 1, 0
    };
    assertArrayEquals("result", expected, finalInts);
  }
  
  @Test
  public void shouldGenerateSimpleKey1() {
      int[] input = {
        1, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
                          0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0
      };
    int[][] result = KeySequence.getEncryptKeys(input);

    int[] expected = {
      0, 0, 0, 0, 0, 0, //  1- 6
      0, 0, 0, 0, 0, 0, //  7-12
      0, 0, 0, 0, 0, 0, // 11-18
      0, 1, 0, 0, 0, 0, // 19-24
      0, 0, 0, 0, 0, 0, // 25-30
      0, 0, 0, 0, 0, 0, // 31-36
      0, 0, 0, 0, 0, 0, // 37-42
      0, 0, 0, 0, 0, 0  // 43-48
    };
    assertArrayEquals(expected, result[0]);
  }
  
  @Test
  public void shouldGenerateEncryptKeys() {
    
  }
  
  @Test
  public void shouldPadBitStream() {
    int[] input = {0, 1, 0, 1, 0, 1, 0, 1};
    // pad with 7 = 0x0111 bytes
    int[] expected = {
      0, 1, 0, 1, 0, 1, 0, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
    };
    assertArrayEquals(expected, PKCS5Padding.enpad(input));
  }
  
  @Test
  public void shouldDepadBitStream() {
    // pad with 7 = 0x0111 bytes
    int[] input = {
      0, 1, 0, 1, 0, 1, 0, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
      0, 0, 0, 0, 0, 1, 1, 1,
    };
    
    int[] expected = {0, 1, 0, 1, 0, 1, 0, 1};
    assertArrayEquals(expected, PKCS5Padding.depad(input));
  }
  
  @Test
  public void shouldConvertPlaintextString() {
    String result = CipherFunction.textToBinaryString("testtest");
    assertEquals(
    "01110100"+"01100101"+
    "01110011"+"01110100"+
    "01110100"+"01100101"+
    "01110011"+"01110100", result);
  }
  
  
  @Test
  public void shouldUnconvertPlaintextString() {
    String input =
      "01110100"+"01100101"+
      "01110011"+"01110100"+
      "01110100"+"01100101"+
      "01110011"+"01110100";
    String result = CipherFunction.binaryStringToText(input);
    assertEquals("testtest", result);
  }
  
  @Test
  public void shouldConvertKeyString() {
    String input = "1112131415161718";
    String result = CipherFunction.hexToBinaryString(input);
    assertEquals(
      "00010001"+"00010010"+
      "00010011"+"00010100"+
      "00010101"+"00010110"+
      "00010111"+"00011000", result);
  }
    
  @Test
  public void shouldUnconvertHexString() {
    String input = 
      "00010001"+"00010010"+
      "00010011"+"00010100"+
      "00010101"+"00010110"+
      "00010111"+"00011000";;
    String result = CipherFunction.binaryStringToHex(input);
    assertEquals("1112131415161718", result);
  }
}