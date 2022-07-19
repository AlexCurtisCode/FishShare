package com.alexc.fishshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexc.fishshare.models.Fish;
import com.alexc.fishshare.repositories.FishRepo;

@Service
public class FishServ {
	@Autowired
	FishRepo fishRepo;
	
	public List<Fish> findAll() {
		return fishRepo.findAll();
	}
	public Fish findFish(Long id) {
		return fishRepo.findById(id).get();
	}
	
	public Fish createFish(Fish fish) {
		return fishRepo.save(fish);
	}
	
	public void deleteFish(Fish fish) {
		fishRepo.delete(fish);
	}
	public Fish updateFish(Fish fish) {
		return fishRepo.save(fish);
	}
}
