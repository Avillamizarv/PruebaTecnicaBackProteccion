package com.example.pruebaTecnica.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * MODEL for User
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "USUARIO", schema = "pruebatecnica")
public class User implements Serializable {

    private static final long serialVersionUID = 6799955020412675540L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IDENTIFICACION")
    private String identification;

    @Column(name = "NOMBRE")
    private String name;

    @Column(name = "TELEFONO")
    private String telephone;
}
