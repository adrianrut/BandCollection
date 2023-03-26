package pl.rutkowski.bandcollection.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests.requestMatchers("/styles/**").permitAll());
        http.authorizeHttpRequests(requests -> requests.requestMatchers("/").permitAll());
        http.authorizeHttpRequests(requests -> requests.requestMatchers("/register").permitAll());
        http.authorizeHttpRequests(requests -> requests.requestMatchers("/register/**").permitAll());
        http.authorizeHttpRequests(request -> request.requestMatchers("/admin").hasRole("ADMIN"));
        http.authorizeHttpRequests(request -> request.requestMatchers("/user").hasRole("ADMIN"));
        http.authorizeHttpRequests(request -> request.requestMatchers("/user").hasRole("USER"));
        http.authorizeHttpRequests(requests -> requests.requestMatchers(PathRequest.toH2Console()).permitAll());
        http.authorizeHttpRequests(requests -> requests.anyRequest().authenticated());
        http.formLogin(login -> login.loginPage("/login").permitAll().defaultSuccessUrl("/band/homepage", true));
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).logoutSuccessUrl("/");
        http.csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()));
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
