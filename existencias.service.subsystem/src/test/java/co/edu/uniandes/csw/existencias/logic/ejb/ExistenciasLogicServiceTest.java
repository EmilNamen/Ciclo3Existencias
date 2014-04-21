
package co.edu.uniandes.csw.existencias.logic.ejb;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.existencias.logic.dto.ExistenciasDTO;
import co.edu.uniandes.csw.existencias.logic.api.IExistenciasLogicService;
import co.edu.uniandes.csw.existencias.persistence.ExistenciasPersistence;
import co.edu.uniandes.csw.existencias.persistence.api.IExistenciasPersistence;
import co.edu.uniandes.csw.existencias.persistence.entity.ExistenciasEntity;

@RunWith(Arquillian.class)
public class ExistenciasLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ExistenciasLogicService.class.getPackage())
				.addPackage(ExistenciasPersistence.class.getPackage())
				.addPackage(ExistenciasEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IExistenciasLogicService existenciasLogicService;
	
	@Inject
	private IExistenciasPersistence existenciasPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<ExistenciasDTO> dtos=existenciasPersistence.getExistenciass();
		for(ExistenciasDTO dto:dtos){
			existenciasPersistence.deleteExistencias(dto.getId());
		}
	}

	private List<ExistenciasDTO> data=new ArrayList<ExistenciasDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ExistenciasDTO pdto=new ExistenciasDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setCantidad(generateRandom(Integer.class));
			pdto.setProductoId(generateRandom(Long.class));
			pdto=existenciasPersistence.createExistencias(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createExistenciasTest(){
		ExistenciasDTO ldto=new ExistenciasDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setCantidad(generateRandom(Integer.class));
		ldto.setProductoId(generateRandom(Long.class));
		
		
		ExistenciasDTO result=existenciasLogicService.createExistencias(ldto);
		
		Assert.assertNotNull(result);
		
		ExistenciasDTO pdto=existenciasPersistence.getExistencias(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getCantidad(), pdto.getCantidad());	
		Assert.assertEquals(ldto.getProductoId(), pdto.getProductoId());	
	}
	
	@Test
	public void getExistenciassTest(){
		List<ExistenciasDTO> list=existenciasLogicService.getExistenciass();
		Assert.assertEquals(list.size(), data.size());
        for(ExistenciasDTO ldto:list){
        	boolean found=false;
            for(ExistenciasDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getExistenciasTest(){
		ExistenciasDTO pdto=data.get(0);
		ExistenciasDTO ldto=existenciasLogicService.getExistencias(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getCantidad(), ldto.getCantidad());
		Assert.assertEquals(pdto.getProductoId(), ldto.getProductoId());
        
	}
	
	@Test
	public void deleteExistenciasTest(){
		ExistenciasDTO pdto=data.get(0);
		existenciasLogicService.deleteExistencias(pdto.getId());
        ExistenciasDTO deleted=existenciasPersistence.getExistencias(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateExistenciasTest(){
		ExistenciasDTO pdto=data.get(0);
		
		ExistenciasDTO ldto=new ExistenciasDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setCantidad(generateRandom(Integer.class));
		ldto.setProductoId(generateRandom(Long.class));
		
		
		existenciasLogicService.updateExistencias(ldto);
		
		
		ExistenciasDTO resp=existenciasPersistence.getExistencias(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getCantidad(), resp.getCantidad());	
		Assert.assertEquals(ldto.getProductoId(), resp.getProductoId());	
	}
	
	public <T> T generateRandom(Class<T> objectClass){
		Random r=new Random();
		if(objectClass.isInstance(String.class)){
			String s="";
			for(int i=0;i<10;i++){
				char c=(char)(r.nextInt()/('Z'-'A')+'A');
				s=s+c;
			}
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Integer.class)){
			Integer s=r.nextInt();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(Long.class)){
			Long s=r.nextLong();
			return objectClass.cast(s);
		}else if(objectClass.isInstance(java.util.Date.class)){
			java.util.Calendar c=java.util.Calendar.getInstance();
			c.set(java.util.Calendar.MONTH, r.nextInt()/12);
			c.set(java.util.Calendar.DAY_OF_MONTH,r.nextInt()/30);
			c.setLenient(false);
			return objectClass.cast(c.getTime());
		} 
		return null;
	}
	
}