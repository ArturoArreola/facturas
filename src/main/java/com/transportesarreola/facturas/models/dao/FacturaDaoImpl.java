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
    
    @Override
    @Transactional(readOnly = true)
    public List<Factura> facturasPorMes(){
        LocalDate firstDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        
        String query = "select f from Factura f where f.fecha >= '" + firstDayofCurrentMonth + "' and f.fecha <= '" + lastDayofCurrentMonth + "' and f.tipo = 1";
        System.out.println("Este es el query -> " + query);
        return em.createQuery(query).getResultList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Factura> facturasViajePorMes(){
        LocalDate firstDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        
        String query = "select f from Factura f where f.fecha >= '" + firstDayofCurrentMonth + "' and f.fecha <= '" + lastDayofCurrentMonth + "' and f.tipo = 2";
        System.out.println("Este es el query -> " + query);
        return em.createQuery(query).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public double totalGeneralMesCorriente() {
        LocalDate firstDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        
        String query = "select sum(f.costo) from Factura f where f.fecha >= '" + firstDayofCurrentMonth + "' and f.fecha <= '" + lastDayofCurrentMonth + "' and f.tipo = 1";
        Query q = em.createQuery(query);
        double d = (double)q.getSingleResult();
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(d));
    }
    
    @Override
    @Transactional(readOnly = true)
    public double totalViajesMesCorriente() {
        LocalDate firstDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        
        String query = "select sum(f.costo) from Factura f where f.fecha >= '" + firstDayofCurrentMonth + "' and f.fecha <= '" + lastDayofCurrentMonth + "' and f.tipo = 2";
        Query q = em.createQuery(query);
        double d = (double)q.getSingleResult();
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(d));
    }

    @Override
    @Transactional
    public void save(Factura factura) {
        if(factura.getId() != null && factura.getId() > 0){
            em.merge(factura);
        } else {
            em.persist(factura);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findOne(Long id) {
        return em.find(Factura.class, id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Factura factura = findOne(id);
        em.remove(factura);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factura> facturasGeneralesPorTiempo(String fechaInicio, String fechaFin, String tipoFactura) {
        String query = "select f from Factura f where f.fecha >= '" + fechaInicio + "' and f.fecha <= '" + fechaFin + "' and f.tipo = " +tipoFactura+"";
        System.out.println("Este es el query -> " + query);
        //System.out.println("resultados1 -> " + em.createQuery(query).getResultList());
        return em.createQuery(query).getResultList();
    }
    
    @Override
    @Transactional(readOnly = true)
    public double totalFacturasPorTiempo(String fechaInicio, String fechaFin, String tipoFactura) {
        double d ;
        String query = "select sum(f.costo) from Factura f where f.fecha >= '" + fechaInicio + "' and f.fecha <= '" + fechaFin + "' and f.tipo = " + tipoFactura +"";
        Query q = em.createQuery(query);
        
        System.out.println("\t\testo es q " + q);
        
        if(q.getSingleResult()!=null){
            System.out.println("NO NULO");
             d = (double)q.getSingleResult();
        } else {
            System.out.println("ES NULO");
            d= 0.0;
        }
        
        
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(d));
    }
}