package br.edu.infnet.AT_OO_Cotacoes.model.negocio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cotacao implements Serializable {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cotacao")
    private Integer id;
    
    private float preco;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    public Cotacao(){

    }

    public Cotacao(float preco, Produto produto) {
        this.preco = preco;
        this.produto = produto;
    }

   public Integer getId() {
        return id;
   }

   public void setId(Integer id) {
        this.id = id;
   }

   public Produto getProduto() {
        return produto;
   }

   public void setProduto(Produto produto) {
        this.produto = produto;
   }

   public float getPreco() {
        return preco;
   }

   public void setPreco(float preco) {
        this.preco = preco;
   }

   @Override
   public String toString() {
        return "Cotacao [preco=" + preco + "]";
   }
}
