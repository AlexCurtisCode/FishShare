package com.alexc.fishshare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexc.fishshare.models.Comment;
import com.alexc.fishshare.models.Fish;
import com.alexc.fishshare.models.User;
import com.alexc.fishshare.repositories.CommentRepo;

@Service
public class CommentServ {
	@Autowired
	CommentRepo commentRepo;
	
	public Comment createComment(Comment comment, User user, Fish fish) {
		Comment newComment = new Comment();
		newComment.setMessage(comment.getMessage());
		newComment.setCommentUser(user);
		newComment.setCommentedFish(fish);
		return commentRepo.save(newComment);
	}
	
}
