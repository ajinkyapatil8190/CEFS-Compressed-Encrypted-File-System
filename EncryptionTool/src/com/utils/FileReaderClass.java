package com.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.compressor.CompressorClass;


public class FileReaderClass {
	/**
	 * {@value #lengthToRead} used to specify the buffer size of the block.
	 */
	
	private final int lengthToRead;
	private byte[] input;
	private InputStream inputStream;		
	private CompressorClass compressor; 
	
	/**
	 * class constructor specifying the buffer size.
	 */
	public FileReaderClass() {
		lengthToRead=67108864; //specifying buffer size
	}
	
	/**
	 * The readFile() method,reads a block from the specified file and passes it to the compressor class.
	 * This process continues till the end of file is reached.
	 *  
	 * @param inputPath - Input path of the file.
	 * @param fileName - Name of the file which is being compressed and encrypt.
	 * @param outputPath - Output path of the current file.
	 * @param keyPath - Path of the key file.
	 */
	
	public void readFile(String inputPath,String fileName,String outputPath,String keyPath)  //A function for reading the data into 
	//a byte array and using input stream and get call the compression function with that data.
	{	
		input = new byte[lengthToRead]; //allocating memory to byte array.
		try {
			inputStream = new FileInputStream(inputPath+fileName);	//Create a new object for reading a file in stream
			int lengthOfBytesRead,counterOfBlock=0;									//a length variable to keep track of byte of data read	
			lengthOfBytesRead = inputStream.read(input,0,input.length);//read into byte array input till the length of the block
			counterOfBlock++;
			compressor = new CompressorClass();  // creating a class for compression
			while(lengthOfBytesRead!=-1)								//reading the file till the end
			{
				System.out.println("Reading block number = " + (counterOfBlock)+" For file = "+fileName);
				compressor.compressor(input,lengthOfBytesRead,fileName,outputPath,keyPath);//calling the compressor function with input and length
				input=new byte[lengthToRead];				//allocating memory to input byte array with new length
				lengthOfBytesRead=inputStream.read(input,0,input.length);//Read the data in input byte array. change here
				counterOfBlock++;						//number of blocks read
			}
			System.out.println("\nTotal number of blocks for file = "+ fileName +" = " + counterOfBlock);
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Error :=  File not found exception occurred in ReadFile class . The specified file is not found.");
			System.out.println(e.getMessage());
		}
			catch (IOException e) {
				System.out.println("Error :=  IOException occurred in ReadFile class.");
				System.out.println(e.getMessage());
		} 
	}
	
}  
