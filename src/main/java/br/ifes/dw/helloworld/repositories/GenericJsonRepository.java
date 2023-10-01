package br.ifes.dw.helloworld.repositories;

import java.io.IOException;
import java.util.List;

public interface GenericJsonRepository<T, ID>{
    public List<T> findAll() throws IOException;
    public T findById(Long id) throws IOException;
    public T save(T entity) throws IOException;
    public void delete(ID id) throws IOException;
    public T update(br.ifes.dw.helloworld.model.Produto produto) throws IOException;
}
