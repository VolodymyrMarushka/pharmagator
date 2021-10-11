package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.dto.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.mapper.PharmacyMapper;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {
	private final PharmacyRepository pharmacyRepository;
	private final PharmacyMapper pharmacyMapper;

	@GetMapping
	public ResponseEntity<List<PharmacyDto>> getAll() {
		return ResponseEntity.ok(pharmacyMapper.convertListPharmacyToPharmacyDto(pharmacyRepository.findAll()));
	}

	@PostMapping()
	public ResponseEntity<PharmacyDto> create(@RequestBody PharmacyDto pharmacyDto) {
		Pharmacy pharmacy = pharmacyMapper.convertPharmacyDtoToPharmacy(pharmacyDto);
		return new ResponseEntity<>(pharmacyMapper.convertPharmacyToPharmacyDto(pharmacyRepository.save(pharmacy)), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PharmacyDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(pharmacyMapper.convertPharmacyToPharmacyDto(pharmacyRepository.findById(id).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Pharmacy with id " + id + " not found!"))));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PharmacyDto> update(@PathVariable Long id, @RequestBody PharmacyDto pharmacy) {
		Pharmacy excitingPharmacy = pharmacyRepository.findById(id).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Pharmacy with id " + id + " not found!"));
		BeanUtils.copyProperties(pharmacy, excitingPharmacy, "id");
		return ResponseEntity.ok(pharmacyMapper.convertPharmacyToPharmacyDto(pharmacyRepository.save(excitingPharmacy)));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Pharmacy pharmacy = pharmacyRepository.findById(id).orElseThrow(() ->
				new ResponseStatusException(HttpStatus.NOT_FOUND));
		pharmacyRepository.delete(pharmacy);
	}

}
