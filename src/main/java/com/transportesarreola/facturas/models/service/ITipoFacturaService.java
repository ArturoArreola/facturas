package com.transportesarreola.facturas.models.service;

import com.transportesarreola.facturas.models.entity.TipoFactura;
import java.util.List;

public interface ITipoFacturaService {

public List <TipoFactura> tiposDeFactura();

public TipoFactura findOne(Long id);
    
}
