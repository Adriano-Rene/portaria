package com.livmall.portaria.controles;


import com.livmall.portaria.modelos.Item;
import com.livmall.portaria.repositorio.ItemRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin("http://localhost:4200")
public class ItemControle {

private final ItemRepositorio repositorio;

    @Autowired
    public ItemControle(ItemRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item salvar(@RequestBody @Valid Item Item){
        return repositorio.save(Item);
    }

    @GetMapping("{id}")
    public Item encontrePorId( @PathVariable Integer id){

        return repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Nao Encontrado!!!"));
    }

    @GetMapping
    public List<Item> obterTodos(){
        return repositorio.findAll();
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePorId(@PathVariable Integer id){

        repositorio.findById(id).map(Item -> {repositorio.delete(Item);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Nao Encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody Item ItemAtualizado){

        repositorio.findById(id).map(Item -> {
            ItemAtualizado.setId(Item.getId());
            return repositorio.save(ItemAtualizado);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Nao Encontrado"));

    }

}
