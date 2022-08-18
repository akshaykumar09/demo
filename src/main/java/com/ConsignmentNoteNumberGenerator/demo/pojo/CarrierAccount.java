package com.ConsignmentNoteNumberGenerator.demo.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CarrierAccount {
	public String CarrierName;
	public String AccountNumber;
	public int Digits;
	public int LastUsedIndex;
	public int RangeStart;
	public int RangeEnd;
}
