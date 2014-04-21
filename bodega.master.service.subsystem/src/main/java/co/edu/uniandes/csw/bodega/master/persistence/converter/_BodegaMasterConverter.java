package co.edu.uniandes.csw.bodega.master.persistence.converter;
import co.edu.uniandes.csw.bodega.master.persistence.entity.BodegaExistenciasEntity;
import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;
import co.edu.uniandes.csw.existencias.persistence.converter.ExistenciasConverter;
import co.edu.uniandes.csw.bodega.logic.dto.BodegaDTO;
import co.edu.uniandes.csw.bodega.master.logic.dto.BodegaMasterDTO;
import co.edu.uniandes.csw.bodega.persistence.converter.BodegaConverter;
import co.edu.uniandes.csw.bodega.persistence.entity.BodegaEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _BodegaMasterConverter {

    public static BodegaMasterDTO entity2PersistenceDTO(BodegaEntity bodegaEntity 
    ,List<BodegaExistenciasEntity> bodegaExistenciasEntity 
    ) {
        BodegaDTO bodegaDTO = BodegaConverter.entity2PersistenceDTO(bodegaEntity);
        ArrayList<ExistenciasDTO> existenciasEntities = new ArrayList<ExistenciasDTO>(bodegaExistenciasEntity.size());
        for (BodegaExistenciasEntity bodegaExistencias : bodegaExistenciasEntity) {
            existenciasEntities.add(ExistenciasConverter.entity2PersistenceDTO(bodegaExistencias.getExistenciasEntity()));
        }
        BodegaMasterDTO respDTO  = new BodegaMasterDTO();
        respDTO.setBodegaEntity(bodegaDTO);
        respDTO.setListExistencias(existenciasEntities);
        return respDTO;
    }

}