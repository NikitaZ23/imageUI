package com.example.imageUI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"tags\"")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "\name\"", nullable = false)
    private String name;

    @CreatedDate
    @Column(name = "\"created\"", updatable = false)
    protected LocalDateTime created = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "\"modified\"")
    protected LocalDateTime modified = LocalDateTime.now();

    public Tag(String name) {
        this.name = name;
    }
}
