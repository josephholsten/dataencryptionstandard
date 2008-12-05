package edu.okstate.cs.des.tests;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import edu.okstate.cs.des.Sbox;
import edu.okstate.cs.des.Sbox1;

public class SboxTest {
  @Test
	public void testI() {
    assertEquals(3, Sbox.i(0x3f)); /* 1111111 => 3 */
    assertEquals(3, Sbox.i(0x21)); /* 1000001 => 3 */
    assertEquals(2, Sbox.i(0x3e)); /* 1111110 => 2 */
    assertEquals(2, Sbox.i(0x20)); /* 1000000 => 2 */
    assertEquals(1, Sbox.i(0x1f)); /* 0111111 => 1 */
    assertEquals(1, Sbox.i(0x01)); /* 0000001 => 1 */
    assertEquals(0, Sbox.i(0x1e)); /* 0111110 => 0 */
    assertEquals(0, Sbox.i(0x00)); /* 0000000 => 0 */
	}
	@Test
  public void testJ() {
    assertEquals( 0, Sbox.j(0x00));  /* 000000 => 0 */
    assertEquals( 1, Sbox.j(0x02));  /* 000010 => 1 */
    assertEquals( 2, Sbox.j(0x04));  /* 000100 => 2 */
    assertEquals( 3, Sbox.j(0x06));  /* 000110 => 3 */
    assertEquals( 4, Sbox.j(0x08));  /* 001000 => 4 */
    assertEquals( 5, Sbox.j(0x0A));  /* 001010 => 5 */
    assertEquals( 6, Sbox.j(0x0C));  /* 001100 => 6 */
    assertEquals( 7, Sbox.j(0x0E));  /* 001110 => 7 */
    assertEquals( 8, Sbox.j(0x10));  /* 010000 => 8 */
    assertEquals( 9, Sbox.j(0x12));  /* 010010 => 9 */
    assertEquals(10, Sbox.j(0x14)); /* 010100 => A */
    assertEquals(11, Sbox.j(0x16)); /* 010110 => B */
    assertEquals(12, Sbox.j(0x18)); /* 011000 => C */
    assertEquals(13, Sbox.j(0x1A)); /* 011010 => D */
    assertEquals(13, Sbox.j(0x1B)); /* 011011 => D */
    assertEquals(14, Sbox.j(0x1C)); /* 011100 => E */
    assertEquals(15, Sbox.j(0x1E)); /* 011110 => F */
  }
  @Test
  public void testS1(){
    assertEquals(14, Sbox1.sub(0x00));
    assertEquals( 0, Sbox1.sub(0x01));
    assertEquals( 4, Sbox1.sub(0x02));
    assertEquals(15, Sbox1.sub(0x03));
    assertEquals(13, Sbox1.sub(0x04));
    assertEquals( 7, Sbox1.sub(0x05));
    assertEquals( 1, Sbox1.sub(0x06));
    assertEquals( 4, Sbox1.sub(0x07));
    assertEquals( 2, Sbox1.sub(0x08));
    assertEquals(14, Sbox1.sub(0x09));
    assertEquals(15, Sbox1.sub(0x0A));
    assertEquals( 2, Sbox1.sub(0x0B));
    assertEquals(11, Sbox1.sub(0x0C));
    assertEquals(13, Sbox1.sub(0x0D));
    assertEquals( 8, Sbox1.sub(0x0E));
    assertEquals( 1, Sbox1.sub(0x0F));
    assertEquals( 3, Sbox1.sub(0x10));
    assertEquals(10, Sbox1.sub(0x11));
    assertEquals(10, Sbox1.sub(0x12));
    assertEquals( 6, Sbox1.sub(0x13));
    assertEquals( 6, Sbox1.sub(0x14));
    assertEquals(12, Sbox1.sub(0x15));
    assertEquals(12, Sbox1.sub(0x16));
    assertEquals( 5, Sbox1.sub(0x1B)); /* 011011 */
    assertEquals( 4, Sbox1.sub(0x20));
    assertEquals(15, Sbox1.sub(0x21));
  }
} 