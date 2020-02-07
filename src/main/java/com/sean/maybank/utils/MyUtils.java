package com.sean.maybank.utils;

import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Component
public class MyUtils {
	
	public CSVReader csvReader(
			final String fileName,
			final String seperator,
			final boolean ignoreQuotations,
			final int skipLines) throws IOException{
		
		CSVParser parser = new CSVParserBuilder()
				.withSeparator('|')
				.withIgnoreQuotations(true)
				.build();
		
		CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileName))
				.withSkipLines(1)
				.withCSVParser(parser)
				.build();
		return csvReader;
	}

}
