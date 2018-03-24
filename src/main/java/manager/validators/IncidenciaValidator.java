package manager.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.*;
import org.springframework.validation.Errors;

import manager.entities.IncidenciaMin;

@Component
public class IncidenciaValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return IncidenciaMin.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		IncidenciaMin incidencia = (IncidenciaMin) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "etiqueta", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "campo", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "latitud", "Error.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "longitud", "Error.empty");

		if (incidencia.getNombre().length() < 5 || incidencia.getNombre().length() > 24) {
			errors.rejectValue("nombre", "Error.sendIncidence.nombre.length");
		}

		if (incidencia.getDescripcion().length() < 5 || incidencia.getDescripcion().length() > 200) {
			errors.rejectValue("descripcion", "Error.sendIncidence.descripcion.length");
		}
		if (valorProhibido(incidencia.getNombre())) {
			errors.rejectValue("nombre", "Error.prohibido");
		}
		if (valorProhibido(incidencia.getDescripcion())) {
			errors.rejectValue("descripcion", "Error.prohibido");
		}
		if (valorProhibido(incidencia.getEtiqueta())) {
			errors.rejectValue("etiqueta", "Error.prohibido");
		}
		if (valorProhibido(incidencia.getCampo())) {
			errors.rejectValue("campo", "Error.prohibido");
		}
		if (campoIncorrecto(incidencia.getCampo())) {
			errors.rejectValue("campo", "Error.campo");
		}
	}

	private boolean valorProhibido(String cad) {
		return cad.contains("$") || cad.contains("@");
	}

	private boolean campoIncorrecto(String cad) {
		String[] campo = cad.split(",");
		for (String s : campo) {
			String[] tmp = s.split(":");
			if (!s.contains(":")) {
				return true;
			} else if (tmp.length > 2) {
				return true;
			}
		}
		return false;
	}
}