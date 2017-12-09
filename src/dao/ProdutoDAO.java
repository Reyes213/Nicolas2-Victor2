package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import to.ProdutoTO;

public class ProdutoDAO {
	public void incluir(ProdutoTO to) {
		String sqlInsert = "INSERT INTO PRODUTOS (cod_produto, descricao, tipo, preco) VALUES (?, ?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, to.getCodProduto());
			stm.setString(2, to.getDescricao());
			stm.setString(3, to.getTipo());
			stm.setDouble(4, to.getPreco());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); 
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					to.setId(rs.getInt(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(ProdutoTO to) {
		String sqlUpdate = "UPDATE produtos SET cod_produto = ?, descricao = ?, tipo = ? , preco = ? WHERE id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setInt(1, to.getCodProduto());
			stm.setString(2, to.getDescricao());
			stm.setString(3, to.getTipo());
			stm.setDouble(4, to.getPreco());
			stm.setInt(5, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(ProdutoTO to) {
		String sqlDelete = "DELETE FROM produtos WHERE id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProdutoTO carregar(int id) {
		ProdutoTO to = new ProdutoTO();
		String sqlSelect = "SELECT cod_produto, descricao, tipo, preco FROM produtos WHERE produtos.id = ?";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setCodProduto(rs.getInt("cod_produto"));
					to.setDescricao(rs.getString("descricao"));
					to.setTipo(rs.getString("tipo"));
					to.setPreco(rs.getDouble("preco"));
					
				} else {
					to.setId(-1);
					to.setCodProduto(0);
					to.setDescricao(null);
					to.setTipo(null);
					to.setPreco(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return to;
	}

	public ArrayList<ProdutoTO> listarProdutos() {
		ProdutoTO to;
		ArrayList<ProdutoTO> lista = new ArrayList<>();
		String sqlSelect = "";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					to = new ProdutoTO();
					// set atributos
					lista.add(to);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return lista;
	}

	public ArrayList<ProdutoTO> listarProdutos(String chave) {
		ProdutoTO to;
		ArrayList<ProdutoTO> lista = new ArrayList<>();
		String sqlSelect = "";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					to = new ProdutoTO();
					// set atributos
					lista.add(to);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.println(e1.getStackTrace());
		}
		return lista;
	}
}
