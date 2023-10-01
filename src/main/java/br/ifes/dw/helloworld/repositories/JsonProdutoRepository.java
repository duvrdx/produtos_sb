package br.ifes.dw.helloworld.repositories;

import br.ifes.dw.helloworld.model.Produto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class JsonProdutoRepository implements GenericJsonRepository<Produto, Long> {

    private final ObjectMapper objectMapper;
    private final File file;
    private Long nextId;

    public JsonProdutoRepository() throws IOException {
        this.objectMapper = new ObjectMapper();
        this.file = new File("produtos.json");
        if (!file.exists()) {
            file.createNewFile();
        }

        // Obtém o valor do último ID do JSON
        List<Produto> produtos = findAll();
        if (produtos.isEmpty()) {
            nextId = 0L;
        } else {
            nextId = produtos.get(produtos.size() - 1).getId() + 1L;
        }
    }

    @Override
    public List<Produto> findAll() throws IOException {
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Produto.class));
    }

    @Override
    public Produto findById(Long id) throws IOException {
        List<Produto> produtos = findAll();
        for (Produto produto : produtos) {
            if (produto.getId().equals(id)) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public Produto save(Produto produto) throws IOException {
        List<Produto> produtos = findAll();
        produto.setId(nextId++);
        produtos.add(produto);
        objectMapper.writeValue(file, produtos);
        return produto;
    }

    @Override
    public void delete(Long id) throws IOException {
        List<Produto> produtos = findAll();

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(id)) {
                produtos.remove(i);
                break;
            }
        }
        objectMapper.writeValue(file, produtos);
    }

    @Override
    public Produto update(Produto produto) throws JsonProcessingException, IOException {
        List<Produto> produtos = findAll();
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId().equals(produto.getId())) {
                produtos.set(i, produto);
                break;
            }
        }
        objectMapper.writeValue(file, produtos);
        return produto;
    }
}
