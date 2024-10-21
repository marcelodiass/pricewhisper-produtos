package br.com.omcorp.pricewhisper.services;

import br.com.omcorp.pricewhisper.model.Usuario;
import br.com.omcorp.pricewhisper.model.UsuarioRole;
import br.com.omcorp.pricewhisper.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        Usuario usuario = new Usuario();
        usuario.setRole(UsuarioRole.ADMIN);
        usuario.setNome("Admin");
        usuario.setUsername("admin");
        usuario.setPassword(passwordEncoder.encode("admin"));
        usuarioRepository.save(usuario);
    }

    public void register(Usuario usuario) {
        usuario.setRole(UsuarioRole.USER);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
