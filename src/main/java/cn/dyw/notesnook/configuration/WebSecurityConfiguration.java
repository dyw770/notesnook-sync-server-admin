package cn.dyw.notesnook.configuration;

import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * @author dyw770
 * @date 2024-11-26
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) throws Exception {
        
        
        http.authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/doc/**").permitAll()
                                .requestMatchers("/webjars/**").permitAll()
                                .requestMatchers("/favicon.ico").permitAll()
                                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                                .anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
