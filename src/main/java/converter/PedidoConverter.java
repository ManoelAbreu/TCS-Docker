package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import dao.PedidoDao;
import model.Pedido;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {

	private PedidoDao dao;

	public PedidoConverter() {
		dao = new PedidoDao();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		return dao.pesquisarPorID(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		Pedido pedido = (Pedido) value;

		if (pedido.getId() == null) {
			return null;
		}

		return pedido.getId().toString();
	}
}
