package com.viasoft.apijava.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ITEMAGRO", schema = "VIASOFT")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM")
    private Integer item;

    @Column(name = "DESCRICAO")
    private String descricao;

}
