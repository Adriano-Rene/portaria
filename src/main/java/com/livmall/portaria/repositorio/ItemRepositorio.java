package com.livmall.portaria.repositorio;

import com.livmall.portaria.modelos.Item;
import com.livmall.portaria.modelos.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepositorio extends JpaRepository<Item, Integer> {

    @Query("select i from Item i join i.usuario u where upper( u.nome ) like upper( :nome ) or  MONTH( i.data) =:mes")
    List<Item> findByNomeUsuarioAndMes(@Param("nome") String nome, @Param("mes") Integer mes);
}
