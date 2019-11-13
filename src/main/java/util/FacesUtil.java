package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	
	public static void addInfoMenssage(String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, null,msg );
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public static void addErrorMenssage(String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, null,msg );
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	public static void addWarnMenssage(String msg) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, null,msg );
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
}
