package com.compressor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import com.encryption.*;

public class CompressorClass {
	private Deflater deflater;
	private ByteArrayOutputStream outputStream;
	private byte[] compressedBlock; private int lengthOfCompressedBlock;
	private byte[] output;
	private EncryptorClass encrypt;
	
	
	/**
	 * 
	 * <code>public void compressor()</code> This method performs the compression on the data which is read by the FileReaderclass.
	 * This method then passes the compressed data to <code> EncryptorClass</code> 
	 * 
	 * @param input- array of the block which is read by FileReadertClass.
	 * @param len- total length of the block read by the FileReaderClass.
	 * @param fileName- Name of the file on which the operation is being performed.
	 * @param outputPath- Location for storing the output file.
	 * @param keyPath- location of the encryption key file.
	 * 
	 */
	
	public void compressor(byte[] input,int length,String fileName,String outputPath,String keyPath) 
	{
		
		
		   deflater = new Deflater();  //Creating a deflator class object 	
		   deflater.setInput(input,0,length);  	//setting input to deflator object	
		   outputStream = new ByteArrayOutputStream(length);	//Creating a byte array output stream to write the compressed data into a byte array   
		   deflater.finish();  		//function to indicate that compression should end with the current contents of the input buffer.
		   compressedBlock = new byte[length];   //new byte array of buffer
		   System.out.println("Compression of the block in progress.");
		   while (!deflater.finished()) {  // loop till all content
		    lengthOfCompressedBlock = deflater.deflate(compressedBlock,0,length); // returns the generated code... index  
		    outputStream.write(compressedBlock, 0, lengthOfCompressedBlock);   //writing to output stream
		   }  
		   try {
			outputStream.close();//closing the output stream
			output = outputStream.toByteArray(); //writing the data to output buffer
			System.out.println("Compression completed.");
			encrypt = new EncryptorClass();//creating a encryptor class for encryption
			encrypt.encryptData(output,output.length,fileName,outputPath,keyPath);
		} catch (IOException e) {
			System.out.println("Error :=  IOException occurred in Compressor class.");
			System.out.println(e.getMessage());
		}  
	}
}

