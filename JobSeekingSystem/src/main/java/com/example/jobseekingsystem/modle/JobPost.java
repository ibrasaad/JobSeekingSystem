package com.example.jobseekingsystem.modle;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title cant be empty")
    @Size(min = 5, max = 20, message = "title must be more than 4 characters")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;


    @NotEmpty(message = "description cannot be empty")
    @Size(min = 5,  message = "description must be more than 4 characters   ")
    @Column(columnDefinition = "varchar(100) not null")
    private String description;


    @NotEmpty(message = "location cannot be empty")
    @Size(min = 5, max = 30, message = "location must be more than 4 characters and less than 30")
    @Column(columnDefinition = "varchar(30) not null")
    private String location;

    @NotNull(message = "salary must not be null")
    @PositiveOrZero(message = "salary must be positive")
    @Column(columnDefinition = "double not null")
    private Double salary;


    @NotNull(message = "salary must not be null")
    @Column(columnDefinition = "datetime not null")
    private LocalDate postingDate;
}
