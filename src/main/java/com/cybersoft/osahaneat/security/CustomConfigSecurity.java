package com.cybersoft.osahaneat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// xài cho config
@EnableWebSecurity
// cho phép xài Security
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomConfigSecurity {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User.withUsername("QKL")
                .password(encoder.encode("12345"))
                .authorities("ADMIN")
//                .roles("ADMIN")
                .build();
        UserDetails user = User.withUsername("QKL2")
                .password(encoder.encode("12345"))
                .roles("USER") // Không cần để chữ ROLE_ đằng trước vì Spring 6 tự thêm
                .build();
        return new InMemoryUserDetailsManager(admin,user);
    }

    //  Author(hasAuthority hay hasAnyAuthority): Xác định có quyền truy cập 1 link cụ thể nào đó không
//  Role(hasRole hay hasAnyRole): role thì lúc tạo user thì phải xài .roles và giá trị của roles phải luôn luôn có prefix
//  ROLE_(tên gì cũng được)
//  - thường mô tả cho việc có quyền để làm 1 chức năng nào đó ví dụ: thêm, xóa, sửa, ...
//  Chỉ được phép có 1 trong 2, có role thì sẽ không có author và ngược lại
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login/signin")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
