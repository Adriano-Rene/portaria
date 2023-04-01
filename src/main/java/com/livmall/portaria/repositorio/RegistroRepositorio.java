package com.livmall.portaria.repositorio;

import com.livmall.portaria.modelos.Registro;
import com.livmall.portaria.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepositorio extends JpaRepository<Registro, Integer> {
}
