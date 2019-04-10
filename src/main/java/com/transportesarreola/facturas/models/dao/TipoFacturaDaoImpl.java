package com.transportesarreola.facturas.models.dao;

import com.transportesarreola.facturas.models.entity.TipoFactura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TipoFacturaDaoImpl implements ITipoFacturaDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<TipoFactura> tiposDeFactura() {
        return em.createQuery("select tf from TipoFactura tf").getResultList();
    }
    
}
