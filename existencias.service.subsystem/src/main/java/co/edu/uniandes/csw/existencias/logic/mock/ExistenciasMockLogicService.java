
package co.edu.uniandes.csw.existencias.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.existencias.logic.api.IExistenciasLogicService;

@Alternative
@Singleton
public class ExistenciasMockLogicService extends _ExistenciasMockLogicService implements IExistenciasLogicService {
	
}