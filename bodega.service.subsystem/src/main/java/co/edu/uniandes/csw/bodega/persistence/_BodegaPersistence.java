
package co.edu.uniandes.csw.bodega.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.bodega.logic.dto.BodegaDTO;
import co.edu.uniandes.csw.bodega.persistence.api._IBodegaPersistence;
import co.edu.uniandes.csw.bodega.persistence.converter.BodegaConverter;
import co.edu.uniandes.csw.bodega.persistence.entity.BodegaEntity;

public abstract class _BodegaPersistence implements _IBodegaPersistence {

	@PersistenceContext(unitName="BodegaPU")
	protected EntityManager entityManager;
	
	public BodegaDTO createBodega(BodegaDTO bodega) {
		BodegaEntity entity=BodegaConverter.persistenceDTO2Entity(bodega);
		entityManager.persist(entity);
		return BodegaConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<BodegaDTO> getBodegas() {
		Query q = entityManager.createQuery("select u from BodegaEntity u");
		return BodegaConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public BodegaDTO getBodega(Long id) {
		return BodegaConverter.entity2PersistenceDTO(entityManager.find(BodegaEntity.class, id));
	}

	public void deleteBodega(Long id) {
		BodegaEntity entity=entityManager.find(BodegaEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateBodega(BodegaDTO detail) {
		BodegaEntity entity=entityManager.merge(BodegaConverter.persistenceDTO2Entity(detail));
		BodegaConverter.entity2PersistenceDTO(entity);
	}
        
        public List<BodegaDTO> searchBodega(String descr) {    
            descr = descr.split("\"description\":\"")[1];
            descr = descr.split("\"")[0];
            System.out.println("Param: " + descr);
            Query q = entityManager.createQuery("select u from BodegaEntity u where u.name like '%" + descr + "%'");
           
            List list = q.getResultList();
            if (list.size() != 0) {
                return BodegaConverter.entity2PersistenceDTOList(list);
            }
            return null;
         }

}