package br.com.fabercanetas.bo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import br.com.fabercanetas.dao.OrdemCompraDAO;
import br.com.fabercanetas.exceptions.RetrieveException;
import br.com.fabercanetas.exceptions.UpdateException;
import br.com.fabercanetas.to.OrdemCompra;

public class OrdemCompraBO {

	public List<OrdemCompra> listarPedidosEmAberto() throws RetrieveException {
		try {
			List<OrdemCompra> listaOC = new OrdemCompraDAO().listarPedidosEmAberto();
			OrdemCompraDAO aux = new OrdemCompraDAO();
			
			for (int i = 0; i < listaOC.size(); i++) {
				if (listaOC.get(i).getStatus().equals("P")) {
					if (ChronoUnit.DAYS.between(listaOC.get(i).getDataSolicitacao(), LocalDateTime.now()) > listaOC.get(i).getPrazoEntrega()) {
						listaOC.get(i).setStatus("A");
						aux.atualizarStatusOrdemCompra(listaOC.get(i));
					}
				}
			}
			return listaOC;
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	public OrdemCompra buscarOrdemCompra(int codigoOrdem) throws RetrieveException {
		try {
			return new OrdemCompraDAO().buscarOrdemCompra(codigoOrdem);
		} catch (SQLException e) {
			throw new RetrieveException();
		}
	}
	
	public void atualizarStatusOrdemCompra(OrdemCompra oc) throws UpdateException {
		try {
			new OrdemCompraDAO().atualizarStatusOrdemCompra(oc);
		} catch (SQLException e) {
			throw new UpdateException();
		}
	}
	
}
