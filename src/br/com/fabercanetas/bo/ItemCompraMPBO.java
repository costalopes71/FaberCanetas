package br.com.fabercanetas.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fabercanetas.dao.ItemCompraMPDAO;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.exceptions.UpdateException;
import br.com.fabercanetas.to.ItemCompraMP;

	public class ItemCompraMPBO {

		public List<ItemCompraMP> listarItensOrdemCompra(int codigoOrdemCompra, Connection conn) throws RetrieveException {
			try {
				return new ItemCompraMPDAO().listarItensOrdemCompra(codigoOrdemCompra, conn);
			} catch (SQLException e) {
				throw new RetrieveException();
			}
		}
	
		public void atualizarEstoqueMP(ItemCompraMP itemCompra) throws UpdateException {
			try {
				new ItemCompraMPDAO().atualizarEstoqueMP(itemCompra);
			} catch (SQLException e) {
				throw new UpdateException();
			}
		}

	}
