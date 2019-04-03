package com.transportesarreola.facturas.controller;

import com.transportesarreola.facturas.models.entity.Factura;
import com.transportesarreola.facturas.models.service.IFacturaService;
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
    
    @RequestMapping(value="/listar", method = RequestMethod.GET)
    public String listar(Model model){
        model.addAttribute("titulo", "Listado de facturas del mes actual");
        model.addAttribute("facturas", facturaService.listarMesCorriente());
        model.addAttribute("totalGeneralFacturas", String.format("%,.2f", facturaService.totalGeneralMesCorriente()));
        model.addAttribute("totalViajesFacturas",String.format("%,.2f", 10500.00));
        model.addAttribute("totalDiferencia", String.format("%,.2f", (facturaService.totalGeneralMesCorriente() - 10500.00)));
        
        return "listar";
    }
    
    @RequestMapping(value="/form", method = RequestMethod.GET)
    public String crear(Map<String,Object> model){
        Factura factura = new Factura();
        model.put("facturas", factura);
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
