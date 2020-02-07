package com.sean.maybank.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sean.maybank.dto.TransferDto;
import com.sean.maybank.repository.TransferRepository;
import com.sean.maybank.response.TransferResponse;
import com.sean.maybank.service.TransferService;
import com.sean.maybank.utils.MyUtils;

@RestController
@RequestMapping("/transfers")
public class TransferController {

	@Autowired
	MyUtils myUtils;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	TransferService transferService;

	@Autowired
	TransferRepository transferRepository;

	@GetMapping("/batch")
	public String batchInsert() throws IOException {
		String returnValue = transferService.batchInsertTransfer();
		return returnValue;
	}

	@GetMapping("/all")
	public MappingJacksonValue getAllTransfer(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "5", required = false) int limit,
			@RequestParam(value = "fields", defaultValue = "id,trxAmount,trxDate,trxTime,description,account,customer", required = false) String fields) {

		String[] theFields = fields.split(",");
		Pageable pageable = PageRequest.of(page, limit);

		Page<TransferDto> transfers = transferService.getAllTransfers(pageable);

		int totalElements = (int) transfers.getTotalElements();

		PageImpl<TransferResponse> pageImpl = new PageImpl<TransferResponse>(
				transfers.stream().map(transfer -> new TransferResponse(transfer)).collect(Collectors.toList()),
				pageable, totalElements);

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(theFields);

		FilterProvider filters = new SimpleFilterProvider().addFilter("TransferFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(pageImpl);

		mapping.setFilters(filters);

		return mapping;

	}

	@GetMapping("/search")
	public MappingJacksonValue searchTransfers(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "5", required = false) int limit,
			@RequestParam(value = "customer_id", required = false) Integer customer_id,
			@RequestParam(value = "account_number", required = false) String account_number,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value = "fields", defaultValue = "id,trxAmount,trxDate,trxTime,description,account,customer", required = false) String fields
			) {
		
		String[] theFields = fields.split(",");
		Pageable pageable = PageRequest.of(page, limit);

		// Page<Transfer> transfers = null;

		Map<String, Object> obj = new HashMap<>();
		obj.put("customer_id", customer_id);
		obj.put("account_number", account_number);
		obj.put("description", description);

		Page<TransferDto> transfers = transferService.searchTransfers(pageable, obj);

		int totalElements = (int) transfers.getTotalElements();

		PageImpl<TransferResponse> pageImpl = new PageImpl<TransferResponse>(
				transfers.stream().map(transfer -> new TransferResponse(transfer)).collect(Collectors.toList()),
				pageable, totalElements);

		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(theFields);

		FilterProvider filters = new SimpleFilterProvider().addFilter("TransferFilter", filter);

		MappingJacksonValue mapping = new MappingJacksonValue(pageImpl);

		mapping.setFilters(filters);

		return mapping;

	}

}
