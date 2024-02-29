package com.ccp5.dto;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ingredients_board")
@Getter
@Setter
public class IngrBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="inum")
    private int id;
    private String title;
    private String category;
    private String name;
    private int unit;
}
