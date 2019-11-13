package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dao.SaboresDao;
import model.Sabores;

@FacesConverter(forClass = Sabores.class)
public class SaboresConverter implements Converter {

	private SaboresDao saboresDao;
	
	public SaboresConverter() {
		saboresDao = new SaboresDao();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if(value == null || value.isEmpty()) {
			return null;
		}
		
		return saboresDao.pesquisarPorID(new Long(value))  ;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value == null) {
			return null;
			
		}
		
		Sabores sabores = (Sabores) value;
	
		if(sabores.getId() == null ) {
			return null;
		}
		
		return sabores.getId().toString();
	
	}

}
