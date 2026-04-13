package com.aguape.infrastructure.adapters.in.web;

import com.aguape.application.service.UserService;
import com.aguape.infrastructure.adapters.in.web.dto.ApiResponse;
import com.aguape.infrastructure.adapters.in.web.dto.LoginRequestDTO;
import com.aguape.infrastructure.adapters.in.web.dto.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação")
public class UserController {

    // 1. Aqui você injeta o SERVICE (que tem a lógica), não o controller.
    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Realiza login e retorna JWT")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(
            @RequestBody @Valid LoginRequestDTO dto) {

        // 2. Você chama o SERVICE. Ele te entrega o DTO puro.
        LoginResponseDTO response = userService.login(dto);

        // 3. Agora você monta a resposta de sucesso com o DTO que recebeu.
        return ResponseEntity.ok(ApiResponse.success(response, "Login realizado com sucesso"));
    }
}