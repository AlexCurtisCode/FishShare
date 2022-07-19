package com.alexc.fishshare.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexc.fishshare.models.Fish;
import com.alexc.fishshare.models.Picture;

@Repository
public interface PicRepo extends CrudRepository<Picture, Long>{
	List<Picture> findAllByPicturedFish(Fish picturedFish);
}
