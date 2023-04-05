package com.UserController;

import com.C10G14.WorldFitBackend.controller.UserController;
import com.C10G14.WorldFitBackend.dto.UserDto;
import com.C10G14.WorldFitBackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;



    @Test
    public void testGetAllUsers() {
        // crea una lista de usuarios simulados
        List<UserDto> usuariosSimulados = new ArrayList<>();
        UserDto usuario1 = new UserDto();
        usuario1.setEmail("user1@example.com");
        usuario1.setAge("25");
        usuario1.setSex("M");
        usuario1.setWeight("70");
        usuario1.setHeight("170");
        usuario1.setProfileImg("profileImg1.jpg");
        usuario1.setClientSince("2020-01-01");
        Set<String> roles1 = new HashSet<>();
        roles1.add("USER");
        usuario1.setRoles(roles1);
        Set<String> routines1 = new HashSet<>();
        routines1.add("Routine1");
        usuario1.setRoutines(routines1);
        usuariosSimulados.add(usuario1);

        UserDto usuario2 = new UserDto();
        usuario2.setEmail("user2@example.com");
        usuario2.setAge("30");
        usuario2.setSex("F");
        usuario2.setWeight("60");
        usuario2.setHeight("160");
        usuario2.setProfileImg("profileImg2.jpg");
        usuario2.setClientSince("2020-01-01");
        Set<String> roles2 = new HashSet<>();
        roles2.add("USER");
        usuario1.setRoles(roles1);
        Set<String> routines2 = new HashSet<>();
        routines2.add("Routine1");
        usuario1.setRoutines(routines1);

        usuariosSimulados.add(usuario2);

        // define el comportamiento del servicio simulado
        when(userService.getAllUsers()).thenReturn(usuariosSimulados);

        // realiza la prueba
        List<UserDto> usuariosObtenidos = userController.getAllUsers();

        // verifica que la lista de usuarios retornada por el controlador sea igual a la simulada
        assertEquals(usuariosSimulados, usuariosObtenidos);

        // verifica que el método getAllUsers del userService fue llamado
        verify(userService).getAllUsers();
    }
    @Test
    public void testGetUserById() {
        // crea un usuario simulado
        UserDto usuarioSimulado = new UserDto();
        usuarioSimulado.setEmail("user@example.com");
        usuarioSimulado.setAge("25");
        usuarioSimulado.setSex("M");
        usuarioSimulado.setWeight("70");
        usuarioSimulado.setHeight("170");
        usuarioSimulado.setProfileImg("profileImg.jpg");
        usuarioSimulado.setClientSince("2020-01-01");
        usuarioSimulado.setRoles(Collections.singleton("USER"));
        usuarioSimulado.setRoutines(Collections.singleton("Routine1"));

        // define el comportamiento del servicio simulado
        when(userService.getUserById(1L)).thenReturn(usuarioSimulado);

        // realiza la prueba
        ResponseEntity<UserDto> response = userController.getUserById(1L);

        // verifica que el código de estado de la respuesta sea 200
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // verifica que el usuario retornado por el controlador sea igual al simulado
        assertEquals(usuarioSimulado, response.getBody());

        // verifica que el método getUserById del userService fue llamado
        verify(userService).getUserById(1L);
    }

    @Test
    public void testCreateUser() {
        // crea un usuario simulado
        UserDto usuarioSimulado = new UserDto();
        usuarioSimulado.setEmail("user@example.com");
        usuarioSimulado.setAge("25");
        usuarioSimulado.setSex("M");
        usuarioSimulado.setWeight("70");
        usuarioSimulado.setHeight("170");
        usuarioSimulado.setProfileImg("profileImg.jpg");
        usuarioSimulado.setClientSince("2020-01-01");
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        usuarioSimulado.setRoles(roles);
        Set<String> routines = new HashSet<>();
        routines.add("Routine1");
        usuarioSimulado.setRoutines(routines);


        // define el comportamiento del servicio simulado
        when(userService.createUser(any(UserDto.class))).thenReturn(usuarioSimulado);

        // realiza la prueba
        ResponseEntity<UserDto> respuesta = userController.createUser(usuarioSimulado);

        // verifica que la respuesta tenga un código HTTP 201 (CREATED)
        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());

        // verifica que la respuesta tenga un cuerpo con el usuario creado
        assertEquals(usuarioSimulado, respuesta.getBody());

        // verifica que el método createUser del userService fue llamado con el usuario simulado
        verify(userService).createUser(eq(usuarioSimulado));
    }

    @Test
    public void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("updatedEmail@example.com");
        userDto.setAge("25");
        userDto.setSex("M");
        userDto.setWeight("70");
        userDto.setHeight("170");
        userDto.setProfileImg("newProfileImg.jpg");
        userDto.setClientSince("2020-01-01");
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        userDto.setRoles(roles);
        Set<String> routines = new HashSet<>();
        routines.add("Routine1");
        routines.add("Routine2");
        userDto.setRoutines(routines);


        when(userService.updateUser(1L, userDto)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.updateUser(1L, userDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());

        // verify that the updateUser method of the userService was called with the correct arguments
        verify(userService).updateUser(1L, userDto);
    }
    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        // realiza la prueba
        ResponseEntity<Void> response = userController.deleteUser(userId);

        // verifica que la respuesta tenga el código 204 (no content)
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // verifica que el método deleteUser del userService fue llamado con el id correcto
        verify(userService).deleteUser(userId);
    }


}


