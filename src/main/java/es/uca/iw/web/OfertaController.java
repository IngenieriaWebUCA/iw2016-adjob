package es.uca.iw.web;
import es.uca.iw.domain.Oferta;
import es.uca.iw.domain.PeticionOferta;
import es.uca.iw.domain.Empresa;
import es.uca.iw.domain.Usuario;
import es.uca.iw.domain.PuestoTrabajo;
import es.uca.iw.domain.Cv;
import es.uca.iw.reference.EstadoOferta;
import es.uca.iw.reference.TipoContrato;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.List;


@RequestMapping("/ofertas")
@Controller
@RooWebScaffold(path = "ofertas", formBackingObject = Oferta.class)
public class OfertaController {

    // Función que dada la id de una oferta, determina si el usuario autenticado puede o no gestionarla
    public static Boolean gestionaOferta(long id){
        Usuario usuario = UsuarioController.getUsuario();
        for(Empresa empresa:usuario.getEmpresas_gestionadas())
            if(empresa.getOfertas().contains(Oferta.findOferta(id)))
                return true;

        return false;
    }

    // Función que comprueba si una oferta está en el rango de tiempo correcto para mostrarla en el listado público
    private Boolean en_rango(Oferta oferta){
        // Fecha de hoy en el mismo formato que usa Spring Roo
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date hoy = new Date();
        return oferta.getFecha_inicio().before(hoy) && oferta.getFecha_fin().after(hoy);
    }

    @RequestMapping(value = "/recomendaciones", produces = "text/html")
    public String recomendaciones(Model uiModel) {
        if (UsuarioController.hasRole("DEMANDANTE")){
            // TODO Arreglar consulta
            Set<Cv> cvs = UsuarioController.getUsuario().getCvs();
            ArrayList<Oferta> recomendadas = new ArrayList<Oferta>();
            for(Cv cv:cvs){
                Set<PuestoTrabajo> posibles = cv.getPuestos_posibles();
                for(PuestoTrabajo puesto:posibles){
                    List<Oferta> ofertas = Oferta.findOfertasByPuesto_buscado(puesto).getResultList();
                    for(Oferta of:ofertas)
                        recomendadas.add(of);
                }

            }
            uiModel.asMap().clear();
            uiModel.addAttribute("ofertas", recomendadas);
            addDateTimeFormatPatterns(uiModel);
            return "ofertas/list";
        }
        else
            return "redirect:/";

    }

    void populateEditForm(Model uiModel, Oferta oferta) {
        ArrayList<Empresa> empresas = new ArrayList<Empresa>(UsuarioController.getUsuario().getEmpresas_gestionadas());
        uiModel.addAttribute("oferta", oferta);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("empresas", empresas);
        uiModel.addAttribute("puestotrabajoes", PuestoTrabajo.findAllPuestoTrabajoes());
        uiModel.addAttribute("estadoofertas", Arrays.asList(EstadoOferta.values()));
        uiModel.addAttribute("tipocontratoes", Arrays.asList(TipoContrato.values()));
    }

    @RequestMapping(value = "/nueva", produces = "text/html")
    public String createForm(Model uiModel) {
        if(UsuarioController.hasRole("GESTORETT") || UsuarioController.hasRole("GESTOREMPRESA")){
            populateEditForm(uiModel, new Oferta());
            return "ofertas/create";
        }
        else
            return "redirect:/";
    }

    @RequestMapping(value = "/todas", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        ArrayList<Oferta> validas = new ArrayList<Oferta>();
        for(Oferta oferta:Oferta.findAllOfertas())
            if(!oferta.getEstado_oferta().equals(EstadoOferta.Cancelada) && en_rango(oferta))
                validas.add(oferta);

        uiModel.addAttribute("ofertas", validas);
        addDateTimeFormatPatterns(uiModel);
        return "ofertas/list";
    }


    @RequestMapping(value = "/mis-ofertas", produces = "text/html")
    public String misOfertas(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if(UsuarioController.hasRole("GESTORETT") || UsuarioController.hasRole("GESTOREMPRESA")){
            Usuario usuario = UsuarioController.getUsuario();
            Set<Empresa> empresas = usuario.getEmpresas_gestionadas();
            ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
            for(Empresa empresa:empresas)
                ofertas.addAll(empresa.getOfertas());
            uiModel.addAttribute("ofertas", ofertas);
            addDateTimeFormatPatterns(uiModel);
            return "ofertas/list";
        }
        else
            return "redirect:/";
    }


    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        if(UsuarioController.hasRole("GESTORETT") || UsuarioController.hasRole("GESTOREMPRESA"))
            if(gestionaOferta(id)){
                populateEditForm(uiModel, Oferta.findOferta(id));
                return "ofertas/update";
            }
            else
                return "redirect:/ofertas/mis-ofertas";
        else
            return "redirect:/";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if(UsuarioController.hasRole("GESTORETT") || UsuarioController.hasRole("GESTOREMPRESA")){
            if(gestionaOferta(id)){
                Oferta oferta = Oferta.findOferta(id);
                System.out.println("LLEGA HASTA AQUÍ!!!!!");
                oferta.remove();
                return "redirect:/ofertas/mis-ofertas";
            }
            else
                return "redirect:/ofertas/mis-ofertas";
        }
        else
            return "redirect:/ofertas/todas";
    }

    /*
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        if(UsuarioController.hasRole("GESTORETT") || UsuarioController.hasRole("GESTOREMPRESA")){
            if(gestionaOferta(id)){
                populateEditForm(uiModel, Oferta.findOferta(id));
                return "ofertas/update";
            }
            else
                return "redirect:/ofertas/mis-ofertas";
        }
        else
            return "redirect:/";
    }

    @RequestMapping(value = "/nueva", produces = "text/html")
    public String createForm(Model uiModel) {
        if(UsuarioController.hasRole("GESTOREMPRESA") || UsuarioController.hasRole("GESTORETT")){
            populateEditForm(uiModel, new Oferta());
            return "ofertas/create";
        }
        else
            return "redirect:/";
    }

    @RequestMapping(value = "/todas", produces = "text/html")
    public String list(Model uiModel) {
        uiModel.asMap().clear();
        ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
        List<Oferta> todas = Oferta.findAllOfertas();

        // Sólo listamos de forma pública ofertas no canceladas y en rango de tiempo
        for(Oferta oferta:todas)
            if(!oferta.getEstado_oferta().equals(EstadoOferta.Cancelada) && en_rango(oferta))
                ofertas.add(oferta);
        uiModel.addAttribute("ofertas", ofertas);
        addDateTimeFormatPatterns(uiModel);
        return "ofertas/list";
    }

    @RequestMapping(value = "/mis-ofertas", produces = "text/html")
    public String misOfertas(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if(UsuarioController.hasRole("GESTORETT") || UsuarioController.hasRole("GESTOREMPRESA")){
            Usuario usuario = UsuarioController.getUsuario();
            Set<Empresa> empresas = usuario.getEmpresas_gestionadas();
            ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
            for(Empresa empresa:empresas)
                ofertas.addAll(empresa.getOfertas());
            uiModel.addAttribute("ofertas", ofertas);
            addDateTimeFormatPatterns(uiModel);
            return "ofertas/list";
        }
        else
            return "redirect:/";

    }

    void populateEditForm(Model uiModel, Oferta oferta) {
        // Damos a escoger entre todas las empresas del usuario que crea la oferta
        ArrayList<Empresa> empresas = new ArrayList<Empresa>(UsuarioController.getUsuario().getEmpresas_gestionadas());
        uiModel.addAttribute("oferta", oferta);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("empresas", empresas);
        uiModel.addAttribute("puesto_buscado", PuestoTrabajo.findAllPuestoTrabajoes());
        uiModel.addAttribute("estadoofertas", Arrays.asList(EstadoOferta.values()));
        uiModel.addAttribute("tipocontratoes", Arrays.asList(TipoContrato.values()));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Oferta oferta, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, oferta);
            return "ofertas/update";
        }
        uiModel.asMap().clear();
        oferta.merge();
        return "redirect:/ofertas/" + encodeUrlPathSegment(oferta.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if(UsuarioController.hasRole("GESTORETT") || UsuarioController.hasRole("GESTOREMPRESA")){
            if(gestionaOferta(id)){
                Oferta oferta = Oferta.findOferta(id);
                oferta.remove();
                uiModel.asMap().clear();
                uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
                uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
                return "redirect:/ofertas";
            }
            else
                return "redirect:/ofertas/mis-ofertas";
        }
        else
            return "redirect:/ofertas/todas";
    }
    */
}


