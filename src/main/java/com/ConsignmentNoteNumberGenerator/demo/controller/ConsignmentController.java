package com.ConsignmentNoteNumberGenerator.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ConsignmentNoteNumberGenerator.demo.Exceptions.ConsignmentException;
import com.ConsignmentNoteNumberGenerator.demo.constants.Consignmentconstants;
import com.ConsignmentNoteNumberGenerator.demo.pojo.CarrierAccount;
import com.ConsignmentNoteNumberGenerator.demo.service.ConsignmentService;

@RestController
@RequestMapping("/api")
public class ConsignmentController {

	@Autowired
	ConsignmentService consignmentService;
	
	@GetMapping("/run")
	public String getConsignmentService() throws ConsignmentException {

		return consignmentService.getProcessedConsignemnt(CarrierAccount.builder().CarrierName(Consignmentconstants.FMCC).AccountNumber("123ABC").Digits(10)
				.LastUsedIndex(19604).RangeStart(19000).RangeEnd(20000).build());
	}
	
	@GetMapping("/test")
	public String getTest() {

		return "test";
	}

}
