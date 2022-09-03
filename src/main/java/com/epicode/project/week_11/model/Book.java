package com.epicode.project.week_11.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;
    @Column(nullable=false)
    private int publicationYear;
    @Column(nullable=false,unique = true)
    private String title;
    @Column(nullable=false)
    private double price;

    @ManyToMany
    private List<Author> authors=new ArrayList<>();
    @ManyToMany
    private List<Category> categories=new ArrayList<>();
}
