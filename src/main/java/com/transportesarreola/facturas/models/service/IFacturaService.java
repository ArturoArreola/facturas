package com.transportesarreola.facturas.models.service;

import com.transportesarreola.facturas.models.entity.Factura;
import java.util.List;

public interface IFacturaService {

    //public List<Factura> findAll();
    
    public List<Factura> listarMesCorriente();
    
    public double totalGeneralMesCorriente();
    
    public void save(Factura factura);
    
    public Factura findOne(Long id);
    
    public void delete(Long id);
}
