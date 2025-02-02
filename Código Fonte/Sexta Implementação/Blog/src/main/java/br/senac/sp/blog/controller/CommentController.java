package br.senac.sp.blog.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senac.sp.blog.model.Comment;
import br.senac.sp.blog.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public ResponseEntity<List<Comment>> get(){
		if(commentService.get().isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(commentService.get());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<Comment>> getById(@PathVariable Long id){
		if(commentService.getById(id) == null || commentService.getById(id).isEmpty())
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok().body(commentService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<Void> post(@RequestBody Comment newComment){
		if(commentService.post(newComment) != null) {
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newComment.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		return ResponseEntity.status(406).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody Comment newComment){
		newComment.updateId(id);
		if(commentService.put(newComment) != null) 
			return ResponseEntity.noContent().build();

		return ResponseEntity.status(406).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		if(commentService.delete(id))
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.notFound().build();
	}
}