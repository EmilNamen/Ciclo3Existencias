
package co.edu.uniandes.csw.existencias.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;
import co.edu.uniandes.csw.existencias.persistence.entity.ExistenciasEntity;

public abstract class _ExistenciasConverter {


	public static ExistenciasDTO entity2PersistenceDTO(ExistenciasEntity entity){
		if (entity != null) {
			ExistenciasDTO dto = new ExistenciasDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setCantidad(entity.getCantidad());
				dto.setProductoId(entity.getProductoId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static ExistenciasEntity persistenceDTO2Entity(ExistenciasDTO dto){
		if(dto!=null){
			ExistenciasEntity entity=new ExistenciasEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setCantidad(dto.getCantidad());
			entity.setProductoId(dto.getProductoId());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<ExistenciasDTO> entity2PersistenceDTOList(List<ExistenciasEntity> entities){
		List<ExistenciasDTO> dtos=new ArrayList<ExistenciasDTO>();
		for(ExistenciasEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<ExistenciasEntity> persistenceDTO2EntityList(List<ExistenciasDTO> dtos){
		List<ExistenciasEntity> entities=new ArrayList<ExistenciasEntity>();
		for(ExistenciasDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}