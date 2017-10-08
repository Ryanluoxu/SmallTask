package com.ryanluo.loggenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestCode {

	public static void main(String[] args) throws ParseException {

		
		
		Date startDate = new SimpleDateFormat( "yyyyMMdd" ).parse( "20171010");
		Date endDate = new SimpleDateFormat( "yyyyMMdd" ).parse( "20171210");

		long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
	    Date date = new Date(random);
		System.out.println(date);
		
		
		ArrayList<String> accoundIds = new ArrayList<>();
		accoundIds.add("accoundId1");
		accoundIds.add("accoundId2");
		accoundIds.add("accoundId3");
		
		int randomInt = new Random().nextInt(3);
		System.out.println(accoundIds.get(randomInt));
		
	}

}
