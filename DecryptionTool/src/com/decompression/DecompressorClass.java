package com.decompression;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import com.utils.WriteFile;

public class DecompressorClass {
	
	
	private Inflater inflater;
	private ByteArrayOutputStream outputStream;
	private byte[] buffer;
	private int count;
	private byte[] output;
	private WriteFile writer;
	
	
	/**
	 * This method does the decompression of decrypted data.
	 *  
	 * @param input- array of the block containing decrypted data.
	 * @param fileName- Name of the file on which the operation is being performed.
	 * @param outputPathName- Location for storing the output file.
	 * 
	 */
	
	
	public void decompressor(byte[] input,String fileName,String outputPathName)
	{	   //Decompressor method
		   inflater = new Inflater();	//Creating an instance of Inflator class    
		   inflater.setInput(input,0,input.length);  //setting the input to inflator object
		   outputStream = new ByteArrayOutputStream(input.length);  //Creating a byte array output stream to write the uncompressed data into a byte array
		   buffer = new byte[input.length];	//allocating byte array memory  
		   System.out.println("Decompressing the data in memory.");
		   while (!inflater.finished()) {  	//while the input is completed.
		    try {
				count = inflater.inflate(buffer,0,input.length);
			} catch (DataFormatException e) {
				
				System.out.println("Error :=  DataFormatException occurred in decompressor class.");
				System.out.println(e.getMessage());
			}  //inflate function to uncompress the data
		    outputStream.write(buffer, 0, count); 				//write the data to buffer variable
		   }  
		   try {
			outputStream.close();
		} catch (IOException e) {
	
			System.out.println("Error :=  IOException occurred in decompressor class.");
			System.out.println(e.getMessage());
		}  //closing the output stream
		   output = outputStream.toByteArray();	//write the uncompressed data into byte array
		   writer = new WriteFile();
		   writer.writeToFile(output,fileName,outputPathName);
		 
	}

}
	
