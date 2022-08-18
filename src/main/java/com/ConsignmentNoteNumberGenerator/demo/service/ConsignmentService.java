package com.ConsignmentNoteNumberGenerator.demo.service;

import org.springframework.stereotype.Service;

import com.ConsignmentNoteNumberGenerator.demo.Exceptions.ConsignmentException;
import com.ConsignmentNoteNumberGenerator.demo.constants.Consignmentconstants;
import com.ConsignmentNoteNumberGenerator.demo.pojo.CarrierAccount;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ConsignmentService {

	public String getProcessedConsignemnt(CarrierAccount carrierAccount) throws ConsignmentException {
		try {
		StringBuilder connoteNumber = new StringBuilder(carrierAccount.getCarrierName());
		connoteNumber.append(carrierAccount.getAccountNumber());
		String consignmentNumber = getConsignmentNumber(carrierAccount);
		if (consignmentNumber != null) {
			connoteNumber.append(consignmentNumber);
			connoteNumber.append(calcChecksum(consignmentNumber, carrierAccount));
			return connoteNumber.toString();
		}
		else 
			log.error(Consignmentconstants.INVALID_RESPONSE);
			throw new ConsignmentException(Consignmentconstants.INVALID_RESPONSE);
		}
		catch (ConsignmentException e) {
			return Consignmentconstants.INVALID_RESPONSE;
		}

	}

	private String calcChecksum(String consignmentNumber, CarrierAccount carrierAccount) {
		int checkSum = 0;
		int firstElement = 0;
		int secondElement = 0;

		// calculating first element
		for (int i = consignmentNumber.length() - 1; i >= 0; i = i - 2) {
			firstElement = firstElement + Integer.parseInt(String.valueOf(consignmentNumber.charAt(i)));
		}

		firstElement = firstElement * 3;

		// calculating second element
		for (int i = consignmentNumber.length() - 2; i >= 0; i = i - 2) {
			secondElement = secondElement + Integer.parseInt(String.valueOf(consignmentNumber.charAt(i)));
		}
		secondElement = secondElement * 7;

		int result = (firstElement + secondElement) / 10;
		checkSum = (result + 1) * 10;
		checkSum = checkSum - (firstElement + secondElement);

		return String.valueOf(checkSum);
	}

	private String getConsignmentNumber(CarrierAccount carrierAccount) {
		
		int lastUsedIndex = carrierAccount.getLastUsedIndex();
		lastUsedIndex++;
		String consignmentIndex = String.valueOf(lastUsedIndex++);

		if (lastUsedIndex >= carrierAccount.getRangeStart() && lastUsedIndex <= carrierAccount.getRangeEnd()) {
			for (int i = 0; i < carrierAccount.getDigits() - String.valueOf(lastUsedIndex).length(); i++) {
				consignmentIndex = "0" + consignmentIndex;
			}
			return consignmentIndex;
		}
		return null;
	}
}
