package com.sean.maybank.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.sean.maybank.model.Account;
import com.sean.maybank.repository.AccountRepository;
import com.sean.maybank.service.BatchInsertService;
import com.sean.maybank.utils.MyUtils;

@Service
public class BatchInsertServiceImpl implements BatchInsertService{
	
	@Autowired
	MyUtils myUtils;
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public String batchInsertTransfer() throws IOException{
		String fileName = "src/main/resources/dataSource.txt";
		String seperator = "|";
		boolean ignoreQuotations = true;
		int skipLines = 1;
		
		CSVReader csvReader = myUtils.csvReader(fileName, seperator, ignoreQuotations, skipLines);
		
		String[] data = null;
		while((data = csvReader.readNext()) != null) {
			System.out.println(data[0]);
			Account account = accountRepository.findByAccountNumber(data[0]);
			System.out.println(account.getAccountNumber());
		}
		return "Batch Insert Successfully";
	}

}
