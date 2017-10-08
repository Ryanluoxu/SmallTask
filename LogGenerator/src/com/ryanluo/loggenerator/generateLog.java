package com.ryanluo.loggenerator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class generateLog {
	
	private static Properties properties;
	
	public static void main(String[] args) throws IOException, ParseException {
		
		properties = getProperties("logGenerat.properties");
		
		/**
		 * create file
		*/
		String fileName = properties.getProperty("fileName");
		PrintWriter printWriter = new PrintWriter(fileName, "UTF-8"); // existing file will be replaced.
		
		/**
		 * add header 
		*/
		
		String filePath = "/home/luoxu/workspace/LogGenerator/doc/logHeader.txt";
		addContent(printWriter, filePath);
		
		/**
		 * create body
		*/
		
		//	T1001	2017-10-01 19:01:00.0	A1001		dep		1000.00
		int txnVolumn = Integer.parseInt(properties.getProperty("txnVolumn"));
		
		String accoundId1 = properties.getProperty("accoundId1");
		String accoundId2 = properties.getProperty("accoundId2");
		String accoundId3 = properties.getProperty("accoundId3");
		ArrayList<String> accoundIds = new ArrayList<>();
		accoundIds.add(accoundId1);
		accoundIds.add(accoundId2);
		accoundIds.add(accoundId3);

		int txnId = 1001;
		int randomInt;
		LogVO logVO;
		String strLine;
		
		for (int i = 0; i < txnVolumn; i++) {
			
			logVO = new LogVO();
			
			logVO.setTxnId("T" + txnId);
			
			Date startDate = new SimpleDateFormat( "yyyyMMdd" ).parse( "20171010");
			Date endDate = new SimpleDateFormat( "yyyyMMdd" ).parse( "20171210");
			long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
		    Date randomDate = new Date(random);
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    String date = sdf.format(randomDate);
			logVO.setDate(date);
			
			randomInt = new Random().nextInt(3);
			logVO.setAccountId(accoundIds.get(randomInt));
			
			ArrayList<String> txnCodes = new ArrayList<>();
			txnCodes.add("dep");
			txnCodes.add("wit");
			randomInt = new Random().nextInt(2);
			logVO.setTxnCode(txnCodes.get(randomInt));
			
			randomInt = new Random().nextInt(1000);
			logVO.setAmount(randomInt);
			
			System.out.println(logVO.getTxnId()+" "+logVO.getDate()+" "+logVO.getAccountId()+" "+logVO.getTxnCode()+" "+logVO.getAmount());
			strLine = logVO.getTxnId()+"	"+logVO.getDate()+"	"+logVO.getAccountId()+"		"+logVO.getTxnCode()+"		"+logVO.getAmount();
			printWriter.write(strLine);
			printWriter.write(System.lineSeparator());
			txnId++;
		}
		
		
		printWriter.close();
		
	}
	
	private static void addContent(PrintWriter printWriter, String filePath) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));

	    String currentLine = br.readLine();

	    while (currentLine != null) {
	    	
	    	printWriter.write(currentLine);
	    	printWriter.write(System.lineSeparator());
	    	currentLine = br.readLine();
	    }

	    br.close();
	}

	
	private static Properties getProperties(String propertiesFile) throws IOException {
		
		FileInputStream fileInputStream = new FileInputStream(propertiesFile);
		Properties properties = new Properties();
		properties.load(fileInputStream);
		
		return properties;
	}
	
	
	
	
	
	
	
	
}
