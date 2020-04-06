package com.hobbymatcher.entity;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.List;
@Data
public class Review extends Comment {
    @NonNull
    private Integer rate;

    @NotBlank
    private List<Comment> comments;

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
