package co.edu.uniandes.csw.bodega.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class BodegaExistenciasEntityId implements Serializable{

    private Long bodegaId;
    private Long existenciasId;

    @Override
    public int hashCode() {
        return (int) (bodegaId + existenciasId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof BodegaExistenciasEntityId) {
            BodegaExistenciasEntityId otherId = (BodegaExistenciasEntityId) object;
            return (otherId.bodegaId == this.bodegaId) && (otherId.existenciasId == this.existenciasId);
        }
        return false;
    }

}
