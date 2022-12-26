package com.example.imageUI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"values\"")
public class Values {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @Column(name = "\"id_image\"")
    Images id_image;

    @OneToOne
    @Column(name = "\"id_tag\"")
    Tags id_tag;

    @Column(name = "\"value\"")
    float value;

    public Values(Images id_image, Tags id_tag, float value) {
        this.id_image = id_image;
        this.id_tag = id_tag;
        this.value = value;
    }
}
