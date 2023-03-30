package com.livmall.portaria.repositorio;

import com.livmall.portaria.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
}
