package com.aguape.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter // Essencial para o user.getRole() funcionar
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING) // Garante que salve "ADMIN" ou "USER" no banco
    private UserRole role; // O campo precisa se chamar 'role'
}