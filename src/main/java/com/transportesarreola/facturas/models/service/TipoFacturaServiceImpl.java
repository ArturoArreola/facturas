package com.transportesarreola.facturas.models.service;

import com.transportesarreola.facturas.models.dao.ITipoFacturaDao;
import com.transportesarreola.facturas.models.entity.TipoFactura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoFacturaServiceImpl implements ITipoFacturaService{

    @Autowired
    private ITipoFacturaDao tipoFacturaDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<TipoFactura> tiposDeFactura() {
        return tipoFacturaDao.tiposDeFactura();
    }
}