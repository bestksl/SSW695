package com.hobbymatcher.entity;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class Hobby implements Serializable {

    private static final long serialVersionUID = 3886854875271518921L;

    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Integer categoryId;
    @NotBlank
    private String description;
    private Boolean plus18Only;
    // @NonNull
    private String photoId;
    // @NonNull
    private Integer createdById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPlus18Only() {
        return plus18Only;
    }

    public void setPlus18Only(Boolean plus18Only) {
        this.plus18Only = plus18Only;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }
}
