package com.example.imageUI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"imagewithtags\"")
public class ImWithTags {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inwt_seq")
    @SequenceGenerator(name = "inwt_seq", sequenceName = "hibernate_sequence_iwt", allocationSize = 1)
    private int id;

    @Column(name = "\"uuid\"", nullable = false)
    private UUID uuid = UUID.randomUUID();

    @JoinColumn(name = "\"id_im\"")
    private int id_im;

    @OneToOne
    @JoinColumn(name = "\"id_tg\"")
    private Tag id_tg;

    public ImWithTags(int id_im, Tag id_tg) {
        this.id_im = id_im;
        this.id_tg = id_tg;
    }
}
