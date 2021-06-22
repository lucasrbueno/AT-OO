package br.edu.infnet.AT_OO_Produtos.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.edu.infnet.AT_OO_Produtos.model.negocio.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Integer> {
    @Query(value = "SELECT p FROM Produto p WHERE p.nomeProduto LIKE '%' || :encontrarProduto || '%'")
    public Produto search(@Param("encontrarProduto") String encontrarProduto);
    
    public List<Produto> listaCotacoes();
}

