package com.livmall.portaria.controles;


import com.livmall.portaria.dtos.ItemDTO;
import com.livmall.portaria.modelos.Item;
import com.livmall.portaria.modelos.Usuario;
import com.livmall.portaria.repositorio.ItemRepositorio;
import com.livmall.portaria.repositorio.UsuarioRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ItemControle {

private final ItemRepositorio repositorio;
private final UsuarioRepositorio usuarioRepositorio;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item salvar(@RequestBody @Valid ItemDTO itemDTO){

       LocalDate data = LocalDate.parse(itemDTO.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idUsuario = itemDTO.getIdUsuario();

         Usuario usuario =usuarioRepositorio.findById(idUsuario).orElseThrow(() ->
                 new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario inexistente"));


        Item item = new Item();
        item.setNome(itemDTO.getNome());
        item.setDescricao(itemDTO.getDescricao());
        item.setSala(Integer.valueOf(itemDTO.getSala()));
        item.setData(data);
        item.setUsuario(usuario);
        
        return repositorio.save(item);

    }

    @GetMapping("{id}")
    public Item encontrePorId( @PathVariable Integer id){

        return repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Nao Encontrado!!!"));
    }
//
//    @GetMapping
//    public List<Item> obterTodos(){
//        return repositorio.findAll();
//    }

    @GetMapping
    public List<Item> pesquisar(@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
                                @RequestParam(value = "mes", required = false, defaultValue = "") Integer mes){
        return repositorio.findByNomeUsuarioAndMes("%" + nome + "%", mes);

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
