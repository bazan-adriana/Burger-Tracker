package com.adriana.burgertracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adriana.burgertracker.models.Burger;
import com.adriana.burgertracker.repositories.BurgerRepository;

@Service
public class BurgerService {
	private final BurgerRepository burgerRepo;
	public BurgerService(BurgerRepository burgerRepo) {
		this.burgerRepo = burgerRepo;
	}
	
	public Burger getOne(Long id) {
		Optional<Burger> burger = burgerRepo.findById(id);
		return burger.isPresent() ? burger.get() : null;
	}

	public List<Burger> getAll() {
		return (List<Burger>) burgerRepo.findAll();
	}

	public Burger create(Burger burger) {
		return burgerRepo.save(burger);
	}

	public Burger update(Burger burger) {
		return burgerRepo.save(burger);
	}

	public void delete(Long id) {
		burgerRepo.deleteById(id);
	}

}
