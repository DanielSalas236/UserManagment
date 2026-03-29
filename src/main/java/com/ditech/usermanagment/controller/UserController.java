package com.ditech.usermanagment.controller;

import com.ditech.usermanagment.dto.SimpleObjectResponse;
import com.ditech.usermanagment.model.User;
import com.ditech.usermanagment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de usuarios.
 * Expone endpoints para operaciones CRUD y maneja las peticiones HTTP.
 */
@RestController
@RequestMapping("/users")
@Tag(name = "User Type API")
@Validated
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Crea un nuevo usuario en el sistema.
     * Recibe un JSON válido y lo persiste en la base de datos.
     * @param user Datos del usuario a crear
     * @return Respuesta con status 200 confirmando la creación
     */
    @Operation(summary = "Crear usuario", description = "Este método crea un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontró el recurso solicitado"),
            @ApiResponse(responseCode = "500", description = "Error con la conexión a la base de datos")})
    @PostMapping(value = "", produces = "application/json;charset=UTF-8")
    public ResponseEntity<SimpleObjectResponse> create(@NotNull(message = "UserDto no puede ser nulo.") @Valid @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(
                SimpleObjectResponse.builder().code(HttpStatus.OK.value()).message("Usuario creado con éxito").build(),
                HttpStatus.OK);
    }

    /**
     * Obtiene la lista completa de todos los usuarios registrados.
     * @return Respuesta con status 200 y lista de usuarios
     */
    @Operation(summary = "Listar Usuarios", description = "Este método lista todos los usuarios.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listados de usuarios con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontró el recurso solicitado"),
            @ApiResponse(responseCode = "500", description = "Error con la conexión a la base de datos")})
    @GetMapping(value = "", produces = "application/json;charset=UTF-8")
    public ResponseEntity<SimpleObjectResponse> findAll() {
        return new ResponseEntity<>(
                SimpleObjectResponse.builder().code(HttpStatus.OK.value()).message("Listados de usuarios con éxito").value(userService.getAllUsers()).build(),
                HttpStatus.OK);
    }

    /**
     * Busca y retorna un usuario específico por su identificador.
     * @param id ID del usuario a buscar
     * @return Respuesta con status 200 y datos del usuario encontrado
     */
    @Operation(summary = "Buscar Usuario", description = "Este método lista al usuario mediante la búsqueda con su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontró el recurso solicitado"),
            @ApiResponse(responseCode = "500", description = "Error con la conexión a la base de datos")})
    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<SimpleObjectResponse> findById(@PathVariable Long id) {
        return new ResponseEntity<>(
                SimpleObjectResponse.builder().code(HttpStatus.OK.value()).message("Encontrado con éxito").value(userService.getUserById(id)).build(),
                HttpStatus.OK);
    }

    /**
     * Elimina un usuario específico del sistema.
     * @param id ID del usuario a eliminar
     * @return Respuesta con status 200 confirmando la eliminación
     */
    @Operation(summary = "Eliminar Usuario", description = "Este método elimina al usuario mediante su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontró el recurso solicitado"),
            @ApiResponse(responseCode = "500", description = "Error con la conexión a la base de datos")})
    @DeleteMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<SimpleObjectResponse> deleteById(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(
                SimpleObjectResponse.builder().code(HttpStatus.OK.value()).message("Eliminado con éxito").value(null).build(),
                HttpStatus.OK);
    }
}
