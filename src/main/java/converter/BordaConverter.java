package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dao.BordaDao;
import model.Borda;

@FacesConverter(forClass = Borda.class)
public class BordaConverter implements Converter {

	private BordaDao bordaDao;
	
	public BordaConverter() {
		bordaDao = new BordaDao();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()) {
			return null;
		}
		
		return bordaDao.pesquisarPorID(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value == null) {
			return null;
		}
		Borda borda = (Borda) value;
		
		if(borda.getId() == null) {
			
			return null;
		}
		return borda.getId().toString();
	}
}
