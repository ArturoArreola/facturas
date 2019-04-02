package com.transportesarreola.facturas.models.dao;

import com.transportesarreola.facturas.models.entity.Factura;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FacturaDaoImpl implements IFacturaDao{

    @PersistenceContext
    private EntityManager em;
    
    @Transactional(readOnly = true)
    @Override
    public List<Factura> facturasPorMes(){
        LocalDate firstDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        
        String query = "select f from Factura f where f.fecha >= '" + firstDayofCurrentMonth + "' and f.fecha <= '" + lastDayofCurrentMonth + "'";
        System.out.println("Este es el query -> " + query);
        return em.createQuery(query).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public double totalGeneralMesCorriente() {
        LocalDate firstDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        
        String query = "select sum(f.costo) from Factura f where f.fecha >= '" + firstDayofCurrentMonth + "' and f.fecha <= '" + lastDayofCurrentMonth + "'";
        Query q = em.createQuery(query);
        double d = (double)q.getSingleResult();
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(d));
    }

    @Override
    @Transactional
    public void save(Factura factura) {
        em.persist(factura);
    }
}