package com.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {
	
	private FileOutputStream fileOutputStream;
	/**
	 * This method delivers the final output of decryption and decompression functions.
	 * 
	 * @param output- block containing the decompreesed data.
	 * @param fileName- Name of the file on which the operation is being performed.
	 * @param outputPathName- Location for storing the output file.
	 */
	public void writeToFile(byte[] output,String fileName,String outputPathName)//Function to write to a file
	{
		System.out.println("Writing the data to output file. File name = " + fileName.split(".enc")[0]);
		   try {
			fileOutputStream = new FileOutputStream(outputPathName+fileName.split(".enc")[0],true);
			fileOutputStream.write(output);
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error :=  File not found exception occurred in WriteFile class . The specified file is not found.");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error :=  IOException occurred in WriteFile class.");
			System.out.println(e.getMessage());
		}
		   
	}

}
