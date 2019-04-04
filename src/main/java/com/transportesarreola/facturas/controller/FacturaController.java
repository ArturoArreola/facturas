package com.transportesarreola.facturas.controller;

import com.transportesarreola.facturas.models.entity.Factura;
import com.transportesarreola.facturas.models.entity.TipoFactura;
import com.transportesarreola.facturas.models.service.IFacturaService;
import com.transportesarreola.facturas.models.service.ITipoFacturaService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacturaController {
    
    @Autowired
    private IFacturaService facturaService;
    private ITipoFacturaService tipoFacturaService;
    
    @RequestMapping(value="/listar", method = RequestMethod.GET)
    public String listar(Model model){
        double totalGeneralFacturas = facturaService.totalGeneralMesCorriente();
        double totalViajesFacturas = facturaService.totalViajesMesCorriente();
     
        model.addAttribute("tituloGenerales", "LISTADO DE FACTURAS GENERALES DEL MES ACTUAL");
        model.addAttribute("tituloViajes", "LISTADO DE FACTURAS VIAJES DEL MES ACTUAL");
        model.addAttribute("facturas", facturaService.listarMesCorriente());
        model.addAttribute("facturasViajes", facturaService.listarViajesMesCorriente());
        model.addAttribute("totalGeneralFacturas", String.format("%,.2f", totalGeneralFacturas));
        model.addAttribute("totalViajesFacturas",String.format("%,.2f", totalViajesFacturas));
        model.addAttribute("totalDiferencia", String.format("%,.2f", (totalViajesFacturas - totalGeneralFacturas)));
        
        return "listar";
    }
    
    @RequestMapping(value="/form", method = RequestMethod.GET)
    public String crear(Map<String,Object> model){
        Factura factura = new Factura();
        TipoFactura tipoDeFacturas = new TipoFactura();
        
        model.put("facturas", factura);
        model.put("tipoDeFacturas", tipoDeFacturas);
        model.put("titulo", "Nueva factura");
        return "form";
    }
    
    @RequestMapping(value="/form", method = RequestMethod.POST)
    public String guardar(@Valid Factura factura, BindingResult result){
        if(result.hasErrors()){
            return "form";
        }
        
        facturaService.save(factura);
        return "redirect:listar";
    }
    
    @RequestMapping(value="/form/{id}")
    public String editar(@PathVariable(value="id") Long id, Map<String, Object> model){
        Factura factura = null;
        if(id > 0){
            factura = facturaService.findOne(id);
        } else {
            return "redirect:/listar";
        }
        model.put("facturas", factura);
        model.put("titulo", "Editar información de la factura");
        return "form";
    }
    
    @RequestMapping(value="/eliminar/{id}")
    public String eliminar(@PathVariable(value="id") Long id){
        if(id > 0){
            facturaService.delete(id);
        }
        return "redirect:/listar";
    }
    
    @RequestMapping(value="/buscar")
    public String buscar(Model model){
        model.addAttribute("titulo", "Búsqueda de facturas por fecha");
        
        return "buscar";
    }
    
}
