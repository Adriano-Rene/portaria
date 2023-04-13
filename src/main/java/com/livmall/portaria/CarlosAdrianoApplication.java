package com.livmall.portaria;

import com.livmall.portaria.modelos.Item;
import com.livmall.portaria.modelos.Registro;
import com.livmall.portaria.modelos.Usuario;
import com.livmall.portaria.modelos.enums.Funcao;
import com.livmall.portaria.repositorio.ItemRepositorio;
import com.livmall.portaria.repositorio.RegistroRepositorio;
import com.livmall.portaria.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class CarlosAdrianoApplication {




	@Bean
	public CommandLineRunner run(@Autowired UsuarioRepositorio repositorio, @Autowired RegistroRepositorio repositorio2
	,@Autowired ItemRepositorio repositorio3) {
		return  args -> {
			Usuario usuario = Usuario.builder().nome("adriano").dataNascimento(LocalDate.of(1989,04,22))
					.cpf("07272825499").funcao(Funcao.PORTEIRO).telefone(988776655).build();
			Usuario usuario2 = Usuario.builder().nome("carlos").cpf("07272825499").
					dataNascimento(LocalDate.parse("27/05/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"))).
					funcao(Funcao.ADMINISRATIVO).telefone(988776655).build();


			repositorio.save(usuario);
			repositorio.save(usuario2);

			Registro registro = Registro.builder().sala(411).servico("pintura").documento("1234567")
					.empresa("EDR").telefone(88776655).usuario(usuario).build();
			Registro registro2 = Registro.builder().sala(123).servico("Piso").documento("32165498")
					.empresa("CAR").telefone(11111111).usuario(usuario2).build();

			repositorio2.save(registro);
			repositorio2.save(registro2);

			Item item = Item.builder().sala(523).nome("talita").descricao("produto da shopee").usuario(usuario).build();
			Item item2 = Item.builder().sala(111).nome("marcos").descricao("HT do shopping").usuario(usuario2).build();

			repositorio3.save(item);
			repositorio3.save(item2);

		};
	}



	public static void main(String[] args) {
		SpringApplication.run(CarlosAdrianoApplication.class, args);

	/*	for (Funcao funcao : Funcao.values()){
				System.out.println(funcao);
			}
		*/



		}

}
