package by.sergey.cinemaservicespring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.request.NativeWebRequest;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, NativeWebRequest nativeWebRequest) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests.requestMatchers(antMatcher("/createFilm")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/save")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/updateFilm")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/update")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/deleteFilm")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/getFilms")).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(antMatcher("/getFilmsByDirectorId/{directorId}")).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(antMatcher("/menu")).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(antMatcher("/getAccount")).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(antMatcher("/addWishes")).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(antMatcher("/addWatches")).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(antMatcher("/delete_desired_film")).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(antMatcher("/delete_watched_film")).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(antMatcher("/addUpdateForm")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/saveDirector")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/updateDirector")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/getDirectors")).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(antMatcher("/deleteDirector")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/addActorForm")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/saveActor")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/getActors")).hasAnyAuthority("ADMIN", "USER")
                                .requestMatchers(antMatcher("/updateActor")).hasAuthority("ADMIN")
                                .requestMatchers(antMatcher("/deleteActor")).hasAuthority("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl("/menu", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .permitAll());

        return http.build();

    }
}
