package co.edu.uniandes.csw.existencias.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.existencias.logic.api.IExistenciasLogicService;
import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;


public abstract class _ExistenciasService {

	@Inject
	protected IExistenciasLogicService existenciasLogicService;
	
	@POST
	public ExistenciasDTO createExistencias(ExistenciasDTO existencias){
		return existenciasLogicService.createExistencias(existencias);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteExistencias(@PathParam("id") Long id){
		existenciasLogicService.deleteExistencias(id);
	}
	
	@GET
	public List<ExistenciasDTO> getExistenciass(){
		return existenciasLogicService.getExistenciass();
	}
	
	@GET
	@Path("{id}")
	public ExistenciasDTO getExistencias(@PathParam("id") Long id){
		return existenciasLogicService.getExistencias(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateExistencias(@PathParam("id") Long id, ExistenciasDTO existencias){
		existenciasLogicService.updateExistencias(existencias);
	}
	
}