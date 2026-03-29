package com.ditech.usermanagment.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que representa un usuario en el sistema.
 * Se persiste en la base de datos y gestiona la información básica de acceso.
 */
@Setter
@Getter
@Entity
@Table(name = "users")
@Schema(description = "Entidad para administrar la información de los usuarios")
public class User {
    @Id
    @GeneratedValue
    private Long id;  // Identificador único del usuario
    
    private String username;  // Nombre de usuario para login
    
    private String email;  // Correo electrónico del usuario
    
    private boolean active;  // Indica si el usuario está activo en el sistema
}
