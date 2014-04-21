package co.edu.uniandes.csw.bodega.master.logic.dto;

import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;
import co.edu.uniandes.csw.bodega.logic.dto.BodegaDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _BodegaMasterDTO {

 
    protected BodegaDTO bodegaEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public BodegaDTO getBodegaEntity() {
        return bodegaEntity;
    }

    public void setBodegaEntity(BodegaDTO bodegaEntity) {
        this.bodegaEntity = bodegaEntity;
    }
    
    public List<ExistenciasDTO> createExistencias;
    public List<ExistenciasDTO> updateExistencias;
    public List<ExistenciasDTO> deleteExistencias;
    public List<ExistenciasDTO> listExistencias;	
	
	
	
    public List<ExistenciasDTO> getCreateExistencias(){ return createExistencias; };
    public void setCreateExistencias(List<ExistenciasDTO> createExistencias){ this.createExistencias=createExistencias; };
    public List<ExistenciasDTO> getUpdateExistencias(){ return updateExistencias; };
    public void setUpdateExistencias(List<ExistenciasDTO> updateExistencias){ this.updateExistencias=updateExistencias; };
    public List<ExistenciasDTO> getDeleteExistencias(){ return deleteExistencias; };
    public void setDeleteExistencias(List<ExistenciasDTO> deleteExistencias){ this.deleteExistencias=deleteExistencias; };
    public List<ExistenciasDTO> getListExistencias(){ return listExistencias; };
    public void setListExistencias(List<ExistenciasDTO> listExistencias){ this.listExistencias=listExistencias; };	
	
	
}

