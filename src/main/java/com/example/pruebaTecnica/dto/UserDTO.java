package com.example.pruebaTecnica.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for User
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -1605527272130934429L;

    private Long id;

    private String identification;

    private String name;

    private String telephone;
}
