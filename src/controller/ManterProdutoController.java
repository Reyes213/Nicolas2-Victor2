package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProdutoService;
import to.ProdutoTO;

@WebServlet("/ManterProduto.do")
public class ManterProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pCodProduto = Integer.parseInt(request.getParameter("codProduto"));
		String pDescricao = request.getParameter("descricao");
		int pPreco = Integer.parseInt(request.getParameter("preco"));
		String pTipo = request.getParameter("tipo");
		
		ProdutoTO produto = new ProdutoTO();
		produto.setCodProduto(pCodProduto);
		produto.setDescricao(pDescricao);
		produto.setPreco(pPreco);
		produto.setTipo(pTipo);
		
		//instanciar o service
		ProdutoService ps = new ProdutoService();
		ps.criar(produto);
		produto = ps.carregar(produto.getId());
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Cliente Cadastrado</title></head><body>");
		out.println( "ID: "+produto.getId()+"<br>");
		out.println( "Codigo do Produto: "+produto.getCodProduto()+"<br>");
		out.println( "Descrição: "+produto.getDescricao()+"<br>");
		out.println( "Preço: "+produto.getPreco()+"<br>");
		out.println( "Tipo: " +produto.getTipo()+"<br>");
		out.println("</body></html>");
	}

}
