package com.livmall.portaria.controles;


import com.livmall.portaria.modelos.Registro;
import com.livmall.portaria.repositorio.RegistroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/registro")
@CrossOrigin("http://localhost:4200")
public class RegistroControle {

private final RegistroRepositorio repositorio;

    @Autowired
    public RegistroControle(RegistroRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Registro salvar(@RequestBody @Valid Registro Registro){
        return repositorio.save(Registro);
    }

    @GetMapping("{id}")
    public Registro encontrePorId( @PathVariable Integer id){

        return repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro Nao Encontrado!!!"));
    }

    @GetMapping
    public List<Registro> obterTodos(){
        return repositorio.findAll();
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePorId(@PathVariable Integer id){

        repositorio.findById(id).map(Registro -> {repositorio.delete(Registro);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro Nao Encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody Registro RegistroAtualizado){

        repositorio.findById(id).map(Registro -> {
            RegistroAtualizado.setId(Registro.getId());
            RegistroAtualizado.setUsuario(Registro.getUsuario());
            return repositorio.save(RegistroAtualizado);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro Nao Encontrado"));

    }

}
