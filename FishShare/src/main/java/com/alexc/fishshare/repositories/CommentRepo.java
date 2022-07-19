package com.alexc.fishshare.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alexc.fishshare.models.Comment;

@Repository
public interface CommentRepo extends CrudRepository <Comment, Long> {

}
