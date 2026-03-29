package com.ditech.usermanagment.service;

import com.ditech.usermanagment.exception.EntityNotFoundException;
import com.ditech.usermanagment.model.User;
import com.ditech.usermanagment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de lógica de negocio para la gestión de usuarios.
 * Coordina las operaciones CRUD y aplica validaciones de negocio.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Guarda un nuevo usuario o actualiza uno existente.
     * @param user Usuario a guardar
     * @return Usuario guardado con ID asignado
     */
    public User saveUser(User user){
        return userRepository.save(user);
    }

    /**
     * Obtiene la lista de todos los usuarios registrados en el sistema.
     * @return Lista de usuarios
     */
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Busca un usuario específico por su identificador.
     * @param id ID del usuario a buscar
     * @return Usuario encontrado
     * @throws EntityNotFoundException si el usuario no existe
     */
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + id));
    }

    /**
     * Elimina un usuario del sistema.
     * @param id ID del usuario a eliminar
     * @throws EntityNotFoundException si el usuario no existe
     */
    public void deleteUser(Long id){
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con id: " + id);
        }
        userRepository.deleteById(id);
    }
}
