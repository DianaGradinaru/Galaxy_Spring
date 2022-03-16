package com.example.server.galaxies;

import com.example.server.user.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "galaxies")
public class GalaxyModel {

    @SequenceGenerator(
            name = "galaxy_sequence",
            sequenceName = "galaxy_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "galaxy_sequence"
    )
    private Long id;

//    private String username;
//    private Long sent_by_id;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            nullable = false,
//            name = "userid"
//    )
    @JsonIgnore
    private UserModel user;
    private String message;
    private String image;
    private LocalDateTime created_at;
}
