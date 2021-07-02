package com.cognizant.ptaedgeservice.model;

import java.util.Objects;
public class Resource {
    private Integer id;
    private String author;
    private String title;
    private String content;

    public Resource() {
    }

    public Resource(Integer id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resource)) return false;
        Resource resource = (Resource) o;
        return Objects.equals(getId(), resource.getId()) && Objects.equals(getAuthor(), resource.getAuthor()) && Objects.equals(getTitle(), resource.getTitle()) && Objects.equals(getContent(), resource.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getTitle(), getContent());
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
