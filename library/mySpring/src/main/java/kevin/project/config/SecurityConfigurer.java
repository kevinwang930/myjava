package kevin.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfigurer {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        return http.authorizeHttpRequests(
                        authorize -> authorize.requestMatchers(HttpMethod.POST, "/register").permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin(Customizer.withDefaults())
                .userDetailsService(userDetailsService)
                .build();
    }
}
