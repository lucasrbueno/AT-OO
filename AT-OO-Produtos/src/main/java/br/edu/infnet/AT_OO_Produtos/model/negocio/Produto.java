package br.edu.infnet.AT_OO_Produtos.model.negocio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name = "Produto.listaCotacoes", query = "SELECT DISTINCT(p) FROM Produto p JOIN FETCH p.listaCotacao"),
})
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Integer id;

    private String nomeProduto;
    private String numeroDeSerie;
    private String tipoDeProduto;

    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cotacao> listaCotacao;

    public List<Cotacao> getCotacao() {
        return listaCotacao;
    }

    public Produto() {

    }

    public Produto(String nomeProduto, String numeroDeSerie, String tipoDeProduto) {
        this.nomeProduto = nomeProduto;
        this.numeroDeSerie = numeroDeSerie;
        this.tipoDeProduto = tipoDeProduto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public String getTipoDeProduto() {
        return tipoDeProduto;
    }

    public void setTipoDeProduto(String tipoDeProduto) {
        this.tipoDeProduto = tipoDeProduto;
    }

    @Override
    public String toString() {
        return "Produto{" + "nomeProduto=" + nomeProduto + ", numeroDeSerie=" + numeroDeSerie + ", tipoDeProduto=" + tipoDeProduto + '}';
    }
}