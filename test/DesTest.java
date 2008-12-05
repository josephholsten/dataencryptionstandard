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

974affbf86022d1f
*/

public class DesTest {
  @Test
	public void testTrue() {}
	
/*  @Test
  public void shouldCalculateIP() {
    int[] input = {0x675a6967, 0x5e5a6b5a};
    int[] result = DESEncrypt.ip(input);
    assertEquals( String.format("got 0x%08x", result[0]), 0xffb2194d, result[0]);
    assertEquals(String.format("got 0x%08x", result[1]), 0x004df6fb, result[1]);
  }*/
  
	@Test
	public void shouldCalculateSimpleIP() {
	  int[] input = {0x80000000, 0x80000000};
    int[] result = DESEncrypt.ip(input);
    assertEquals(0x00000088, result[0]); /* 5109499 */
    assertEquals(0x00000000, result[1]); /* 65809 */
  }
  
/*  @Test
  public void shouldPerformRound1() {
    int r0  = 0x004df6fb;
    int key = 0x00000000;
    int result = f(r0, key);
    assertEquals(0x746fc91a, result);
  }*/
  
/*  @Test
  public void shouldCalculateZeroBitSelection() {
      int input = 0x0000;
      int[] result = BitSelection.call(input);
      assertEquals(String.format("got 0x%06x", result[0]), 0x00 , result[0]);
      assertEquals(String.format("got 0x%06x", result[1]), 0x00 , result[1]);
      assertEquals(String.format("got 0x%06x", result[2]), 0x00 , result[2]);
      assertEquals(String.format("got 0x%06x", result[3]), 0x00 , result[3]);
      assertEquals(String.format("got 0x%06x", result[4]), 0x00 , result[4]);
      assertEquals(String.format("got 0x%06x", result[5]), 0x00 , result[5]);
      assertEquals(String.format("got 0x%06x", result[6]), 0x00 , result[6]);
      assertEquals(String.format("got 0x%06x", result[7]), 0x00 , result[7]);
  }*/
  
  
/*  @Test
  public void shouldCalculateSimpleBitSelection() {
      int input = 0x80000000;
      int[] result = BitSelection.call(input);
      assertEquals(String.format("got 0x%06x", result[0]), 0x40 , result[0]);
      assertEquals(String.format("got 0x%06x", result[1]), 0x00 , result[1]);
      assertEquals(String.format("got 0x%06x", result[2]), 0x00 , result[2]);
      assertEquals(String.format("got 0x%06x", result[3]), 0x00 , result[3]);
      assertEquals(String.format("got 0x%06x", result[4]), 0x00 , result[4]);
      assertEquals(String.format("got 0x%06x", result[5]), 0x00 , result[5]);
      assertEquals(String.format("got 0x%06x", result[6]), 0x00 , result[6]);
      assertEquals(String.format("got 0x%06x", result[7]), 0x02 , result[7]);
  }*/
  
  @Test
  public void shouldUnpackSimpleInts() {
    int[] input = {0x00000000, 0xFFFFFFFF};
    int[] result = DESEncrypt.unpack_to_bits(input, 32);
    assertEquals(64, result.length);
    assertEquals(1, result[63]);
    assertEquals(0, result[0]);
  }
    @Test
    public void shouldPickBit(){
      int[] result;
      result = DESEncrypt.pickBit(0, 32);
      assertEquals(0, result[0]);
      assertEquals(0, result[1]);
      
      result = DESEncrypt.pickBit(31, 32);
      assertEquals(0, result[0]);
      assertEquals(31, result[1]);
      
      result = DESEncrypt.pickBit(32, 32);
      assertEquals(1, result[0]);
      assertEquals(0, result[1]);
      
      result = DESEncrypt.pickBit(63, 32);
      assertEquals(1, result[0]);
      assertEquals(31, result[1]);
    }
  
  
}