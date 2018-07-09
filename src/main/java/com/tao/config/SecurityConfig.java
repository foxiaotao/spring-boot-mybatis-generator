package com.tao.config;

import com.tao.service.impl.UrlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created with suntao on 2018/6/27
 */
@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    SessionRegistry sessionRegistry;

    @Autowired
    private UrlUserService urlUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginProcessingUrl("/loginProcess")
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/welcome")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(urlUserService).passwordEncoder(new PasswordEncoder() {

            @Override
            public String encode(CharSequence rawPassword) {
                return (String)rawPassword;//MD5Util.encode((String) rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                System.out.println(encodedPassword + "---" + (String)rawPassword);
                return encodedPassword.equals((String) rawPassword);
            }
        });
    }
//
//    @Bean
//    public SessionRegistry getSessionRegistry() {
//        SessionRegistry sessionRegistry = new SessionRegistryImpl();
//        return sessionRegistry;
//    }
}
