package co.edu.uniandes.csw.bodega.master.persistence.api;

import co.edu.uniandes.csw.bodega.master.persistence.entity.BodegaExistenciasEntity;
import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;
import co.edu.uniandes.csw.bodega.master.logic.dto.BodegaMasterDTO;
import java.util.List;

public interface _IBodegaMasterPersistence {

    public BodegaExistenciasEntity createBodegaExistencias(BodegaExistenciasEntity entity);

    public void deleteBodegaExistencias(Long bodegaId, Long existenciasId);
    
    public List<ExistenciasDTO> getExistenciasListForBodega(Long bodegaId);
    
    public BodegaMasterDTO getBodega(Long bodegaId);

}
