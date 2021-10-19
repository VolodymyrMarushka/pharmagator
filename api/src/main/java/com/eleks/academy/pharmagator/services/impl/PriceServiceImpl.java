package com.eleks.academy.pharmagator.services.impl;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
import com.eleks.academy.pharmagator.exception.ResourceNotFoundException;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import com.eleks.academy.pharmagator.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

	private final PriceRepository priceRepository;

	public Price create(Price price) {
		if (price.getUpdatedAt() == null) {
			price.setUpdatedAt(Instant.now());
		}
		return priceRepository.save(price);
	}

	public Price getById(PriceId id) {
		return priceRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Price with id " + id + " not found!"));
	}

	public Price update(PriceId id, Price price) {
		if (price.getUpdatedAt() == null) {
			price.setUpdatedAt(Instant.now());
		}
		Price goalPrice = priceRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Price with id " + id + " not found!"));
		BeanUtils.copyProperties(price, goalPrice, "pharmacyId", "medicineId");
		return priceRepository.save(goalPrice);
	}

	public void deleteById(PriceId id) {
		Price goalPrice = priceRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("Price with id " + id + " not found!"));
		priceRepository.delete(goalPrice);
	}

	public List<Price> getAll() {
		return priceRepository.findAll();
	}
}
