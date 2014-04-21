
package co.edu.uniandes.csw.existencias.persistence;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.existencias.persistence.api.IExistenciasPersistence;
import javax.ejb.LocalBean;

@Default
@Stateless 
@LocalBean
public class ExistenciasPersistence extends _ExistenciasPersistence  implements IExistenciasPersistence {

}