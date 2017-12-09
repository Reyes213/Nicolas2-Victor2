package model;

import java.io.Serializable;

import dao.ProdutoDAO;
import to.ProdutoTO;

public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int codProduto;
	private String descricao;
	private String tipo;
	private double preco;
	
	public Produto() {
		
	}
	
	public Produto(int id, int codProduto, String descricao, String tipo, double preco) {
		this.id = id;
		this.codProduto = codProduto;
		this.descricao = descricao;
		this.tipo = tipo;
		this.preco = preco;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCodProduto() {
		return codProduto;
	}
	
	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public void criar() {
		ProdutoDAO dao = new ProdutoDAO();
		ProdutoTO to = new ProdutoTO();
		to.setId(id);
		to.setCodProduto(codProduto);
		to.setDescricao(descricao);
		to.setTipo(tipo);
		to.setPreco(preco);
		dao.incluir(to);
	}
	
	public void atualizar() {
		ProdutoDAO dao = new ProdutoDAO();
		ProdutoTO to = new ProdutoTO();
		to.setId(id);
		to.setCodProduto(codProduto);
		to.setDescricao(descricao);
		to.setTipo(tipo);
		to.setPreco(preco);
		dao.atualizar(to);
	}
	
	public void excluir() {
		ProdutoDAO dao = new ProdutoDAO();
		ProdutoTO to = new ProdutoTO();
		to.setId(id);
		dao.excluir(to);
	}
	
	public void carregar() {
		ProdutoDAO dao = new ProdutoDAO();
		ProdutoTO to = dao.carregar(id);
		codProduto = to.getCodProduto();
		descricao = to.getDescricao();
		tipo = to.getTipo();
		preco = to.getPreco();
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", codProduto=" + codProduto + ", descricao=" + descricao + ", tipo=" + tipo
				+ ", preco=" + preco + "]";
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
		if (codProduto != other.codProduto)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
}
