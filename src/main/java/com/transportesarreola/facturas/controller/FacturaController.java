package com.transportesarreola.facturas.controller;

import com.transportesarreola.facturas.models.entity.Factura;
import com.transportesarreola.facturas.models.entity.TipoFactura;
import com.transportesarreola.facturas.models.service.IFacturaService;
import com.transportesarreola.facturas.models.service.ITipoFacturaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FacturaController {
    
    @Autowired
    private IFacturaService facturaService;
    @Autowired
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

        model.put("facturas", factura);
        model.put("tipoDeFactura", tipoFacturaService.tiposDeFactura());
        model.put("titulo", "Nueva factura");
        return "form";
    }
    
    @RequestMapping(value="/form", method = RequestMethod.POST)
    public String guardar(@Valid Factura factura, BindingResult result, @RequestParam("tipo") String tipo){
        System.out.println("factura tipo -> " + tipo + " | " );
        long idTipo = Long.parseLong(tipo);
        TipoFactura tipoFactura = tipoFacturaService.findOne(idTipo);
//        System.out.println("+++++++++++++++++++++++++++++ " + tipoFactura );
        factura.setTipo(tipoFactura);
//        System.out.println("############################# " + result );
//        if(result.hasErrors()){
//            return "redirect:form";
//        }
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
    
    @RequestMapping(value="/factura/consultaFacturasPorTiempo", method=RequestMethod.GET, produces={"application/json"})
    //public @ResponseBody List<Factura> consultaFacturasPorTiempo(
     public @ResponseBody List<Factura> consultaFacturasPorTiempo(
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin,
            @RequestParam("tipoFactura") String tipoFactura){
        
        System.out.println("\t::FacturaController consultaFacturasPorTiempo::");
        System.out.println("\tFechaInicio -> " + fechaInicio);
        System.out.println("\tFechaFin -> " + fechaFin);
        System.out.println("\tTipoFactura -> " + tipoFactura);
        
        //List<Factura> facturas = facturaService.listarFacturasPorTiempo(fechaInicio, fechaFin, tipoFactura);
        //System.out.println("FACTURAS -> " +  facturas);
        return facturaService.listarFacturasPorTiempo(fechaInicio, fechaFin, tipoFactura);
        //return (facturaService.listarMesCorriente(),HttpStatus.OK);
    }
     
    @RequestMapping(value="/factura/consultaTotalesPorTiempo", method=RequestMethod.GET, produces={"application/json"})
    //public @ResponseBody List<Factura> consultaFacturasPorTiempo(
     public @ResponseBody List<String> consultaTotalesPorTiempo(
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin){
        
        System.out.println("\t::FacturaController consultaTotalesPorTiempo::");
        System.out.println("\tFechaInicio -> " + fechaInicio);
        System.out.println("\tFechaFin -> " + fechaFin);
        
        String valorGenerales = "1";
        String valorViajes = "2";
        
        double totalGenerales =  facturaService.totalFacturasPorTiempo(fechaInicio, fechaFin, valorGenerales);
        double totalViajes =  facturaService.totalFacturasPorTiempo(fechaInicio, fechaFin, valorViajes);
        
        List<String> listaTotales = new ArrayList<>();
        listaTotales.add(String.format("%,.2f", (totalGenerales)));
        listaTotales.add(String.format("%,.2f", (totalViajes)));
        listaTotales.add(String.format("%,.2f", (totalViajes - totalGenerales)));
        
        return listaTotales;
    }
}
















