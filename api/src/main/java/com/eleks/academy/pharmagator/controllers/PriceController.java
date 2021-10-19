package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
import com.eleks.academy.pharmagator.services.PriceService;
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

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prices")
public class PriceController {

	private final PriceService priceService;

	@PostMapping
	public ResponseEntity<Price> createPrice(@RequestBody Price price) {
		return new ResponseEntity<>(priceService.create(price), HttpStatus.CREATED);
	}

	@GetMapping("/{pharmacyId}/{medicineId}")
	public ResponseEntity<Price> getById(@PathVariable Long pharmacyId, @PathVariable Long medicineId) {
		return ResponseEntity.ok(priceService.getById(createPriceId(pharmacyId, medicineId)));
	}

	@PutMapping("/{pharmacyId}/{medicineId}")
	public ResponseEntity<Price> updatePrice(@PathVariable Long pharmacyId, @PathVariable Long medicineId,
	                                         @RequestBody Price price) {
		return ResponseEntity.ok(priceService.update(createPriceId(pharmacyId, medicineId), price));
	}

	@DeleteMapping("/{pharmacyId}/{medicineId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePrice(@PathVariable Long pharmacyId, @PathVariable Long medicineId) {
		priceService.deleteById(createPriceId(pharmacyId, medicineId));
	}

	@GetMapping
	public ResponseEntity<List<Price>> getAllPrices() {
		return ResponseEntity.ok(priceService.getAll());
	}

	private PriceId createPriceId(Long pharmacyId, Long medicineId) {
		return new PriceId(pharmacyId, medicineId);
	}
}
