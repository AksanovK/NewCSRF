package ru.itis.javalab.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String UUID;
    private String password;
    private String email;
}
