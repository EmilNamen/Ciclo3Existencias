package co.edu.uniandes.csw.bodega.master.persistence.entity;

import co.edu.uniandes.csw.existencias.persistence.entity.ExistenciasEntity;
import co.edu.uniandes.csw.bodega.persistence.entity.BodegaEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(BodegaExistenciasEntityId.class)
@NamedQueries({
    @NamedQuery(name = "BodegaExistenciasEntity.getExistenciasListForBodega", query = "SELECT  u FROM BodegaExistenciasEntity u WHERE u.bodegaId=:bodegaId"),
    @NamedQuery(name = "BodegaExistenciasEntity.deleteBodegaExistencias", query = "DELETE FROM BodegaExistenciasEntity u WHERE u.existenciasId=:existenciasId and  u.bodegaId=:bodegaId")
})
public class BodegaExistenciasEntity implements Serializable {

    @Id
    @Column(name = "bodegaId")
    private Long bodegaId;
    @Id
    @Column(name = "existenciasId")
    private Long existenciasId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "existenciasId", referencedColumnName = "id")
    @JoinFetch
    private ExistenciasEntity existenciasEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "bodegaId", referencedColumnName = "id")
    @JoinFetch
    private BodegaEntity bodegaEntity;

    public BodegaExistenciasEntity() {
    }

    public BodegaExistenciasEntity(Long bodegaId, Long existenciasId) {
        this.bodegaId = bodegaId;
        this.existenciasId = existenciasId;
    }

    public Long getBodegaId() {
        return bodegaId;
    }

    public void setBodegaId(Long bodegaId) {
        this.bodegaId = bodegaId;
    }

    public Long getExistenciasId() {
        return existenciasId;
    }

    public void setExistenciasId(Long existenciasId) {
        this.existenciasId = existenciasId;
    }

    public ExistenciasEntity getExistenciasEntity() {
        return existenciasEntity;
    }

    public void setExistenciasEntity(ExistenciasEntity existenciasEntity) {
        this.existenciasEntity = existenciasEntity;
    }

    public BodegaEntity getBodegaEntity() {
        return bodegaEntity;
    }

    public void setBodegaEntity(BodegaEntity bodegaEntity) {
        this.bodegaEntity = bodegaEntity;
    }

}
