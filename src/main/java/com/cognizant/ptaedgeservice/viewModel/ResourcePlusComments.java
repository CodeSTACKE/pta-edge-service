package com.cognizant.ptaedgeservice.viewModel;

import com.cognizant.ptaedgeservice.model.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResourcePlusComments {
    private Integer id;
    private String author;
    private String title;
    private String content;
    private List<Comment> comments = new ArrayList<>();

    public ResourcePlusComments() {
    }

    public ResourcePlusComments(Integer id, String author, String title, String content, List<Comment> comments) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourcePlusComments that = (ResourcePlusComments) o;
        return Objects.equals(id, that.id) && Objects.equals(author, that.author) && Objects.equals(title, that.title) && Objects.equals(content, that.content) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, content, comments);
    }

    @Override
    public String toString() {
        return "ResourcePlusComments{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }
}
