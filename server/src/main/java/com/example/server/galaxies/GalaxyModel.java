package com.example.server.galaxies;

import com.example.server.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "galaxies")
public class GalaxyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //    private String username;
//
//    private Long sent_by_id;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "userid"
    )
    private UserModel user;
    private String message;
    private String image;
    private LocalDateTime created_at;
}
