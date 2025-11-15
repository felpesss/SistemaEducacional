package com.sistema.educacional;

import com.sistema.educacional.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

// faz login
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String senha = credentials.get("senha");
        String senhaMD5 = toMD5(senha);
        
        return usuarioRepository.findByEmail(email)
            .filter(usuario -> usuario.getSenha().equals(senhaMD5))
            .map(usuario -> {
                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("usuario", usuario.getNome());
                response.put("grupo", usuario.getIdGrupo());
                return ResponseEntity.ok(response);
            })
            .orElse(ResponseEntity.status(401).body(Map.of("success", false, "message", "Credenciais inválidas")));
    }
    
    // converte senha pra md5
    private String toMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return input;
        }
    }
}
