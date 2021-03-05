package com.example.sweater.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //включаем авторизацию
                    .antMatchers("/", "/registration").permitAll() //для главное странички разрешаем полнй доступ
                    .anyRequest().authenticated() //для остальных запросов требуем авторизацию
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
        // нужен для того, чтобы менеджер мог ходить в базу данных и искать там users и их roles
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
        // шифрует пароли, чтобы они у нас не хранились в явном виде
                .usersByUsernameQuery("select username, password, active from usr where username=?")
        // нужен чтобы сиситема могла найти user'а по его username
        // username, password, active обязательно должны быть в таком порядке иначе что-то пойдет не так
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
        // помогает Spring'у получить список пользователей с их roles
    }
}