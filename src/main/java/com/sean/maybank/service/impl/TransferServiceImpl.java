package com.sean.maybank.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.sean.maybank.dto.TransferDto;
import com.sean.maybank.exceptions.TransferServiceException;
import com.sean.maybank.exceptions.message.ErrorMessages;
import com.sean.maybank.model.Account;
import com.sean.maybank.model.Transfer;
import com.sean.maybank.repository.TransferRepository;
import com.sean.maybank.service.AccountService;
import com.sean.maybank.service.TransferService;
import com.sean.maybank.utils.MyUtils;

@Service
public class TransferServiceImpl implements TransferService{
	
	@Autowired
	MyUtils myUtils;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	TransferRepository transferRepository;
	
	@Autowired
	AccountService accountService;
	
	@Override
	public String batchInsertTransfer() throws IOException{
		String fileName = "src/main/resources/dataSource.txt";
		String seperator = "|";
		boolean ignoreQuotations = true;
		int skipLines = 1;
		
		CSVReader csvReader = myUtils.csvReader(fileName, seperator, ignoreQuotations, skipLines);
		
		String[] data = null;
		List<Transfer> transfers = new ArrayList<>();
		while((data = csvReader.readNext()) != null) {
			Transfer transfer = new Transfer();
			Account account = accountService.getAccountByAccountNumber(data[0]);
			BigDecimal trxAmount = new BigDecimal(data[1]);
			String description = data[2];
			LocalDate trxDate = LocalDate.parse(data[3]);
			LocalTime trxTime = LocalTime.parse(data[4]);
			
			transfer.setAccount(account);
			transfer.setTrxAmount(trxAmount);
			transfer.setDescription(description);
			transfer.setTrxDate(trxDate);
			transfer.setTrxTime(trxTime);
			transfers.add(transfer);
		}
		transferRepository.saveAll(transfers);
		return "Batch Insert Successfully";
	}

	@Override
	public Page<TransferDto> getAllTransfers(Pageable pageable) {
		
		Page<Transfer> transfers = transferRepository.findAll(pageable);
		
		int totalElements = (int) transfers.getTotalElements();
		return new PageImpl<TransferDto>(transfers.stream().map(transfer -> new TransferDto(transfer))
				.collect(Collectors.toList()), pageable, totalElements);
	}
	
	@Override
	public Page<TransferDto> searchTransfers(Pageable pageable, Map<String, Object> obj) {
		
		Page<Transfer> transfers = null;
		if(obj.get("customer_id") != null) {
			int customer_id = Integer.parseInt(String.valueOf(obj.get("customer_id")));
			transfers = transferRepository.findTransfersByCustomerId(pageable, customer_id);
		}
		if(obj.get("account_number") != null) {
			String account_number = String.valueOf(obj.get("account_number"));
			transfers = transferRepository.findTransfersByAccountNumber(pageable, account_number);
		}
		if(obj.get("description") != null) {
			String description = String.valueOf(obj.get("description"));
			transfers = transferRepository.findTransfersByDescription(pageable, description);
		}
		if(!transfers.hasContent()) {
			throw new TransferServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		PageImpl<TransferDto> returnValue = null;
		if(transfers != null) {
			int totalElements = (int) transfers.getTotalElements();
			returnValue =  new PageImpl<TransferDto>(transfers.stream().map(transfer -> new TransferDto(transfer))
					.collect(Collectors.toList()), pageable, totalElements);
		}

		return returnValue;
	}

}
