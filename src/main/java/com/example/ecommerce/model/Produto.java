package com.example.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
	private Long id;
	
	@Column(name = "tipo_unidade")
	private String tipoUnidade;
	
	private String nome;
	
	private Boolean ativo = Boolean.TRUE;
	
	@Column(columnDefinition = "text", length = 2000)
	private String descricao;
	
	private Double peso;
	
	private Double altura;
	
	private Double largura;
	
	private Double profundidade;
	
	@Column(name = "valor_venda")
	private BigDecimal valorVenda = BigDecimal.ZERO;
	
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque = 0;
	
	@Column(name = "quantidade_alerta_estoque")
	private Integer quantidadeAlertaEstoque = 0;
	
	@Column(name = "link_youtube")
	private String linkYoutube;
	
	@Column(name = "alerta_quantidade_estoque")
	private Boolean alertaQuantidadeEstoque = Boolean.FALSE;
	
	@Column(name = "quantidade_clique")
	private Integer quantidadeClique = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoUnidade() {
		return tipoUnidade;
	}

	public void setTipoUnidade(String tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public Double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Integer getQuantidadeAlertaEstoque() {
		return quantidadeAlertaEstoque;
	}

	public void setQuantidadeAlertaEstoque(Integer quantidadeAlertaEstoque) {
		this.quantidadeAlertaEstoque = quantidadeAlertaEstoque;
	}

	public String getLinkYoutube() {
		return linkYoutube;
	}

	public void setLinkYoutube(String linkYoutube) {
		this.linkYoutube = linkYoutube;
	}

	public Boolean getAlertaQuantidadeEstoque() {
		return alertaQuantidadeEstoque;
	}

	public void setAlertaQuantidadeEstoque(Boolean alertaQuantidadeEstoque) {
		this.alertaQuantidadeEstoque = alertaQuantidadeEstoque;
	}

	public Integer getQuantidadeClique() {
		return quantidadeClique;
	}

	public void setQuantidadeClique(Integer quantidadeClique) {
		this.quantidadeClique = quantidadeClique;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}