package com.example.pruebaTecnica.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for TASK
 *
 * @author Adriana Villamizar Vera
 * @since 1.0
 */

@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
public class TaskDTO implements Serializable {

    private static final long serialVersionUID = 5472321489948527296L;

    private Long id;

    private Long idUser;


    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @NotNull
    private String description;

    @NotNull
    private String name;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date registerDate;

    @NotNull
    private String status;

    private String userName;
}
