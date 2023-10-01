package br.ifes.dw.helloworld.controller;

import br.ifes.dw.helloworld.application.AppProdutoJson;
import br.ifes.dw.helloworld.exceptions.IdNotFoundException;
import br.ifes.dw.helloworld.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class ProdutoJsonController {
    @Autowired
    AppProdutoJson appProduto;

    @GetMapping("/")
    public ResponseEntity<List<Produto>> getAll() throws IOException {
        List<Produto> produtos = appProduto.findAll();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@RequestParam long id) throws IOException {
        try {
            Produto produto = appProduto.findById(id);
            return new ResponseEntity<>(produto, HttpStatus.FOUND);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) throws IOException {
        try {
            Produto novoProduto = appProduto.save(produto);
            return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) throws IOException {
        try {
            appProduto.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto) throws IOException {
        try {
            Produto updatedProduto = appProduto.update(produto);
            return new ResponseEntity<>(updatedProduto, HttpStatus.OK);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
