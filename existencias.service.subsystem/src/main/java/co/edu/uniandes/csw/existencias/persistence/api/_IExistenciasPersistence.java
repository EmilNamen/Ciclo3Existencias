
package co.edu.uniandes.csw.existencias.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;

public interface _IExistenciasPersistence {

	public ExistenciasDTO createExistencias(ExistenciasDTO detail);
	public List<ExistenciasDTO> getExistenciass();
	public ExistenciasDTO getExistencias(Long id);
	public void deleteExistencias(Long id);
	public void updateExistencias(ExistenciasDTO detail);
	
}