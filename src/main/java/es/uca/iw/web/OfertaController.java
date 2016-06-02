package es.uca.iw.web;
import es.uca.iw.domain.Empresa;
import es.uca.iw.domain.Oferta;
import es.uca.iw.domain.PuestoTrabajo;
import es.uca.iw.domain.Usuario;
import es.uca.iw.reference.EstadoOferta;
import es.uca.iw.reference.TipoContrato;
import es.uca.iw.reference.TipoUsuario;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.List;

@RequestMapping("/ofertas")
@Controller
@RooWebScaffold(path = "ofertas", formBackingObject = Oferta.class)
public class OfertaController {


    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        //if(UsuarioController.getSesionIniciada()){
            System.out.println("Sesión iniciada");
            if(UsuarioController.getUsuario().getTipo().equals(TipoUsuario.GestorETT) || UsuarioController.getUsuario().getTipo().equals(TipoUsuario.GestorEmpresa)){
                // Comprueba que el gestor tenga esa oferta

                uiModel.addAttribute("oferta",  Oferta.findOferta(id));
                addDateTimeFormatPatterns(uiModel);
                uiModel.addAttribute("empresas", UsuarioController.getUsuario().getEmpresas_gestionadas());
                uiModel.addAttribute("puesto_buscado", PuestoTrabajo.findAllPuestoTrabajoes());
                uiModel.addAttribute("estadoofertas", Arrays.asList(EstadoOferta.values()));
                uiModel.addAttribute("tipocontratoes", Arrays.asList(TipoContrato.values()));
                return "ofertas/update";
            }
            else
                list(uiModel);

            //System.out.println("Sesión no iniciada");
            //list(uiModel);

        return null;
    }

    @RequestMapping(value = "/nueva", produces = "text/html")
    public String createForm(Model uiModel) {
        Usuario usuario = UsuarioController.getUsuario();
        Set<Empresa> empresas = usuario.getEmpresas_gestionadas();
        List<Empresa> empresasL = new ArrayList<Empresa>(empresas);
        // TODO Mostrar sólo las empresas del usuario autenticado
        uiModel.addAttribute("oferta", new Oferta());
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("empresas", empresasL);
        uiModel.addAttribute("puestrotrabajoes", PuestoTrabajo.findAllPuestoTrabajoes());
        uiModel.addAttribute("estadoofertas", Arrays.asList(EstadoOferta.values()));
        uiModel.addAttribute("tipocontratoes", Arrays.asList(TipoContrato.values()));
        return "ofertas/create";
    }

    @RequestMapping(value = "/todas", produces = "text/html")
    public String list(Model uiModel) {

        // @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder,
        /*if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("ofertas", Oferta.findOfertaEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Oferta.countOfertas() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("ofertas", Oferta.findAllOfertas(sortFieldName, sortOrder));
        }*/
        uiModel.asMap().clear();
        uiModel.addAttribute("ofertas", Oferta.findAllOfertas());
        addDateTimeFormatPatterns(uiModel);
        return "ofertas/list";
    }

    @RequestMapping(value = "/mis-ofertas", produces = "text/html")
    public String misEmpresas(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        Usuario usuario = UsuarioController.getUsuario();

        // Buscar todas las ofertas de todas las empresas que gestione el usuario
        uiModel.addAttribute("ofertas", Oferta.findAllOfertas(sortFieldName, sortOrder));
        addDateTimeFormatPatterns(uiModel);
        return "ofertas/list";
    }
}
