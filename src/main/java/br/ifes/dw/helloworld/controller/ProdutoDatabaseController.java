package br.ifes.dw.helloworld.controller;

import br.ifes.dw.helloworld.application.AppProdutoDatabase;
import br.ifes.dw.helloworld.exceptions.IdNotFoundException;
import br.ifes.dw.helloworld.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db")
public class ProdutoDatabaseController {
    @Autowired
    AppProdutoDatabase appProduto;

    @GetMapping("/")
    public ResponseEntity<List<Produto>> getAll(){
        return new ResponseEntity<>(appProduto.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@RequestParam long id){
        try{
            Produto produto = appProduto.findById(id);
            return new ResponseEntity<>(produto, HttpStatus.FOUND);
        }catch(IdNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/")
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto){
        return new ResponseEntity<>(appProduto.save(produto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteProduto(@PathVariable Long id){
        try{
            Produto produto = appProduto.delete(id);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        }catch(IdNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto){
        try{
            Produto newProduto = appProduto.update(produto);
            return new ResponseEntity<>(newProduto, HttpStatus.OK);
        }catch(IdNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
