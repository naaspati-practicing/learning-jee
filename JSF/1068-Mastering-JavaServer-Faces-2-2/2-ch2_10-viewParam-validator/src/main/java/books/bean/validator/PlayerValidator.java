package books.bean.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("playerValidator")
public class PlayerValidator implements Validator {
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(Math.random() < 0.5)
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, " Player name/surname validation failed.", "Details about failure!"));
	}
}
