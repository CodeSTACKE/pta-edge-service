package com.cognizant.ptaedgeservice.model;

import java.util.Objects;
public class Comment {
    private Integer Id;
    private Integer resourceId;
    private String comment;

    public Comment() {
    }

    public Comment(Integer id, Integer resourceId, String comment) {
        Id = id;
        this.resourceId = resourceId;
        this.comment = comment;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(getId(), comment1.getId()) && Objects.equals(getResourceId(), comment1.getResourceId()) && Objects.equals(getComment(), comment1.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getResourceId(), getComment());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "Id=" + Id +
                ", resourceId=" + resourceId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
