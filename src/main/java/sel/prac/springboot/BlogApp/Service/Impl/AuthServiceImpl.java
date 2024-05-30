package sel.prac.springboot.BlogApp.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sel.prac.springboot.BlogApp.Entity.Role;
import sel.prac.springboot.BlogApp.Entity.User;
import sel.prac.springboot.BlogApp.Exception.BlogAPIException;
import sel.prac.springboot.BlogApp.Payload.LoginDTO;
import sel.prac.springboot.BlogApp.Payload.RegisterDTO;
import sel.prac.springboot.BlogApp.Repository.RoleRepository;
import sel.prac.springboot.BlogApp.Repository.UserRepository;
import sel.prac.springboot.BlogApp.Security.JwtTokenProvider;
import sel.prac.springboot.BlogApp.Service.AuthServiceInterface;

import java.util.HashSet;
import java.util.Set;


@Service
public class AuthServiceImpl implements AuthServiceInterface {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDTO loginDTO) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( loginDTO.getUsernameOrEmail(),loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token=jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDTO registerDTO) {

        //check for usrname exist in database
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            throw new BlogAPIException("Username exist",HttpStatus.BAD_REQUEST);
        }


        //user email exist in database
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new BlogAPIException("Email exist",HttpStatus.BAD_REQUEST);
        }

        User user= new User();
        user.setName(registerDTO.getName());
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles=new HashSet<>();
        Role userrole=roleRepository.findByName("ROLE_USER").get();

        roles.add(userrole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User Register successfully";
    }
}
