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
    public List<Factura> findAll() {
        return (List<Factura>) facturaDao.findAll();
    }

    @Override
    @Transactional
    public void save(Factura factura) {
        facturaDao.save(factura);
    }
}