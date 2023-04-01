package com.livmall.portaria.controles;


import com.livmall.portaria.modelos.Usuario;
import com.livmall.portaria.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("http://localhost:4200")
public class UsuarioControle {

private final UsuarioRepositorio repositorio;

    @Autowired
    public UsuarioControle(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        return repositorio.save(usuario);
    }

    @GetMapping("{id}")
    public Usuario encontrePorId( @PathVariable Integer id){

        return repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Nao Encontrado!!!"));
    }

    @GetMapping
    public List<Usuario> obterTodos(){
        return repositorio.findAll();
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePorId(@PathVariable Integer id){

        repositorio.findById(id).map(usuario -> {repositorio.delete(usuario);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Nao Encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody Usuario usuarioAtualizado){

        repositorio.findById(id).map(usuario -> {
            usuarioAtualizado.setId(usuario.getId());
            return repositorio.save(usuarioAtualizado);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario Nao Encontrado"));

    }

}
