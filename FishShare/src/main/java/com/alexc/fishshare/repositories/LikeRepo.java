package com.alexc.fishshare.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexc.fishshare.models.Like;

@Repository
public interface LikeRepo extends CrudRepository<Like, Long>{

}
