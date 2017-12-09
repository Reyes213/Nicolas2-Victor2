package service;

import to.ProdutoTO;
import dao.ProdutoDAO;


public class ProdutoService {
	ProdutoDAO dao;
	
	public ProdutoService() {
		dao = new ProdutoDAO();
	}
	
	public void criar(ProdutoTO to) {
		dao.incluir(to);
	}
	
	public void atualizar(ProdutoTO to){
		dao.atualizar(to);
	}
	
	public void excluir(ProdutoTO to){
		dao.excluir(to);
	}
	
	public ProdutoTO carregar(int id){
		ProdutoTO to = dao.carregar(id);
		return to;
	}
}
