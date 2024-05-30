package sel.prac.springboot.BlogApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sel.prac.springboot.BlogApp.Payload.LoginDTO;
import sel.prac.springboot.BlogApp.Payload.RegisterDTO;
import sel.prac.springboot.BlogApp.Service.AuthServiceInterface;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthServiceInterface authServiceInterface;


    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        String response= authServiceInterface.login(loginDTO);

        return  ResponseEntity.ok(response);
    }

    @PostMapping(value = {"/register" ,"/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){

        String response=authServiceInterface.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
