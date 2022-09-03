package com.epicode.project.week_11.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @Column(nullable=false)
    private String name;
    @Column(nullable=false,unique=true)
    private String surname;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnore
    private List<Book> books=new ArrayList<>();
}
