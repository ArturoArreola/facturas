package com.transportesarreola.facturas.models.service;

import com.transportesarreola.facturas.models.entity.Factura;
import java.util.List;

public interface IFacturaService {

    public List<Factura> findAll();
    
    public void save(Factura factura);
    
}