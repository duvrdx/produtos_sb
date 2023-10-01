package br.ifes.dw.helloworld.repositories;

import br.ifes.dw.helloworld.model.Produto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.File;
import java.io.IOException;
import java.util.List;

@NoRepositoryBean
public interface GenericRepository<T, ID> extends JpaRepository<T, ID>{
}