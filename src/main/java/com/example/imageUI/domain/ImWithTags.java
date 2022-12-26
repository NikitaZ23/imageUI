package com.example.imageUI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"imagewithtags\"")
public class ImWithTags {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JoinColumn(name = "\"id_im\"")
    private int id_im;

    @JoinColumn(name = "\"id_tg\"")
    private int id_tg;

    public ImWithTags(int id_im, int id_tg) {
        this.id_im = id_im;
        this.id_tg = id_tg;
    }
}
