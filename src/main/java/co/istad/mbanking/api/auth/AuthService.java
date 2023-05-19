package co.istad.mbanking.api.auth;

import co.istad.mbanking.api.auth.web.AuthDto;
import co.istad.mbanking.api.auth.web.LoginDto;
import co.istad.mbanking.api.auth.web.RegisterDto;

public interface AuthService {

    void  register(RegisterDto registerDto);

    void verify(String email);

    void checkVerify(String email, String code);

    AuthDto login(LoginDto loginDto);
}
