package sel.prac.springboot.BlogApp.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {


    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        (authorize) -> //authorize.anyRequest().authenticated() <- authenticate all request
                                       authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                                               .requestMatchers("/api/v1/auth/**").permitAll()

                                               .anyRequest().authenticated()


                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    //inmemory user
    /*@Bean
    public UserDetailsService userDetailsService(){
        UserDetails a= User.builder().username("a").password(passwordEncoder().encode("a")).roles("ADMIN").build();
        UserDetails b= User.builder().username("b").password(passwordEncoder().encode("b")).roles("USER").build();
        UserDetails c= User.builder().username("c").password(passwordEncoder().encode("c")).roles("USER").build();

        return new InMemoryUserDetailsManager(a,b,c);
    }*/
}
