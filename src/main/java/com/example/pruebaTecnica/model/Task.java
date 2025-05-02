package com.example.pruebaTecnica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * MODEL for Task
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "TAREA", schema = "pruebatecnica")
public class Task implements Serializable {

    private static final long serialVersionUID = -6580091882629896234L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE")
    private String name;

    @Column(name = "DESCRIPCION")
    private String description;

    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "FECHA_REGISTRO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registerDate;

    @Column(name = "ESTADO", nullable = false)
    private String status;

    @Column(name = "ID_USUARIO", nullable = false)
    private Long idUser;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", insertable = false, updatable = false)
    private User user;
}
