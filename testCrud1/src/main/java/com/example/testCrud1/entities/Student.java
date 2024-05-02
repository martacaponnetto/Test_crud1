package com.example.testCrud1.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "surname")
    private String surname;
    @Column(name = "is Working")
    private boolean isWorking;


}
