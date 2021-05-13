package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import com.idx.Idx;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("IDX-uByte converter");
		System.out.println("-type '-load' to load test IDX3 file that contains MINST images");
		System.out.println("-if images has been loaded type '-image' then"
				+ "enter id from 0 to 59999 to print image on console");
		
		Idx idx = null;
		BufferedReader reader;

		reader = new BufferedReader(new InputStreamReader(System.in));
		String readLine = "";
		while(true) {
			try {
				readLine = reader.readLine();
				switch (readLine) {
				case "-load":
					idx = new Idx(new File(
							"C:\\Users\\Technologia01\\eclipse-workspace\\ConverterIDXuByte\\src\\lib\\idx\\train-images.idx3-ubyte"));
					break;
					
				case "-image":
					System.out.println("Enter id (0 to 59999) of image to be printed");
					System.out.println("If you want to exit image printing type '-1'");
					readLine = reader.readLine();

					while(true) {
						if(readLine != null) {
							Integer id = Integer.valueOf(readLine);
							if(id == -1) break;
							idx.images[id].print();
						}
						readLine = reader.readLine();				
					}
					break;
					
				case "-loadlabels":
					idx = new Idx(new File(
							"C:\\Users\\Technologia01\\eclipse-workspace\\ConverterIDXuByte\\src\\lib\\idx\\train-labels.idx1-ubyte"));
					break;
					
				case "-label":
					System.out.println("Enter id (0 to 59999) of label to be printed");
					System.out.println("If you want to exit label printing type '-1'");
					readLine = reader.readLine();		
					
					while(true) {
						if(readLine != null) {
							Integer id = Integer.valueOf(readLine);
							if(id == -1) break;
							idx.labels[id].print();
						}
						readLine = reader.readLine();				
					}	
					break;
					
				default:
					break;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
