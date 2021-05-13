package com.idx;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;

/**
 * IDX class allows to unpack file with .idx_ubyte extension.
 * Converter was created mainly to unpack MINST data pack that
 * contains images of handwritten numbers. Images are 28x28 size.
 */
public class Idx {
	
	public int NUMBER_OF_OBJECTS;
	public int MAGIC_NUMBER;
	public String TYPE;
	
	File file;
	//byte[] bytes;
	public Image[] images;
	public Label[] labels;
	
	public Idx(File file) {
		this.file = file;
		initialize();
	}
	
	/**
	 * Methods loads selected file and checks its objects size and magic number
	 * magic number:
	 * 2051 - contains images 28x28 written in 784bytes
	 * 2049 - contains label written in 1byte
	 */
	private void initialize() {
		try(FileInputStream fis = new FileInputStream(file)){
			byte[] bytes = new byte[8];
			fis.readNBytes(bytes, 0, 8);
			MAGIC_NUMBER = ByteBuffer.wrap(bytes, 0, 4).getInt();
			NUMBER_OF_OBJECTS = ByteBuffer.wrap(bytes, 4, 4).getInt();
		} catch(Exception e){
			System.out.println(e);
		}
		switch (MAGIC_NUMBER) {
		case 2051:
			TYPE = "IDX3";
			System.out.println(toString());
			initializeIDX3();
			downloadDataIDX3();
			break;
		case 2049:
			TYPE = "IDX1";
			System.out.println(toString());
			initializeIDX1();
			downloadDataIDX1();
			break;
		default:
			TYPE = "NULL";
			break;
		}
	}
	
	/**
	 * Method initializes labels' array.
	 */
	private void initializeIDX1() {
		labels = new Label[NUMBER_OF_OBJECTS];
	}
	
	/**
	 * Method downloads labels from file and saves them 
	 * in labels array.
	 */
	private void downloadDataIDX1() {
		System.out.println("Loading labels...");
		try(FileInputStream fis = new FileInputStream(file)) {
			fis.skip(8);
			for(int i = 0; i < NUMBER_OF_OBJECTS; i++) {
				byte[] bytes = new byte[1];
				fis.read(bytes, 0, bytes.length);
				labels[i] = new Label(bytes);
			}
		System.out.println("All " + NUMBER_OF_OBJECTS + " labels has been loaded");	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Method initializes images' array.
	 */
	private void initializeIDX3() {
		images = new Image[NUMBER_OF_OBJECTS];
		try(FileInputStream fis = new FileInputStream(file)){
			byte[] bytes = new byte[8];
			fis.skip(8);
			fis.readNBytes(bytes, 0, 8);
			Image.ROWS = ByteBuffer.wrap(bytes, 0, 4).getInt();
			Image.COLUMNS = ByteBuffer.wrap(bytes, 4, 4).getInt();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Method downloads images from file and saves them 
	 * in images array.
	 */
	private void downloadDataIDX3() {
		System.out.println("Loading images...");
		try(FileInputStream fis = new FileInputStream(file)){
			fis.skip(16);
			for(int i = 0; i < NUMBER_OF_OBJECTS; i++) {		
				byte[] bytes = new byte[Image.PIXELS];
				fis.read(bytes, 0, bytes.length);
				images[i] = new Image(bytes);
			}
			System.out.println("All " + NUMBER_OF_OBJECTS + " labels has been loaded");
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@Override
	public String toString() {
		return new String(
				"\nMAGIC_NUMBER: " + MAGIC_NUMBER + 
				"\nNUMBER_OF_OBJECTS: " + NUMBER_OF_OBJECTS +
				"\nTYPE: " + TYPE +
				"\n");
	}
}
