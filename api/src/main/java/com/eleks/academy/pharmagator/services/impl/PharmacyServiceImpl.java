package com.eleks.academy.pharmagator.services.impl;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.exception.ResourceNotFoundException;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import com.eleks.academy.pharmagator.services.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {

	private final PharmacyRepository pharmacyRepository;


	@Override
	public Pharmacy create(Pharmacy pharmacy) {
		return pharmacyRepository.save(pharmacy);
	}

	@Override
	public Pharmacy getById(Long id) {
		return pharmacyRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Pharmacy with id " + id + " not found!"));
	}

	@Override
	public Pharmacy update(Long id, Pharmacy pharmacy) {
		Pharmacy goalPharmacy = getById(id);
		BeanUtils.copyProperties(pharmacy, goalPharmacy, "id");
		return pharmacyRepository.save(goalPharmacy);
	}

	@Override
	public void deleteById(Long id) {
		Pharmacy pharmacy = pharmacyRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Pharmacy with id " + id + " not found!"));
		pharmacyRepository.delete(pharmacy);
	}

	@Override
	public List<Pharmacy> getAll() {
		return pharmacyRepository.findAll();
	}
}
