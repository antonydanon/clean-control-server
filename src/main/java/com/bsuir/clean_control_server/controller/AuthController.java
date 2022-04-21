package com.bsuir.clean_control_server.controller;

import com.bsuir.clean_control_server.dto.LoginRequestDTO;
import com.bsuir.clean_control_server.dto.LoginResponseDTO;
import com.bsuir.clean_control_server.exception.ExceptionWithHttpStatus;
import com.bsuir.clean_control_server.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login/worker")
    public ResponseEntity<?> loginWorker(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        try {
            return new ResponseEntity<>(authenticationService.loginWorker(loginRequestDTO),
                    HttpStatus.OK);
        } catch (ExceptionWithHttpStatus e) {
            return new ResponseEntity<>(authenticationService.createNegativeResponse(),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/login/manager")
    public ResponseEntity<LoginResponseDTO> loginManager(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        try {
            return new ResponseEntity<>( authenticationService.loginManager(loginRequestDTO),
                    HttpStatus.OK);
        } catch (ExceptionWithHttpStatus e) {
            return new ResponseEntity<>(authenticationService.createNegativeResponse(),
                    HttpStatus.UNAUTHORIZED);
        }
    }
}
