
package co.edu.uniandes.csw.existencias.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;
import co.edu.uniandes.csw.existencias.logic.api._IExistenciasLogicService;

public abstract class _ExistenciasMockLogicService implements _IExistenciasLogicService {

	private Long id= new Long(1);
	protected List<ExistenciasDTO> data=new ArrayList<ExistenciasDTO>();

	public ExistenciasDTO createExistencias(ExistenciasDTO existencias){
		id++;
		existencias.setId(id);
		return existencias;
    }

	public List<ExistenciasDTO> getExistenciass(){
		return data; 
	}

	public ExistenciasDTO getExistencias(Long id){
		for(ExistenciasDTO data1:data){
			if(data1.getId().equals(id)){
				return data1;
			}
		}
		return null;
	}

	public void deleteExistencias(Long id){
	    ExistenciasDTO delete=null;
		for(ExistenciasDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateExistencias(ExistenciasDTO existencias){
	    ExistenciasDTO delete=null;
		for(ExistenciasDTO data1:data){
			if(data1.getId().equals(id)){
				delete=data1;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(existencias);
		} 
	}	
}