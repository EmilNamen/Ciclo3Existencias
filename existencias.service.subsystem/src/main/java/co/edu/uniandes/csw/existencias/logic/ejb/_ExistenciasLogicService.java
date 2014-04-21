
package co.edu.uniandes.csw.existencias.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;
import co.edu.uniandes.csw.existencias.logic.api._IExistenciasLogicService;
import co.edu.uniandes.csw.existencias.persistence.api.IExistenciasPersistence;

public abstract class _ExistenciasLogicService implements _IExistenciasLogicService {

	@Inject
	protected IExistenciasPersistence persistance;

	public ExistenciasDTO createExistencias(ExistenciasDTO existencias){
		return persistance.createExistencias( existencias); 
    }

	public List<ExistenciasDTO> getExistenciass(){
		return persistance.getExistenciass(); 
	}

	public ExistenciasDTO getExistencias(Long id){
		return persistance.getExistencias(id); 
	}

	public void deleteExistencias(Long id){
	    persistance.deleteExistencias(id); 
	}

	public void updateExistencias(ExistenciasDTO existencias){
	    persistance.updateExistencias(existencias); 
	}	
}