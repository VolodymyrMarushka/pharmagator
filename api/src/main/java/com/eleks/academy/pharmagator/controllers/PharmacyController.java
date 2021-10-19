package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.services.PharmacyService;
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
@RequestMapping("/pharmacies")
public class PharmacyController {
	private final PharmacyService pharmacyService;

	@GetMapping
	public ResponseEntity<List<Pharmacy>> getAll() {
		return ResponseEntity.ok(pharmacyService.getAll());
	}

	@PostMapping()
	public ResponseEntity<Pharmacy> create(@RequestBody Pharmacy pharmacy) {
		if (pharmacy.getName() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty pharmacy name!");
		}
		return new ResponseEntity<>(pharmacyService.create(pharmacy), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pharmacy> getById(@PathVariable Long id) {
		return ResponseEntity.ok(pharmacyService.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pharmacy> update(@PathVariable Long id, @RequestBody Pharmacy pharmacy) {
		return ResponseEntity.ok(pharmacyService.update(id, pharmacy));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		pharmacyService.deleteById(id);
	}
}
