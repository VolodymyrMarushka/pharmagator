package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.entities.Medicine;

import java.util.List;

public interface MedicineService {

	Medicine create(Medicine medicine);

	Medicine getById(Long id);

	Medicine update(Long id, Medicine medicine);

	void deleteById(Long id);

	List<Medicine> getAll();
}
