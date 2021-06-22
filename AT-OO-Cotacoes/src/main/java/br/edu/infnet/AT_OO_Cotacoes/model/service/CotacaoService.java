package br.edu.infnet.AT_OO_Cotacoes.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.infnet.AT_OO_Cotacoes.model.negocio.Cotacao;
import br.edu.infnet.AT_OO_Cotacoes.model.negocio.Produto;
import br.edu.infnet.AT_OO_Cotacoes.model.repository.ICotacaoRepository;

@Service
public class CotacaoService {
	
    @Autowired
    private final ICotacaoRepository cotacaoRepository;

    private Produto produto = new Produto();

    public CotacaoService(ICotacaoRepository cotacaoRepository) {
        this.cotacaoRepository = cotacaoRepository;
    }

    public Produto search(String encontrarProduto){
        return produto = cotacaoRepository.search(encontrarProduto);
        
    }

    public Cotacao registrarCotacaoRepository(float preco, Produto produto) {
        return cotacaoRepository.save(new Cotacao(preco, produto));
    }
    
//    public void alterarCotacaoRepository(Integer id, Cotacao cotacao){  
//        cotacao.setId(id);
//        cotacaoRepository.save(id, cotacao, produto);
//    }

    public void excluirCotacaoRepository(Integer id) {
        cotacaoRepository.deleteById(id);
    }	
}
