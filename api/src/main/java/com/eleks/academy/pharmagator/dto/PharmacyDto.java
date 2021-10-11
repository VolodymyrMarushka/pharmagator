package com.eleks.academy.pharmagator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PharmacyDto {
	private String name;
	private String medicineLinkTemplate;
}
