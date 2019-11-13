package converter;

import dao.ItemPedidoDao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.ItemPedido;

@FacesConverter(forClass = ItemPedidoDao.class)
public class ItemPedidoConverter implements Converter {

	private ItemPedidoDao dao;

	public ItemPedidoConverter() {
		dao = new ItemPedidoDao();
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

		ItemPedido itemPedido = (ItemPedido) value;

		if (itemPedido.getId() == null) {
			return null;
		}

		return itemPedido.getId().toString();
	}
}
