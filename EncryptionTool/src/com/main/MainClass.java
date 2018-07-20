package com.main;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import com.utils.FileReaderClass;
public class MainClass {
	//static Logger logger = Logger.getLogger(MainClass.class.getName());

	/**
	 *
	 * @param args[0]- this argument takes path of the input file.
	 * @param args[1]- this argument takes path of the output file.
	 * @param args[2]- this argument takes key for the encryption.
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] args) throws IOException{
		
	/*	FileOutputStream fileOutputStream= new FileOutputStream(new File("./key.key"));
		byte  array[] =new byte[16];
		SecureRandom randomgen = new SecureRandom();
		randomgen.nextBytes(array);
		System.out.println(new String(array));
		fileOutputStream.write(array);
		fileOutputStream.close();
		*/
		
		System.out.println("Input location = " + args[0]);
		System.out.println("Output location = "+args[1]);
		System.out.println("Path of key file = " + args[2]);
 
		String inputPathName = args[0];
		String outputPathName=args[1];
		String keyFile=args[2];
		
		final double startTime = System.currentTimeMillis(); //Recording start time
		File inputPath=new File(inputPathName);
		
		File[] listOfFiles = inputPath.listFiles();
		if(inputPath.isDirectory())
		{
			FileReaderClass fileReader = new FileReaderClass(); //Creating an object for FileReaderClass
			for(int i=0;i<listOfFiles.length;i++)
			{
				fileReader.readFile(inputPathName, listOfFiles[i].getName(),outputPathName,keyFile); // calling the function for reading the file.
				
			}
			
		}
		final double endTime = System.currentTimeMillis();	//Recording end time
		double time = (((endTime - startTime)/1000));
		System.out.println("Total execution time: " +time +" seconds");
		System.out.println("Total execution time: " +(((endTime - startTime)/1000))/60 +" minutes");
	}
}
