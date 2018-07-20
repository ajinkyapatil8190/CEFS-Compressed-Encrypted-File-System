package com.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
//import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.utils.WriteFile;

public class EncryptorClass {
	
	
	/**
	 * 
	 * <code>public void encryptData()</code> This method performs the encryption on the data which is compressed by the EncryptorClass.
	 * This method then passes the encrypted data to <code> WriteFile</code> class. 
	 * 
	 * @param byte[] input- array of the block which is compressed by <code>CompressorClass</code>.
	 * @param int length- total length of the compressed block.
	 * @param fileName- Name of the file on which the operation is being performed.
	 * @param outputPath- Location for storing the output file.
	 * @param keyPath- location of the encryption key file.
	 * 
	 */
	
	private Cipher cipher;
	private SecretKeySpec secretKey;
	private byte[] key;
	private WriteFile writer;
	
	public void encryptData(byte[] input,int length,String fileName,String outputPath,String keyPath) 
	{
		key=new byte[16];
		byte[] randomInitializationVector=new byte[16];
		key=this.getKey(keyPath);
		try {
				System.out.println("Generating a random Initialization Vector.");
				randomInitializationVector=this.getRandomInitializationVector();
				this.cipher=this.cipherInitialization(randomInitializationVector);
				System.out.println("Encrypting the compressed data.");
				byte[] encryptedData = cipher.doFinal(input,0,length);//Calling do Final method which does the encryption
		        writer = new WriteFile(); //creating an object of write file class
		        writer.writeToFile(encryptedData,fileName,randomInitializationVector,outputPath);//calling the function write to file.
			
			} catch (IllegalBlockSizeException e) {
				System.out.println("Error :=  Illegal Block size exception occurred in Encryptor class. Blocks are not in multiples of 16 bytes.");
				System.out.println(e.getMessage());
			} catch (BadPaddingException e) {
				System.out.println("Error :=  Bad Padding Exception occurred in Encryptor class. Blocks are not properly padded in multiples of 16 bytes.");
				System.out.println(e.getMessage());
			} 
		}
	
	/**
	 * This function returns the 16bit key which will be used for creating cipher text i.e,Encryption. 
	 * 
	 * @param keyPath- location where the key file is stored.
	 * @return key- returns the value of the key which is used for encryption.
	 */
	
	private byte[] getKey(String keyPath)
	{
		byte[] key = new byte[16];
		try {
			new FileInputStream(new File(keyPath)).read(key);
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
	 * This method does the initialization of the cipher with mode,secret key and initialization vector. 
	 * @param randomIntializationVector
	 * @return
	 */
	private Cipher cipherInitialization(byte[] randomIntializationVector) 
	{
		System.out.println("Getting instance of Cipher....");
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			secretKey = new SecretKeySpec(key, "AES");  //Creating a secret key object with the key
		    cipher.init(Cipher.ENCRYPT_MODE, secretKey,new IvParameterSpec(randomIntializationVector));  //Initializing cipher with encrypt mode and secret key
		    return cipher;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error :=  No Such Algorithm Exception occured in Encryptor class. The specified Algorithm does not exist.");
			System.out.println(e.getMessage());
		} catch (NoSuchPaddingException e) {
			System.out.println("Error :=  NoSuchPaddingException occurred in Encryptor class. Wrong Padding type provided.");
			System.out.println(e.getMessage());

		}  // Creating a cipher instant with specifying an Algorithm.
		catch (InvalidKeyException e) {
			System.out.println("Error :=  InvalidKeyException occurred in Encryptor class. Wrong Key provided.");
			System.out.println(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			System.out.println("Error :=  InvalidAlgorithmParameterException occurred in Encryptor class. Wrong parameter is provided.");
			System.out.println(e.getMessage());
		}
		return null;
       
	}
	
	
	/**
	 * 
	 * @return Initialization Vector
	 */
	
	private byte[] getRandomInitializationVector()
	{
		byte[] IV = new byte[16];
		new SecureRandom().nextBytes(IV);
		return IV;
	}
}
