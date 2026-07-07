package com.schedulecontroldevelopproject.common.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component //UserService, LoginService에서 @RequiredArgsConstructor로 주입받아 쓸 수 있게 gka
public class PasswordEncoder {

    public String encode(String rawPassword){ // 비밀번호를 받아 암호화된 해시 문자열로 변환
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    public boolean matches(String rawPassword, String encodedPassword){
        //입력한 비밀번호가, DB에 저장된 암호화 된 비밀번호와 일치하는지 검증
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }

}
