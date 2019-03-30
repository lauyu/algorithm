package com.test;

public class ShiftTest {
	private static int RESIZE_STAMP_BITS = 16;
	private static final int RESIZE_STAMP_SHIFT = 32 - RESIZE_STAMP_BITS;
	
	public static void main(String[] args) {
		int i = 0x80000003;
//		System.out.println(Integer.toBinaryString(i));
//		System.out.println(Integer.toBinaryString(i>>1));
//		System.out.println(Integer.toBinaryString(i>>>1));
//		System.out.println(Integer.toBinaryString(0x41000000));
//		System.err.println(Integer.numberOfLeadingZeros(0x71000000));
//		System.err.println(Integer.numberOfLeadingZeros(0x41000000));
//		int rs = resizeStamp(16);
//		System.out.println(Integer.toBinaryString(rs));
//		System.out.println((Integer.toBinaryString(rs << RESIZE_STAMP_SHIFT)));
//		System.out.println((Integer.toBinaryString((rs << RESIZE_STAMP_SHIFT) + 2)));
//		
//		System.out.println(Integer.toBinaryString(0x01000001<<7));
//		System.out.println(Integer.toBinaryString((0x01000001<<7)+2));
//		System.out.println((0x01000001<<7));
//		System.out.println(((0x01000001<<7))+2);
		
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(-1<<29));
		System.out.println(-1<<29);
		System.out.println(0<<29);
		System.out.println(Integer.toBinaryString(0<<29));
		System.out.println(Integer.toBinaryString(1<<29));
		System.out.println(Integer.toBinaryString(1<<31));
		
		
		
		
		
		
		
		
	}

	
	static final int resizeStamp(int n) {
		System.out.println(Integer.toBinaryString(Integer.numberOfLeadingZeros(n)));
		System.out.println(Integer.toBinaryString((1 << (RESIZE_STAMP_BITS - 1))));
        return Integer.numberOfLeadingZeros(n) | (1 << (RESIZE_STAMP_BITS - 1));
    }
}
