package co.edu.uniandes.csw.bodega.master.persistence;
import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;
import co.edu.uniandes.csw.bodega.master.persistence.entity.BodegaExistenciasEntity;
import co.edu.uniandes.csw.existencias.persistence.converter.ExistenciasConverter;
import co.edu.uniandes.csw.bodega.logic.dto.BodegaDTO;
import co.edu.uniandes.csw.bodega.master.logic.dto.BodegaMasterDTO;
import co.edu.uniandes.csw.bodega.master.persistence.api._IBodegaMasterPersistence;
import co.edu.uniandes.csw.bodega.persistence.api.IBodegaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _BodegaMasterPersistence implements _IBodegaMasterPersistence {

    @PersistenceContext(unitName = "BodegaMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IBodegaPersistence bodegaPersistence;  

    public BodegaMasterDTO getBodega(Long bodegaId) {
        BodegaMasterDTO bodegaMasterDTO = new BodegaMasterDTO();
        BodegaDTO bodega = bodegaPersistence.getBodega(bodegaId);
        bodegaMasterDTO.setBodegaEntity(bodega);
        bodegaMasterDTO.setListExistencias(getExistenciasListForBodega(bodegaId));
        return bodegaMasterDTO;
    }

    public BodegaExistenciasEntity createBodegaExistencias(BodegaExistenciasEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteBodegaExistencias(Long bodegaId, Long existenciasId) {
        Query q = entityManager.createNamedQuery("BodegaExistenciasEntity.deleteBodegaExistencias");
        q.setParameter("bodegaId", bodegaId);
        q.setParameter("existenciasId", existenciasId);
        q.executeUpdate();
    }

    public List<ExistenciasDTO> getExistenciasListForBodega(Long bodegaId) {
        ArrayList<ExistenciasDTO> resp = new ArrayList<ExistenciasDTO>();
        Query q = entityManager.createNamedQuery("BodegaExistenciasEntity.getExistenciasListForBodega");
        q.setParameter("bodegaId", bodegaId);
        List<BodegaExistenciasEntity> qResult =  q.getResultList();
        for (BodegaExistenciasEntity bodegaExistenciasEntity : qResult) { 
            if(bodegaExistenciasEntity.getExistenciasEntity()==null){
                entityManager.refresh(bodegaExistenciasEntity);
            }
            resp.add(ExistenciasConverter.entity2PersistenceDTO(bodegaExistenciasEntity.getExistenciasEntity()));
        }
        return resp;
    }

}
