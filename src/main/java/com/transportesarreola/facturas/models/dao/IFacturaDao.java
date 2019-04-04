package com.transportesarreola.facturas.models.dao;

import com.transportesarreola.facturas.models.entity.Factura;
import java.util.List;
//import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao{
    
    public List<Factura> facturasPorMes();
    
    public List<Factura> facturasViajePorMes();
    
    public double totalGeneralMesCorriente();
    
    public double totalViajesMesCorriente();
    
    public void save(Factura factura);
    
    public Factura findOne(Long id);
    
    public void delete(Long id);
}
