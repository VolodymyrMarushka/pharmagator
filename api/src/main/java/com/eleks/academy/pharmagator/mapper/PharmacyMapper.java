package com.eleks.academy.pharmagator.mapper;

import com.eleks.academy.pharmagator.dto.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PharmacyMapper {

	public Pharmacy convertPharmacyDtoToPharmacy(PharmacyDto pharmacyDto) {
		Pharmacy pharmacy = new Pharmacy();
		pharmacy.setName(pharmacyDto.getName());
		pharmacy.setMedicineLinkTemplate(pharmacyDto.getMedicineLinkTemplate());
		return pharmacy;
	}

	public PharmacyDto convertPharmacyToPharmacyDto(Pharmacy pharmacy) {
		return PharmacyDto.builder()
				.name(pharmacy.getName())
				.medicineLinkTemplate(pharmacy.getMedicineLinkTemplate())
				.build();
	}

	public List<PharmacyDto> convertListPharmacyToPharmacyDto(List<Pharmacy> pharmacies) {
		return pharmacies.stream()
				.map(this::convertPharmacyToPharmacyDto)
				.collect(Collectors.toList());
	}
}
