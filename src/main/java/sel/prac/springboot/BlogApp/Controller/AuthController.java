package sel.prac.springboot.BlogApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sel.prac.springboot.BlogApp.Payload.JWTAuthResponse;
import sel.prac.springboot.BlogApp.Payload.LoginDTO;
import sel.prac.springboot.BlogApp.Payload.RegisterDTO;
import sel.prac.springboot.BlogApp.Service.AuthServiceInterface;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthServiceInterface authServiceInterface;


    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO){
        String token= authServiceInterface.login(loginDTO);

        JWTAuthResponse jwtAuthResponse= new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return  ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping(value = {"/register" ,"/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){

        String response=authServiceInterface.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
