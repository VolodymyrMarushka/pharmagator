package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.services.MedicineService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/medicines")
public class MedicineController {

	private final MedicineService medicineService;

	@PostMapping
	public ResponseEntity<Medicine> create(@RequestBody Medicine medicine) {
		if (medicine == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title cannot be empty");
		}
		return new ResponseEntity<>(medicineService.create(medicine), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Medicine> getById(@PathVariable Long id) {
		return ResponseEntity.ok(medicineService.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Medicine> update(@PathVariable Long id, @RequestBody Medicine medicine) {
		return ResponseEntity.ok(medicineService.update(id, medicine));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMedicine(@PathVariable Long id) {
		medicineService.deleteById(id);
	}

	@GetMapping
	public ResponseEntity<List<Medicine>> getAll() {
		return ResponseEntity.ok(medicineService.getAll());
	}
}
