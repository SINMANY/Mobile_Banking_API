package co.istad.mbanking.api.auth.web;

import co.istad.mbanking.api.auth.AuthService;
import co.istad.mbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthService authService;
    @PostMapping("/register")
    public BaseRest<?> register(@Valid @RequestBody RegisterDto registerDto){

        authService.register(registerDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been registered successfully.")
                .timestamp(LocalDateTime.now())
                .data(registerDto.email())
                .build();
    }

    @PostMapping("/verify")
    public BaseRest<?> verify(@RequestParam String email){
        authService.verify(email);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been verified successfully.")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }

    @GetMapping("/check-verify")
    public BaseRest<?> checkVerify(@RequestParam String email, @RequestParam String verifiedCode){

        authService.checkVerify(email,verifiedCode);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been verified successfully.")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }
}