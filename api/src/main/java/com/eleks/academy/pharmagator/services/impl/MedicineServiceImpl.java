package com.eleks.academy.pharmagator.services.impl;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.exception.ResourceNotFoundException;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import com.eleks.academy.pharmagator.services.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

	private final MedicineRepository medicineRepository;

	@Override
	public Medicine create(Medicine medicine) {
		return medicineRepository.save(medicine);
	}

	@Override
	public Medicine getById(Long id) {
		return medicineRepository.findById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("Medicine with id " + id + " not found!");
		});
	}

	@Override
	public Medicine update(Long id, Medicine medicine) {
		Medicine goalMedicine = medicineRepository.findById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("Medicine with id " + id + " not found!");
		});
		BeanUtils.copyProperties(medicine, goalMedicine, "id");
		return medicineRepository.save(goalMedicine);
	}

	@Override
	public void deleteById(Long id) {
		Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> {
			throw new ResourceNotFoundException("Medicine with id " + id + " not found!");
		});
		medicineRepository.delete(medicine);
	}

	@Override
	public List<Medicine> getAll() {
		return medicineRepository.findAll();
	}
}
