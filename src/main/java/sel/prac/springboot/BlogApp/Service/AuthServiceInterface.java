package sel.prac.springboot.BlogApp.Service;

import sel.prac.springboot.BlogApp.Payload.LoginDTO;
import sel.prac.springboot.BlogApp.Payload.RegisterDTO;

public interface AuthServiceInterface {

    String login(LoginDTO loginDTO);

    String register(RegisterDTO registerDTO);
}
