package br.ifes.dw.helloworld.application;

import br.ifes.dw.helloworld.exceptions.IdNotFoundException;
import br.ifes.dw.helloworld.model.Produto;
import br.ifes.dw.helloworld.repositories.ProdutoDatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppProdutoDatabase {

    private final ProdutoDatabaseRepository produtoRepository;

    @Autowired
    public AppProdutoDatabase(ProdutoDatabaseRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElseThrow(IdNotFoundException::new);
    }

    public Produto save(Produto produto) {
        produtoRepository.save(produto);
        return produto;
    }

    public Produto delete(Long id){
        Produto produto = findById(id);
        produtoRepository.deleteById(id);
        return produto;
    }

    public Produto update(Produto novoProduto) {
        Produto produtoExistente = produtoRepository.findById(novoProduto.getId()).orElseThrow(IdNotFoundException::new);

        if (produtoExistente != null) {
            produtoExistente.setNome(novoProduto.getNome());
            produtoExistente.setPreco(novoProduto.getPreco());
            produtoRepository.save(produtoExistente);
            return novoProduto;
        }
        throw new IdNotFoundException();
    }
}
