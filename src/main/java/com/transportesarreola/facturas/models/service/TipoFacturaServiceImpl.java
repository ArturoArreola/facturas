package com.transportesarreola.facturas.models.service;

import com.transportesarreola.facturas.models.dao.ITipoFacturaDao;
import com.transportesarreola.facturas.models.entity.TipoFactura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TipoFacturaServiceImpl implements ITipoFacturaService{

    @Autowired
    private ITipoFacturaDao tipoFacturaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<TipoFactura> tiposDeFactura() {
        System.out.println("----------------------------------- LLEGANDO AL SERVICE IMPL -----------------------------------");
        return tipoFacturaDao.tiposDeFactura();
    }
}