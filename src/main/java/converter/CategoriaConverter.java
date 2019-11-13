package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dao.CategoriaDao;
import model.Categoria;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter{

	private CategoriaDao categoriaDao;
	
	public CategoriaConverter() {
		categoriaDao = new CategoriaDao();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()) {
			return null;
		}
	
		return categoriaDao.pesquisarPorID(new Long(value));
	
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null ) {
			
			return null;
		}
		
		Categoria categoria = (Categoria) value;
		
		if(categoria.getId() == null) {
			return null;
		}
		
		return categoria.getId().toString();
	}

}
