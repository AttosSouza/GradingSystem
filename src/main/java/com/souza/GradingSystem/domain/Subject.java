package com.souza.GradingSystem.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "subjects")
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    public Subject(String name) {
        this.name = name;
    }
}
