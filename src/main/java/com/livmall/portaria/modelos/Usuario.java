package com.livmall.portaria.modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 150)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @CPF
    @Column(nullable = false, length = 11)
    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    private String cpf;

    @Column(name = "data_nascimento", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataNascimento;

    /*
    @Column
     @NotEmpty
    private
     */

}
