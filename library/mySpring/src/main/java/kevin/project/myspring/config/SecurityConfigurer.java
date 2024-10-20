package kevin.project.myspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;



public class SecurityConfigurer {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
//        return http.authorizeHttpRequests(
//                        authorize -> authorize.requestMatchers(HttpMethod.POST, "/register").permitAll()
//                                .anyRequest()
//                                .authenticated())
//                .formLogin(Customizer.withDefaults())
//                .userDetailsService(userDetailsService)
//                .build();
//    }
}
