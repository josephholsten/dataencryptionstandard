package edu.okstate.cs.des.tests;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import edu.okstate.cs.des.Sbox;
import edu.okstate.cs.des.Sbox1;

public class SboxTest {
  @Test
	public void testI3() {
    assertEquals(3, Sbox.i("111111"));
    assertEquals(3, Sbox.i("100001"));
	}
  @Test
	public void testI2() {
    assertEquals(2, Sbox.i("111110"));
    assertEquals(2, Sbox.i("100000"));
	}
  @Test
	public void testI1() {
    assertEquals(1, Sbox.i("011111"));
    assertEquals(1, Sbox.i("000001"));
	}
  @Test
	public void testI0() {
    assertEquals(0, Sbox.i("011110"));
    assertEquals(0, Sbox.i("000000"));
	}
	@Test
  public void testJ0() {
    assertEquals( 0, Sbox.j("000000")); 
    assertEquals( 1, Sbox.j("000010")); 
    assertEquals( 2, Sbox.j("000100")); 
    assertEquals( 3, Sbox.j("000110")); 
    assertEquals( 4, Sbox.j("001000")); 
    assertEquals( 5, Sbox.j("001010")); 
    assertEquals( 6, Sbox.j("001100")); 
    assertEquals( 7, Sbox.j("001110"));
  }
  	@Test
    public void testJ8() {
    assertEquals( 8, Sbox.j("010000")); 
    assertEquals( 9, Sbox.j("010010")); 
    assertEquals(10, Sbox.j("010100")); 
    assertEquals(11, Sbox.j("010110")); 
    assertEquals(12, Sbox.j("011000")); 
    assertEquals(13, Sbox.j("011010")); 
    assertEquals(13, Sbox.j("011011")); 
    assertEquals(14, Sbox.j("011100")); 
    assertEquals(15, Sbox.j("011110")); 
  }
  @Test
  public void testS1(){
    assertEquals("0101", Sbox1.call("011011"));
  }
} 