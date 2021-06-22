package br.edu.infnet.AT_OO_Cotacoes.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.edu.infnet.AT_OO_Cotacoes.model.negocio.Cotacao;
import br.edu.infnet.AT_OO_Cotacoes.model.negocio.Produto;

@Repository
public interface ICotacaoRepository extends JpaRepository<Cotacao, Integer> {
    @Query(value = "SELECT p FROM Produto p WHERE p.nomeProduto LIKE '%' || :encontrarProduto || '%'")
    public Produto search(@Param("encontrarProduto") String encontrarProduto);
}
