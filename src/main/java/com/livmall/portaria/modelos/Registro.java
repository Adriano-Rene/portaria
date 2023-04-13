package com.livmall.portaria.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "nome")
    private Usuario usuario;


    @Column(nullable = false,length = 150)
    @NotEmpty(message = "{campo.empresa.obrigatorio}")
    private String empresa;

    @Column(nullable = false,length = 150)
    @NotEmpty(message = "{campo.documento.obrigatorio}")
    private String documento;

    @Column(nullable = false)
    private Integer telefone;

    @Column(nullable = false)
    private Integer sala;

    @Column(nullable = false,length = 150)
    @NotEmpty(message = "{campo.servico.obrigatorio}")
    private String servico;

    @Column(name = "hora_registro", updatable = false)
    private LocalTime hora;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_registro", updatable = false)
    private LocalDate data;

    @PrePersist
    public void prePersist(){
        setHora(LocalTime.now());
        setData(LocalDate.now());
    }
}

