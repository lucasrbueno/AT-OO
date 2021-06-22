package br.edu.infnet.AT_OO_Produtos.model.negocio;

import java.util.List;

public class ArquivoCSV {
	
    private Integer idProduto;
    private Integer idCotacao;

    private String nomeProduto;
    private float preco;

    public ArquivoCSV(Integer idProduto, Integer idCotacao, String nomeProduto, float preco) {
        this.idProduto = idProduto;
        this.idCotacao = idCotacao;
        this.nomeProduto = nomeProduto;
        this.preco = preco;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getIdCotacao() {
        return idCotacao;
    }

    public void setIdCotacao(Integer idCotacao) {
        this.idCotacao = idCotacao;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ArquivoCSV{" + "idProduto=" + idProduto + ", idCotacao=" + idCotacao + ", nomeProduto=" + nomeProduto + ", preco=" + preco + '}';
    }
}