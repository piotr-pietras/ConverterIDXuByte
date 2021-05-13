package com.idx;

/**
 * Class that describes image loaded from .idx extension file
 */
public class Image implements Object{
	
	public static int ROWS = 28; //number of pixels in row
	public static int COLUMNS = 28; //number of pixels in row
	public static int PIXELS = ROWS*COLUMNS; //total number of pixels
	
	byte[] bytes;
	
	Image(byte[] bytes){
		 this.bytes = bytes;
	}
	
	Image(){}	
	
	/**
	 * Method prints image on the console.
	 * Single character is considered as a pixel. 
	 */
	public void print() {
		int pixel = 0;
		for(int i = 0; i < COLUMNS; i++) {
			System.out.print("\n");
			for(int j = 0; j < ROWS; j++) {
				int a = bytes[pixel] & 0xff;
				pixel++;
				if(a>0 && a<=100) System.out.print("o");
				else if(a>100 && a<=200) System.out.print("a");
				else if(a>200) System.out.print("@");
				else System.out.print("-");
			}	
		}
		System.out.print("\n");
	}
}
