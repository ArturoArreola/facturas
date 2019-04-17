package com.transportesarreola.facturas.models.dao;

import com.transportesarreola.facturas.models.entity.TipoFactura;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TipoFacturaDaoImpl implements ITipoFacturaDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<TipoFactura> tiposDeFactura() {
        return em.createQuery("select tf from TipoFactura tf").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoFactura findOne(Long id) {
        return em.find(TipoFactura.class, id);
    }
}
