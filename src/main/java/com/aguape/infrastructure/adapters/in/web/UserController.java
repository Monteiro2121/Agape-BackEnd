package com.aguape.infrastructure.adapters.in.web; // Pacote correto


import com.aguape.application.service.UserService;
import com.aguape.infrastructure.adapters.in.web.dto.ApiResponse;
import com.aguape.infrastructure.adapters.in.web.dto.LoginRequestDTO;
import com.aguape.infrastructure.adapters.in.web.dto.LoginResponseDTO;
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

    private final UserController userController;

    @PostMapping("/login")
    @Operation(summary = "Realiza login e retorna JWT")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(
            @RequestBody @Valid LoginRequestDTO dto) {

        LoginResponseDTO response = userController.login(dto);

        // Se você não tiver a classe ApiResponse, pode usar:
        // return ResponseEntity.ok(response);
        return ResponseEntity.ok(ApiResponse.success(response, "Login realizado com sucesso"));
    }
}