package co.edu.uniandes.csw.bodega.master.logic.ejb;

import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;
import co.edu.uniandes.csw.existencias.persistence.api.IExistenciasPersistence;
import co.edu.uniandes.csw.bodega.logic.dto.BodegaDTO;
import co.edu.uniandes.csw.bodega.master.logic.api._IBodegaMasterLogicService;
import co.edu.uniandes.csw.bodega.master.logic.dto.BodegaMasterDTO;
import co.edu.uniandes.csw.bodega.master.persistence.api.IBodegaMasterPersistence;
import co.edu.uniandes.csw.bodega.master.persistence.entity.BodegaExistenciasEntity;
import co.edu.uniandes.csw.bodega.persistence.api.IBodegaPersistence;
import javax.inject.Inject;

public abstract class _BodegaMasterLogicService implements _IBodegaMasterLogicService {

    @Inject
    protected IBodegaPersistence bodegaPersistance;
    @Inject
    protected IBodegaMasterPersistence bodegaMasterPersistance;
    @Inject
    protected IExistenciasPersistence existenciasPersistance;

    public BodegaMasterDTO createMasterBodega(BodegaMasterDTO bodega) {
        BodegaDTO persistedBodegaDTO = bodegaPersistance.createBodega(bodega.getBodegaEntity());
        if (bodega.getCreateExistencias() != null) {
            for (ExistenciasDTO existenciasDTO : bodega.getCreateExistencias()) {
                ExistenciasDTO persistedExistenciasDTO = existenciasPersistance.createExistencias(existenciasDTO);
                BodegaExistenciasEntity bodegaExistenciasEntity = new BodegaExistenciasEntity(persistedBodegaDTO.getId(), persistedExistenciasDTO.getId());
                bodegaMasterPersistance.createBodegaExistencias(bodegaExistenciasEntity);
            }
        }
        return bodega;
    }

    public BodegaMasterDTO getMasterBodega(Long id) {
        return bodegaMasterPersistance.getBodega(id);
    }

    public void deleteMasterBodega(Long id) {
        bodegaPersistance.deleteBodega(id);
    }

    public void updateMasterBodega(BodegaMasterDTO bodega) {
        bodegaPersistance.updateBodega(bodega.getBodegaEntity());

        //---- FOR RELATIONSHIP
        // persist new existencias
        if (bodega.getCreateExistencias() != null) {
            for (ExistenciasDTO existenciasDTO : bodega.getCreateExistencias()) {
                ExistenciasDTO persistedExistenciasDTO = existenciasPersistance.createExistencias(existenciasDTO);
                BodegaExistenciasEntity bodegaExistenciasEntity = new BodegaExistenciasEntity(bodega.getBodegaEntity().getId(), persistedExistenciasDTO.getId());
                bodegaMasterPersistance.createBodegaExistencias(bodegaExistenciasEntity);
            }
        }
        // update existencias
        if (bodega.getUpdateExistencias() != null) {
            for (ExistenciasDTO existenciasDTO : bodega.getUpdateExistencias()) {
                existenciasPersistance.updateExistencias(existenciasDTO);
            }
        }
        // delete existencias
        if (bodega.getDeleteExistencias() != null) {
            for (ExistenciasDTO existenciasDTO : bodega.getDeleteExistencias()) {
                bodegaMasterPersistance.deleteBodegaExistencias(bodega.getBodegaEntity().getId(), existenciasDTO.getId());
                existenciasPersistance.deleteExistencias(existenciasDTO.getId());
            }
        }
    }
}
