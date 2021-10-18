package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.entities.Pharmacy;

import java.util.List;

public interface PharmacyService {

	Pharmacy create(Pharmacy pharmacy);

	Pharmacy getById(Long id);

	Pharmacy update(Long id, Pharmacy pharmacy);

	void deleteById(Long id);

	List<Pharmacy> getAll();

}
