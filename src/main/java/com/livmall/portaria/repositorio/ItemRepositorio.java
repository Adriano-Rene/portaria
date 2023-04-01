package com.livmall.portaria.repositorio;

import com.livmall.portaria.modelos.Item;
import com.livmall.portaria.modelos.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepositorio extends JpaRepository<Item, Integer> {
}
