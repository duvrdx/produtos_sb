package br.ifes.dw.helloworld.repositories;

import br.ifes.dw.helloworld.model.Produto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoDatabaseRepository extends GenericRepository<Produto, Long> {
}