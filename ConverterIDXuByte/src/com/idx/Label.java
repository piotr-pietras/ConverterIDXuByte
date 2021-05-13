package com.idx;

/**
 * Class that describes label loaded from .idx extension file
 */
public class Label implements Object{
	
	String label;
	//Integer label;
	
	byte[] bytes;
	
	Label(byte[] bytes) {
		 this.bytes = bytes;
		 label = Byte.valueOf(bytes[0]).toString();
	}
	
	public void print() {
		System.out.println(label);
	}
}
