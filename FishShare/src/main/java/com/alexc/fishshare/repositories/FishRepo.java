package com.alexc.fishshare.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexc.fishshare.models.Fish;

@Repository
public interface FishRepo extends CrudRepository<Fish, Long> {
	List<Fish> findAll();
}
