package co.istad.mbanking.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {
    private final PasswordEncoder encoder;
    private final UserDetailsServiceImpl userDetailsService;

//    Define in-memory user
//@Bean
//public InMemoryUserDetailsManager userDetailsService() {
//    InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//    UserDetails admin = User.builder()
//                    .username("admin")
//                    .password("{noop}12345")
//                    .roles("ADMIN")
//                    .build();
//    UserDetails goldUser = User.builder()
//                    .username("gold")
//                    .password("{noop}12345")
//                    .roles("ACCOUNT")
//                    .build();
//    UserDetails user = User.builder()
//            .username("user")
//            .password("{noop}12345")
//            .roles("USER")
//            .build();
//    userDetailsManager.createUser(admin);
//    userDetailsManager.createUser(goldUser);
//    userDetailsManager.createUser(user);
//    return userDetailsManager;
//}
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
// // use csrf token to take the form login security of
//        http.csrf(csrf -> csrf.disable());
//
//
////Authorize Url mapping
//        http.authorizeHttpRequests(request ->{
//                    request.requestMatchers("/api/v1/users").hasAnyRole("ADMIN");
//                    request.requestMatchers("/api/v1/account-types/**", "/api/v1/files/**").hasAnyRole("ACCOUNT", "USER");
//                    request.anyRequest().permitAll();
//                });
//
////        Security mechanism
//        http.httpBasic();
//
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);// Make security http stateless
//        return http.build();
//    }

    // Define user auth with jdbc db
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(encoder);
        return auth;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/v1/auth/**").permitAll();
            auth.requestMatchers("/api/v1/account-types/**").permitAll();
            auth.requestMatchers("/api/v1/accounts/**").permitAll();
            auth.requestMatchers("/api/v1/users/**").permitAll();

            auth.requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasAnyRole("SYSTEM","ADMIN");
            auth.requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasRole("SYSTEM");
            auth.anyRequest().authenticated();
        });
        http.httpBasic();


//        Make your API stateless
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
