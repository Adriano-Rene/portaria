package com.livmall.portaria.modelos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "Sala", nullable = false)
    private Integer sala;

    @Column(name = "hora_item", updatable = false)
    private LocalTime hora;

    @Column(name = "data_item", updatable = false)
    private LocalDate data;

    @PrePersist
    public void prePersist(){
        setHora(LocalTime.now());
        setData(LocalDate.now());
    }


}
