package com.utils;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFile {  //Class for writing to a file
	
	/**
	 * Method <code>writeToFile</code> writes the compressed and encrypted file to the specified output path.
	 * 
	 * @param encryptedData- array which is containing the encrypted data.
	 * @param fileName- Name of the file on which the operation is being performed.
	 * @param randomIntializationVector- initialization vector generated in the <code>EncryptorClass</code>
	 * @param outputPath- Location for storing the output file.
	 * 
	 */
	
	private DataOutputStream dataOutputStream; 
	public void writeToFile(byte[] encryptedData,String fileName,byte[] randomIntializationVector,String outputPath)  //Function to write to a file
	{ 
		try {
		    dataOutputStream = new DataOutputStream(new FileOutputStream(outputPath+fileName+".enc",true)); 
		    //creating an object of data output stream to write in a specifying the way in which file is to be written 
	        System.out.println("Lenght of the final block = " + encryptedData.length); //writing the length of block
	        System.out.println("Writing the block to output file.");
				dataOutputStream.writeInt(encryptedData.length);
				dataOutputStream.flush();		//clearing the stream
			    dataOutputStream.write(randomIntializationVector);
			    dataOutputStream.flush();
			    dataOutputStream.write(encryptedData);	//writing the block to the file
			    dataOutputStream.flush();
			    dataOutputStream.close();	//closing the output stream
			} catch (IOException e) {
				System.out.println("Error :=  IOException occurred in WriteFile class.");
				System.out.println(e.getMessage());
			}
	       
	}
}
