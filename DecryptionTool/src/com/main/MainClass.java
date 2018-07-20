package com.main;


import java.io.File;

import com.utils.FileReaderClass;

public class MainClass {

	/**
	 * @param args[0]- this argument takes path of the input file.
	 *  @param args[1]- this argument takes path of the output file.
	 * @param args[2]- this argument takes key for the decryption.
	 */
	public static void main(String[] args){
		
		System.out.println("Input location = " + args[0]);
		System.out.println("Output location = "+args[1]);
		System.out.println("Path of key file = " + args[2]);
		
		String inputPathName = args[0];
		String outputPathName=args[1];
		String keyFile=args[2];
		
		File inputPath=new File(args[0]);
		File[] listOfFiles = inputPath.listFiles();
		final double startTime = System.currentTimeMillis(); //Recording start time
		if(inputPath.isDirectory())
		{
			FileReaderClass fileReader = new FileReaderClass();
			
			for(int i=0;i<listOfFiles.length;i++)
			{	
					//Creating an object for FileReaderClass
				fileReader.readFile(inputPathName, listOfFiles[i].getName(),outputPathName,keyFile);  // calling the function for reading the file.
				
			}
		}
		final double endTime = System.currentTimeMillis();	//Recording end time
		double time = (((endTime - startTime)/1000));
		System.out.println("Total execution time: " +time +" seconds");
		System.out.println("Total execution time: " + (((endTime - startTime)/1000)/60)+" minutes");
	}

}
