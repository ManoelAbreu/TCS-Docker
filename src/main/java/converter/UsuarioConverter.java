package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dao.UsuarioDao;
import model.Usuario;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	private UsuarioDao usuarioDao;
	
	public UsuarioConverter() {
		usuarioDao = new UsuarioDao();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.isEmpty()) {
			return null;
		}
		
		return usuarioDao.pesquisarPorID(new Long(value));
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		
		Usuario usuario = (Usuario) value;

		if (usuario.getId() == null) {
			return null;
		}
		
		return usuario.getId().toString();
	}

}
