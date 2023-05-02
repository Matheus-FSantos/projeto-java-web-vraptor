package br.senac.sp.blog.model;

import java.util.Date;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="comment_tb")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String text;
	
	private Date creationDate;
	
	private Date updateDate;
	
	@ManyToOne
	@JoinColumn(nullable=false, name="author_id")
	private User author;
	
	@ManyToOne
	@JoinColumn(nullable=false, name="post_id")
	private Post post;
	
	public Comment() {
		
	}
	
	public Comment(Long id, String text, Date creationDate, Date updateDate, User author, Post post) {
		this.id = id;
		this.text = text;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.author = author;
		this.post = post;
	}

	public Long getId() {
		return id;
	}
	
	public void updateId(Long id) {
		this.setId(id);
	}
	
	protected void setId(Long id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void updateText(String text) {
		this.setText(text);
	}

	protected void setText(String text) {
		this.text = text;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public String getFormatedCreationDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
		String date = dateFormat.format(this.creationDate);
		
		return date;
	}
	
	public void updateCreationDate(Date creationDate) {
		this.setCreationDate(creationDate);
	}

	protected void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}
	
	public String getFormatedUpdateDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
		String date = dateFormat.format(this.updateDate);
		
		return date;
	}
	
	public void updateUpdateDate(Date updateDate) {
		this.setUpdateDate(updateDate);
	}

	protected void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public User getAuthor() {
		return author;
	}
	
	public void updateAuthor(User author) {
		this.setAuthor(author);
	}

	protected void setAuthor(User author) {
		this.author = author;
	}

	public Post getPost() {
		return post;
	}

	public void updatePost(Post post) {
		this.setPost(post);
	}

	protected void setPost(Post post) {
		this.post = post;
	}
}