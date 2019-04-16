package com.transportesarreola.facturas.models.service;

import com.transportesarreola.facturas.models.dao.IFacturaDao;
import com.transportesarreola.facturas.models.entity.Factura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaServiceImpl implements IFacturaService{

    @Autowired
    private IFacturaDao facturaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Factura> listarMesCorriente() {
        return facturaDao.facturasPorMes();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Factura> listarViajesMesCorriente() {
        return facturaDao.facturasViajePorMes();
    }

    @Override
    @Transactional(readOnly = true)
    public double totalGeneralMesCorriente() {
        return facturaDao.totalGeneralMesCorriente();
    }
    
    @Override
    @Transactional(readOnly = true)
    public double totalViajesMesCorriente() {
        return facturaDao.totalViajesMesCorriente();
    }
    
    @Override
    @Transactional
    public void save(Factura factura) {
        facturaDao.save(factura);
    }

    @Override
    public Factura findOne(Long id) {
        return facturaDao.findOne(id);
    }

    @Override
    public void delete(Long id) {
        facturaDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Factura> listarFacturasPorTiempo(String fechaInicio, String fechaFin, String tipoFactura) {
        return facturaDao.facturasGeneralesPorTiempo(fechaInicio, fechaFin, tipoFactura);
    }
    
    @Override
    @Transactional(readOnly = true)
    public double totalFacturasPorTiempo(String fechaInicio, String fechaFin, String tipoFactura) {
        return facturaDao.totalFacturasPorTiempo(fechaInicio, fechaFin, tipoFactura);
    }
}