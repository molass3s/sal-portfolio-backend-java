package org.personal.salportfoliobackend.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
        
    // TODO Change test credential
    // This is only for initial setup and testing. 
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("sal").password("abc123").authorities("USER");
//    } 
//    
//    // HttpSecurity for POST API calls
//    @Configuration
//    @Order(1)
//    public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//            .antMatchers(HttpMethod.POST, "/api/**").hasAuthority("USER")
//            .and()
//            .formLogin().loginProcessingUrl("/userLogin")
//            .and()
//            .csrf().disable();
//        }
//    }
//    
//    
//    @Configuration
//    public static class PublicWebSecurityConfigurerAdpater extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                .antMatchers(HttpMethod.GET)
//                .permitAll();
//        }
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest()
            .permitAll()
            .and()
            .csrf().disable();
    }
}
