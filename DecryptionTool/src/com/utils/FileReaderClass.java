package com.utils;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

import com.decryption.DecryptorClass;


public class FileReaderClass {
	private DataInputStream dataInputStream;
	private int lengthOfBlock;
	private byte[] input;
	private int length;
	
	/**
	 * 
	 * @param inputPath- Input path of the file.
	 * @param fileName of the file which is being decompressed and decrypt.
	 * @param outputPathName - Output path of the current file.
	 * @param keyFile - Path of the key file.
	 */
	
	public void readFile(String inputPath,String fileName,String outputPathName,String keyFile)
	{//A function for reading the data into 
		//a byte array and using input stream and get call the compression function with that data.
		try{
		dataInputStream = new DataInputStream(new FileInputStream(inputPath+fileName)); 
		//creating a Data input stream object to read from file in specific format 
		lengthOfBlock = dataInputStream.readInt();	//Read the length of the block in 4 byte  
		System.out.println("Lenght of the block = " + lengthOfBlock);
		byte[] randomIntializationVector = new byte[16];
		int counterForBlock = 0;
		dataInputStream.read(randomIntializationVector);
		input = new byte[lengthOfBlock];	//create a byte array of the exact length of the block
		length = dataInputStream.read(input,0,lengthOfBlock); //read the block in input byte array and the length is stored explicitly
		counterForBlock++;
		while(length!=-1)		//Till the end of file
		{
			System.out.println("Read block no. " + counterForBlock +" For file = "+ fileName);
			DecryptorClass decrypt = new DecryptorClass();//Creating a decryptor class
			decrypt.decryptData(input,length,fileName,randomIntializationVector,outputPathName,keyFile); //Call the decrypt data function 
			lengthOfBlock = dataInputStream.readInt();		//read the data for next block
			
			if(lengthOfBlock == -1)
			{
				break;
			}
			randomIntializationVector = new byte[16];
			dataInputStream.read(randomIntializationVector);
			input = new byte[lengthOfBlock];	//allocating memory for input block
			length=dataInputStream.read(input,0,lengthOfBlock);//input.length);  //Read the block in memory
		}
		}
		catch(EOFException eof){
			System.out.println("End Of file has reached.");
		}
		catch(IndexOutOfBoundsException ioe)
		{
			System.out.println("Error :=  IndexOutOfBoundsException occurred in ReadFile class.");
			System.out.println(ioe.getMessage());
		} catch (IOException e) {
			System.out.println("Error :=  IOException occurred in ReadFile class.");
			System.out.println(e.getMessage());
		} 
	}
}
