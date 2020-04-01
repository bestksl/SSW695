package com.hobbymatcher.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 3886854875271518921L;

    private String id;
    @NotBlank
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
