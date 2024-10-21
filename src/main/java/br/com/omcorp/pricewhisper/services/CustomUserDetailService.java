package br.com.omcorp.pricewhisper.services;

import br.com.omcorp.pricewhisper.model.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario byUsername = usuarioService.findByUsername(username);

        if (byUsername == null) {
            log.error("Erro ao encontrar usuário.");
            return null;
        }

        return User.builder()
                .username(byUsername.getUsername())
                .password(byUsername.getPassword())
                .roles(byUsername.getRole().name())
                .build();
    }
}
