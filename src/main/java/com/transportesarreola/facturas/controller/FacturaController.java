package com.transportesarreola.facturas.controller;

import com.transportesarreola.facturas.models.entity.Factura;
import com.transportesarreola.facturas.models.service.IFacturaService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "listar";
    }
    
    @RequestMapping(value="/form", method = RequestMethod.GET)
    public String crear(Map<String,Object> model){
        Factura factura = new Factura();
        model.put("facturas", factura);
        model.put("titulo", "Alta de registro");
        return "form";
    }
    
    @RequestMapping(value="/form", method = RequestMethod.POST)
    public String guardar(Factura factura){
        facturaService.save(factura);
        return "redirect:listar";
    }
    
}
