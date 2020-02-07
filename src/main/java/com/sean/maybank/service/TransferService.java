package com.sean.maybank.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sean.maybank.dto.TransferDto;

public interface TransferService {
	
	String batchInsertTransfer() throws IOException;
	
	Page<TransferDto> getAllTransfers(Pageable pageable);
	
	Page<TransferDto> searchTransfers(Pageable pageable, Map<String, Object> obj);

}
