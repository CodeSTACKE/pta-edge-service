package com.cognizant.ptaedgeservice.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PasswordEncoder encoder;
    private final JwtConverter converter;
    @Autowired
    DataSource dataSource;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    // Secure endpoints
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors(); // adds handling for the OPTIONS pre-flight
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers(HttpMethod.POST, "/refresh_token").authenticated()
                .antMatchers(HttpMethod.GET, "/resource").permitAll()
                .antMatchers(HttpMethod.POST, "/resource").permitAll()
                .antMatchers(HttpMethod.PUT, "/resource").permitAll()
                .antMatchers(HttpMethod.DELETE, "/resource").permitAll()
                .antMatchers(HttpMethod.PUT, "/comment").permitAll()
                .antMatchers(HttpMethod.DELETE, "/comment").permitAll()
                .antMatchers(HttpMethod.POST, "/comment").permitAll()
                .antMatchers(HttpMethod.PUT, "/comment").permitAll()
                .antMatchers(HttpMethod.DELETE, "/comment").permitAll()
                .and()
//                .anyRequest().authenticated()
                 .addFilter(new JwtRequestFilter(authenticationManager(), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery("select id, username, authority from authorities where username = ?")
                .passwordEncoder(encoder);

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {

        // Configure CORS globally versus
        // controller-by-controller.
        // Can be combined with @CrossOrigin.

        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("*")
                        .allowCredentials(true)
                        .allowedHeaders("*");
            }
        };
    }


}
