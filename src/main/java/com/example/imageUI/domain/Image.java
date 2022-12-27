package com.example.imageUI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"images\"")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "images_seq")
    @SequenceGenerator(name = "images_seq", sequenceName = "hibernate_sequence_im", allocationSize = 1)
    private int id;

    @Column(name = "\"uuid\"", nullable = false)
    private UUID uuid = UUID.randomUUID();

    @Column(name = "\"name\"")
    private String name;

    @CreatedDate
    @Column(name = "\"created\"", updatable = false)
    protected LocalDateTime created = LocalDateTime.now();

    @LastModifiedDate
    @Column(name = "\"modified\"")
    protected LocalDateTime modified = LocalDateTime.now();

    public Image(String name) {
        this.name = name;
    }
}
