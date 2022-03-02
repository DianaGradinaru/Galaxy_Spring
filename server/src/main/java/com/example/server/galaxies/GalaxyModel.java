package com.example.server.galaxies;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="galaxies")
public class GalaxyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long sent_by_id;
    private String message;
    private String image;
    private LocalDateTime created_at;
}
