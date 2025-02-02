package br.senac.sp.blog.model.domain;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="post_tb")
public class Post implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=75)
	private String title;
	
	@Column(nullable=false)
	private String text;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creationDate;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDate;

	@ManyToOne
	@JoinColumn(nullable=false, name="author")
	private User author;

	@ManyToOne
	@JoinColumn(nullable=false, name="category")
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy="post", orphanRemoval=true)
	private List<Comment> comments = new ArrayList<>();
	
	public Post() {}
	
	public Post(Long id, String title, String text, User author, LocalDateTime creationDate, LocalDateTime updateDate, Category category) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.author = author;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.category = category;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", text=" + text + ", author=" + author + ", creationDate=" + creationDate + ", updateDate=" + updateDate + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(id, other.id);
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

	public String getTitle() {
		return title;
	}
	
	public void updateTitle(String title) {
		this.setTitle(title);
	}

	protected void setTitle(String title) {
		this.title = title;
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

	public User getAuthor() {
		return author;
	}
	
	public void updateAuthor(User author) {
		this.setAuthor(author);
	}

	protected void setAuthor(User author) {
		this.author = author;
	}
	
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getCreationFormatedDate(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return this.getCreationDate().format(formatter);
	}
	
	public void updateCreationDate(LocalDateTime creationDate) {
		this.setCreationDate(creationDate);
	}
	
	private void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public String getUpdateFormatedDate(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return this.getUpdateDate().format(formatter);
	}

	public void updateUpdateDate(LocalDateTime updateDate) {
		this.setUpdateDate(updateDate);
	}

	private void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	public List<Comment> getComments(){
		return comments;
	}

	public Category getCategory() {
		return category;
	}

	public void updateCategory(Category category) {
		this.setCategory(category);
	}

	private void setCategory(Category category) {
		this.category = category;
	}
}