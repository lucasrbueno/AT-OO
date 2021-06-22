package br.edu.infnet.AT_OO_Cotacoes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.infnet.AT_OO_Cotacoes.model.negocio.Cotacao;
import br.edu.infnet.AT_OO_Cotacoes.model.negocio.Produto;
import br.edu.infnet.AT_OO_Cotacoes.model.service.CotacaoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CotacaoController {
	
    @Autowired
    private final CotacaoService cotacaoService;

    Produto produto = new Produto();

    @Autowired
    public CotacaoController(CotacaoService cotacaoService) {
        this.cotacaoService = cotacaoService;
    }

    @PostMapping("/registrarcotacao")
    Cotacao registrarCotacao(float preco, String encontrarProduto) {
        produto = cotacaoService.search(encontrarProduto);
        
        try {
            return cotacaoService.registrarCotacaoRepository(preco, produto);
        } catch (Exception e){               
        }
        
        return null;
    }
    
//    @PutMapping(value = "/alterarcotacao/{id}")
//    public void alterarCotacao(@PathVariable Integer id, @RequestBody float preco, Produto produto) {  
//        cotacaoService.alterarCotacaoRepository(id, preco, produto);
//    }

    @DeleteMapping("/deletarcotacao/{id}")
    public void excluirCotacao(@PathVariable Integer id) {
        cotacaoService.excluirCotacaoRepository(id);	
    }
}
