
package co.edu.uniandes.csw.existencias.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.existencias.logic.api.IExistenciasLogicService;

@Default
@Stateless
@LocalBean
public class ExistenciasLogicService extends _ExistenciasLogicService implements IExistenciasLogicService {

}