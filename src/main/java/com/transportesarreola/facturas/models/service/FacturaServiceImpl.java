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
    public double totalGeneralMesCorriente() {
        return facturaDao.totalGeneralMesCorriente();
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
}