package com.example.server.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModelDTO {
    private Long userid;
    private String username;
    private String email;


}
