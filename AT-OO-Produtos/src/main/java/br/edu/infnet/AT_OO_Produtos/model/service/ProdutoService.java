package br.edu.infnet.AT_OO_Produtos.model.service;

import br.edu.infnet.AT_OO_Produtos.model.negocio.ArquivoCSV;
import br.edu.infnet.AT_OO_Produtos.model.negocio.Cotacao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.infnet.AT_OO_Produtos.model.negocio.Produto;
import br.edu.infnet.AT_OO_Produtos.model.repository.IProdutoRepository;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class ProdutoService {
	
    @Autowired
    private final IProdutoRepository produtoRepository;

    public ProdutoService(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    public Produto registrarProdutoRepository(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto utilizarEncontrarProduto(String encontrarProduto) {
        return produtoRepository.search(encontrarProduto);
    }

    public List<Produto> encontrarLista(){
        return produtoRepository.listaCotacoes();
    }

    public Integer utilizarEncontrarProdutoId(String encontrarProduto) {
        return produtoRepository.search(encontrarProduto).getId();
    }
    
    public void alterarProduto(Integer id, Produto produto){  
        produto.setId(id);
        produtoRepository.save(produto);
    }

    public void excluirProdutoRepository(Integer id) {
        produtoRepository.deleteById(id);
    }		
    
    public List<ArquivoCSV> exportarArquivo(List<Produto> cotacaoProduto) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        List<ArquivoCSV> cotacaoArquivo = new ArrayList<>();
        
        for (Produto produto : cotacaoProduto) {
            List<Cotacao> cotacao = produto.getCotacao();
            for(Cotacao c: cotacao){
                cotacaoArquivo.add(new ArquivoCSV(produto.getId(), c.getId(), produto.getNomeProduto(), c.getPreco()));
            }        
        }
     
        try (Writer writer = Files.newBufferedWriter(Paths.get("lista de cotações.csv"))) {
            StatefulBeanToCsv<ArquivoCSV> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            
            beanToCsv.write(cotacaoArquivo);
            writer.flush();
        }
        
        return cotacaoArquivo;
    }
    
//    public static void downloadCsv(PrintWriter writer, List<Produto> cotacao) {
//        List<ArquivoCSV> cotacaoArquivo = new ArrayList<>();
//        writer.write("ID, Nome do produto, Número de série, Tipo do Produto \n");
//        for (Produto produto : cotacao) {
//            writer.write(produto.getId() + "," + produto.getNomeProduto() + "," + produto.getNumeroDeSerie() + "," + produto.getTipoDeProduto() + "\n");
//        }
//    }
}
