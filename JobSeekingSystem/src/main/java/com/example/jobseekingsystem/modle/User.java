package com.example.jobseekingsystem.modle;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 5 , message = "name must be over than 5")
    @Pattern(regexp = ("^[a-zA-Z]*$"))
    @NotEmpty(message = "name cant be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @Email(message = "Must be valid Email")
    @Column(columnDefinition = " varchar (40)")
    private String email;
    @NotEmpty(message = "password cant be empty")
    @Column(columnDefinition = " varchar (25) not null")
    private String password;
    @NotNull(message = " age cant be null")
    @Positive(message = "age  Must be Positive")
    @Min(value = 22, message = " age Must be over than 21")
    @Column(columnDefinition = "int not null")
    private Integer age;
    @NotEmpty(message = "Role cant be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$", message = "Role must be JOB_SEEKER OR EMPLOYER Only ")
    @Column(columnDefinition = "varchar(20) ")
    private String role;


}
