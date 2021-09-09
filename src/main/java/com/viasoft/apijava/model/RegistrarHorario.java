package com.viasoft.apijava.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "REGISTRAR_HORARIO", schema = "VIASOFT")
@Data
public class RegistrarHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PEDIDO")
    private Integer pedido;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "PLACA")
    private String placa;

    @Column(name = "HORARIO")
    private String horario;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "DATA")
    private Date data;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "DESCRICAO")
    private String descricao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
