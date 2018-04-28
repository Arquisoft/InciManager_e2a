package manager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manager.entities.Etiqueta;
import manager.repository.EtiquetasRepository;

@Service
public class EtiquetaService {
	
	@Autowired 
	EtiquetasRepository etiquetasRepository;
	
	public List<Etiqueta> obtenerEtiquetas(Long id){
		return etiquetasRepository.findByIncidencia(id);
	}

}
