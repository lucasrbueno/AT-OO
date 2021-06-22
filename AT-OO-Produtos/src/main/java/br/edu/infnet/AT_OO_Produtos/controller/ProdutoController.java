package br.edu.infnet.AT_OO_Produtos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.edu.infnet.AT_OO_Produtos.model.negocio.Produto;
import br.edu.infnet.AT_OO_Produtos.client.AmazonClient;
import br.edu.infnet.AT_OO_Produtos.model.negocio.ArquivoCSV;
import br.edu.infnet.AT_OO_Produtos.model.service.ProdutoService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProdutoController {

    @Autowired
    private final ProdutoService produtoService;

    private final AmazonClient amazonClient;

    @Autowired
    public ProdutoController(ProdutoService produtoService, AmazonClient amazonClient) {
        this.produtoService = produtoService;
        this.amazonClient = amazonClient;
    }
    
    @PostMapping("/registrarproduto")
    public Produto registrarProduto(Produto produto, @RequestPart(value = "file") MultipartFile file) {
        amazonClient.uploadFile(file);
        return produtoService.registrarProdutoRepository(produto);
    }

    @PostMapping("/listacotacoes")
    public List<Produto> listagemCotacoes(){	
        return produtoService.encontrarLista();
    }
    
    @PutMapping(value = "/alterarproduto/{id}")
    public void alterarProduto(@PathVariable Integer id, @RequestBody Produto produto) {  
        produtoService.alterarProduto(id, produto);
    }

    @DeleteMapping(value = "/deletarproduto/{id}")
    public void excluirProduto(@PathVariable Integer id) {
        produtoService.excluirProdutoRepository(id);	
    }

    @DeleteMapping("/deletefile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromBucketS3(fileUrl);
    }
    
    @PostMapping("/exportarquivocsv")
    public List<ArquivoCSV> exportarArquivoCSV(){
        List<Produto> arquivoLista = produtoService.encontrarLista();

        try {
            return produtoService.exportarArquivo(arquivoLista);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
        }
        
        return null;
    }	
    
//    @GetMapping("/exportarquivocsv")
//    public void downloadCsv(HttpServletResponse response, Produto produto) throws IOException {
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; file=lista.csv");
//        produtoService.downloadCsv(response.getWriter(), produto.getCotacao());
//    }
}
