
package co.edu.uniandes.csw.producto.logic.mock;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import co.edu.uniandes.csw.producto.logic.api.IProductoLogicService;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import java.util.List;

@Alternative
@Singleton
public class ProductoMockLogicService extends _ProductoMockLogicService implements IProductoLogicService {

    public List<ProductoDTO> searchProducto(ProductoDTO producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}