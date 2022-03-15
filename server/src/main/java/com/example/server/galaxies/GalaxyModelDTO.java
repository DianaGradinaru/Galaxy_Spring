package com.example.server.galaxies;

import com.example.server.user.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GalaxyModelDTO {

    private Long id;
//    private String username;
//    private Long sent_by_id;
    private UserModel userModel;
    private String message;
    private String image;
    private LocalDateTime created_at;
}
