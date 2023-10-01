package br.ifes.dw.helloworld.application;

import br.ifes.dw.helloworld.exceptions.IdNotFoundException;
import br.ifes.dw.helloworld.model.Produto;
import br.ifes.dw.helloworld.repositories.JsonProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AppProdutoJson {

    private final JsonProdutoRepository produtoRepository;

    @Autowired
    public AppProdutoJson(JsonProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll() throws IOException {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) throws IOException {
        Produto produto = produtoRepository.findById(id);

        if(produto != null){
            return produto;
        }

        throw new IdNotFoundException();
    }

    public Produto save(Produto produto) throws IOException {
        produtoRepository.save(produto);
        return produto;
    }

    public void delete(Long id) throws IOException {
        if(produtoRepository.findById(id) != null){
            produtoRepository.delete(id);
        }else{
            throw new IdNotFoundException();
        }
    }

    public Produto update(Produto novoProduto) throws IOException {
        Produto produtoExistente = produtoRepository.findById(novoProduto.getId());

        if (produtoExistente != null) {
            produtoExistente.setNome(novoProduto.getNome());
            produtoExistente.setPreco(novoProduto.getPreco());
            produtoRepository.update(produtoExistente);
            return novoProduto;
        }
        throw new IdNotFoundException();
    }
}
