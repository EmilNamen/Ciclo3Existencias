
package co.edu.uniandes.csw.existencias.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;

public interface _IExistenciasLogicService {

	public ExistenciasDTO createExistencias(ExistenciasDTO detail);
	public List<ExistenciasDTO> getExistenciass();
	public ExistenciasDTO getExistencias(Long id);
	public void deleteExistencias(Long id);
	public void updateExistencias(ExistenciasDTO detail);
	
}