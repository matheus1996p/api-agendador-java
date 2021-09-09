package com.viasoft.apijava.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CONF_AGENDAMENTO", schema = "VIASOFT")
@Data
public class ConfAgendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "BLOQUEADOS")
    private String bloqueados;

    @Column(name = "HORAINICIAL")
    private String horainicial;

    @Column(name = "HORAFINAL")
    private String horafinal;

    @Column(name = "INTERVALO")
    private Integer intervalo;

    @Column(name = "DOMINGO")
    private Integer domingo;

    @Column(name = "SEGUNDA")
    private Integer segunda;

    @Column(name = "TERCA")
    private Integer terca;

    @Column(name = "QUARTA")
    private Integer quarta;

    @Column(name = "QUINTA")
    private Integer quinta;

    @Column(name = "SEXTA")
    private Integer sexta;

    @Column(name = "SABADO")
    private Integer sabado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
