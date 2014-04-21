
package co.edu.uniandes.csw.existencias.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;
import co.edu.uniandes.csw.existencias.persistence.api._IExistenciasPersistence;
import co.edu.uniandes.csw.existencias.persistence.converter.ExistenciasConverter;
import co.edu.uniandes.csw.existencias.persistence.entity.ExistenciasEntity;

public abstract class _ExistenciasPersistence implements _IExistenciasPersistence {

	@PersistenceContext(unitName="ExistenciasPU")
	protected EntityManager entityManager;
	
	public ExistenciasDTO createExistencias(ExistenciasDTO existencias) {
		ExistenciasEntity entity=ExistenciasConverter.persistenceDTO2Entity(existencias);
		entityManager.persist(entity);
		return ExistenciasConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<ExistenciasDTO> getExistenciass() {
		Query q = entityManager.createQuery("select u from ExistenciasEntity u");
		return ExistenciasConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public ExistenciasDTO getExistencias(Long id) {
		return ExistenciasConverter.entity2PersistenceDTO(entityManager.find(ExistenciasEntity.class, id));
	}

	public void deleteExistencias(Long id) {
		ExistenciasEntity entity=entityManager.find(ExistenciasEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateExistencias(ExistenciasDTO detail) {
		ExistenciasEntity entity=entityManager.merge(ExistenciasConverter.persistenceDTO2Entity(detail));
		ExistenciasConverter.entity2PersistenceDTO(entity);
	}

}