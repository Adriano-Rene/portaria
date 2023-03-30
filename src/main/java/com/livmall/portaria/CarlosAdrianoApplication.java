package com.livmall.portaria;

import com.livmall.portaria.modelos.Funcao;
import com.livmall.portaria.modelos.Usuario;
import com.livmall.portaria.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class CarlosAdrianoApplication {

	/*
	@Bean
	public CommandLineRunner run(@Autowired UsuarioRepositorio repositorio) {
		return  args -> {
			Usuario usuario = Usuario.builder().nome("adriano").dataNascimento(LocalDate.of(1989,04,22)).cpf("07272825499").build();
			Usuario usuario2 = Usuario.builder().nome("carlos").cpf("07272825499").dataNascimento(LocalDate.parse("27/05/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"))).build();
			repositorio.save(usuario);
			repositorio.save(usuario2);
		};
	}
*/


	public static void main(String[] args) {
		SpringApplication.run(CarlosAdrianoApplication.class, args);

	/*	for (Funcao funcao : Funcao.values()){
				System.out.println(funcao);
			}
		*/



		}

}
