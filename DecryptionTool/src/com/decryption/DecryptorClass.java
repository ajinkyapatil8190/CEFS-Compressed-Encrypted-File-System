package com.decryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.decompression.DecompressorClass;

public class DecryptorClass {
	private byte[] key;
	private Cipher cipher;
	private SecretKeySpec secretKey;
	private DecompressorClass decompressor;
	
	/**
	 * This method decrypts the block and passes it to the <code> DecompressorClass </code>.
	 * 
	 * @param input- Array which containing cipher text.
	 * @param length- total length of the decrypted block.
	 * @param fileName- Name of the file on which the operation is being performed.
	 * @param randomInitializationVector- initialiazation vector for decryption.
	 * @param outputPathName- Location for storing the output file.
	 * @param keyFile- Location of the decryption key file.
	 */
	
	
	public void decryptData(byte[] input,int length,String fileName,byte[] randomInitializationVector,String outputPathName,String keyFile) 
	{		//Decrypt data function
		key=new byte[16];
		key=this.getKey(keyFile);
		this.cipher=this.init(randomInitializationVector);
		byte[] decryptedData;
		System.out.println("Decrypting the read data block.");
		try {
			decryptedData = cipher.doFinal(input,0,length);//Dofinal method to decrypt the data.
			decompressor = new DecompressorClass();	//Decompressor class object
	        decompressor.decompressor(decryptedData,fileName,outputPathName);	//Decompressor method to unzip the data       
		} catch (IllegalBlockSizeException e) {
			System.out.println("Error :=  Illegal Block size exception occurred in Encryptor class. Blocks are not in multiples of 16 bytes.");
			System.out.println(e.getMessage());
		} catch (BadPaddingException e) {
			System.out.println("Error :=  Bad Padding Exception occurred in Encryptor class. Blocks are not properly padded in multiples of 16 bytes.");
			System.out.println(e.getMessage());
		}	
}
	/**
	 * 
	 * @param keyFile location where the key file is stored.
	 * @return Returns the decryption key.
	 */
	private byte[] getKey(String keyFile)
	{
		byte[] key = new byte[16];
		try {
			new FileInputStream(new File(keyFile)).read(key);
			return key;
		} catch (FileNotFoundException e) {
			System.out.println("Error :=  File not found exception occurred in Encryptor class . The specified file is not found.");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error :=  IOException occurred in Encryptor class.");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * this method does the initialization of the cipher with mode,secret key and initialization vector.
	 * 
	 * @param randomIntializationVector
	 * @return
	 */
	
	private Cipher init(byte[] randomIntializationVector)
	{
		System.out.println("Creating a cipher object.");
	  try {
		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");	//Getting a cipher object with AES encryption 
		secretKey = new SecretKeySpec(key, "AES");	//Creating a secret key object for encryption
 		cipher.init(Cipher.DECRYPT_MODE, secretKey,new IvParameterSpec(randomIntializationVector));
		} catch (InvalidAlgorithmParameterException e) {
	
			System.out.println("Error :=  InvalidAlgorithmParameterException occurred in Encryptor class. Wrong parameter is provided.");
			System.out.println(e.getMessage());
		}	//initializing cipher with decryption mode
		catch (NoSuchAlgorithmException e) {

			System.out.println("Error :=  No Such Algorithm Exception occured in Encryptor class. The specified Algorithm does not exist.");
			System.out.println(e.getMessage());
		} catch (NoSuchPaddingException e) {
		
			System.out.println("Error :=  NoSuchPaddingException occurred in Encryptor class. Wrong Padding type provided.");
			System.out.println(e.getMessage());
		} catch (InvalidKeyException e) {
			
			System.out.println("Error :=  InvalidKeyException occurred in Encryptor class. Wrong Key provided.");
			System.out.println(e.getMessage());
		}
	  
	  /**
	   * @return 
	   */
        return cipher;
	}
}
