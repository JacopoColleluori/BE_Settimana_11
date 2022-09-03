package com.epicode.project.week_11.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;
    @Column(nullable=false,unique = true)
    private String name;
    @ManyToMany(mappedBy="categories")
  @JsonIgnore
    private List<Book> books=new ArrayList<>();
}
