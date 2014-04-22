
package co.edu.uniandes.csw.producto.logic.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless; 
import javax.inject.Inject;

import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.producto.logic.api.IProductoLogicService;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import java.util.List;

@Default
@Stateless
@LocalBean
public class ProductoLogicService extends _ProductoLogicService implements IProductoLogicService {

    public List<ProductoDTO> searchProducto(ProductoDTO producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}