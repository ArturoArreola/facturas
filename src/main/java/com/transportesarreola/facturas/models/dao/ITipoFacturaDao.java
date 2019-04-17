package com.transportesarreola.facturas.models.dao;

import com.transportesarreola.facturas.models.entity.TipoFactura;
import java.util.List;

public interface ITipoFacturaDao {
    
    public List <TipoFactura> tiposDeFactura();
    
    public TipoFactura findOne(Long id);
}
