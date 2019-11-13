package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dao.MesaDao;
import model.Mesa;

@FacesConverter(forClass = Mesa.class)
public class MesaConverter implements Converter{

	private MesaDao mesaDao;
	
	public MesaConverter() {
		mesaDao = new MesaDao();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()) {
			return null;
		}
	
		return mesaDao.pesquisarPorID(new Long(value));
	
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null ) {
			
			return null;
		}
		
		Mesa mesa = (Mesa) value;
		
		if(mesa.getId() == null) {
			return null;
		}
		
		return mesa.getId().toString();
	}

}
