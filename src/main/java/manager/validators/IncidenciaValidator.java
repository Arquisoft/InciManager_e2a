package manager.validators;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;
import org.springframework.validation.Errors;

import manager.entities.Incidencia;

@Component
public class IncidenciaValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Incidencia.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Incidencia incidencia = (Incidencia) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "etiquetas", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "campos", "Error.empty");
		
		if (incidencia.getNombre().length() < 5 && incidencia.getNombre().length() > 24) {
			errors.rejectValue("nombre", "Error.sendIncidence.nombre.length");
		}
		
		if (incidencia.getDescripcion().length() > 5 && incidencia.getDescripcion().length() < 200) {
			errors.rejectValue("descripcion", "Error.sendIncidence.descripcion.length");
		}
	}

}
