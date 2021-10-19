package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;

import java.util.List;

public interface PriceService {

	Price create(Price price);

	Price getById(PriceId id);

	Price update(PriceId id, Price price);

	void deleteById(PriceId id);

	List<Price> getAll();
}
