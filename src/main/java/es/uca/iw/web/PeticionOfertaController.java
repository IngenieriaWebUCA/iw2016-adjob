package es.uca.iw.web;
import es.uca.iw.domain.Usuario;
import es.uca.iw.domain.Empresa;
import es.uca.iw.domain.Cv;
import es.uca.iw.domain.PuestoTrabajo;
import es.uca.iw.domain.Oferta;
import es.uca.iw.domain.PeticionOferta;
import es.uca.iw.reference.EstadoPeticionOferta;
import es.uca.iw.domain.MailMail;
import es.uca.iw.reference.TipoUsuario;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@RequestMapping("/peticionofertas")
@Controller
@RooWebScaffold(path = "peticionofertas", formBackingObject = PeticionOferta.class)
public class PeticionOfertaController {

    private boolean estaInscrito(Usuario usuario, long id){
        ArrayList<PeticionOferta> peticiones = new ArrayList<PeticionOferta>(PeticionOferta.findPeticionOfertasByOferta(Oferta.findOferta(id)).getResultList());
        for(PeticionOferta peticion:peticiones)
            if(peticion.getUsuario_demandante().getId() == usuario.getId())
                return true;
        return false;
    }

    // Función que dada la id de una oferta, determina si el usuario autenticado puede o no gestionarla
    private Boolean gestionaOferta(long id){
        Usuario usuario = UsuarioController.getUsuario();
        for(Empresa empresa:usuario.getEmpresas_gestionadas())
            if(empresa.getOfertas().contains(Oferta.findOferta(id)))
                return true;

        return false;
    }

    @RequestMapping(value = "/apuntar/{id}", produces = "text/html")
    public String createForm(@PathVariable("id") Long id, Model uiModel) {
        // La petición la debe iniciar un DEMANDANTE
        if(UsuarioController.hasRole("DEMANDANTE")){
            Usuario usuario = UsuarioController.getUsuario();
            if(!estaInscrito(usuario, id)){
                List<Oferta> ofertas = Arrays.asList(Oferta.findOferta(id));
                uiModel.addAttribute("peticionOferta", new PeticionOferta());
                uiModel.addAttribute("cvs", Cv.findCvsByUsuario(usuario).getResultList());
                uiModel.addAttribute("ofertas", ofertas);
                return "peticionofertas/create";
            }
            else
                return "redirect:/";
        }
        else
            return "redirect:/";
    }

    // Esta función lista las peticiones de la oferta id
    @RequestMapping(value = "/peticiones/{id}", produces = "text/html")
    public String list(@PathVariable("id") Long id, Model uiModel) {
        if(UsuarioController.hasRole("GESTOREMPRESA") || UsuarioController.hasRole("GESTORETT")){
            if(gestionaOferta(id)){
                uiModel.addAttribute("peticionofertas", PeticionOferta.findPeticionOfertasByOferta(Oferta.findOferta(id)).getResultList());

                return "peticionofertas/list";
            }
            else
                return "redirect:/ofertas/mis-ofertas";
        }
        else
            return "redirect:/";
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        if(UsuarioController.hasRole("GESTOREMPRESA") || UsuarioController.hasRole("GESTORETT")){
            PeticionOferta peticion = PeticionOferta.findPeticionOferta(id);
            if(gestionaOferta(peticion.getOferta().getId())){
                // Preparamos formulario. Lo he hecho así porque si no daba numerosos errores
                ArrayList<Cv> cv_actual = new ArrayList<Cv>();
                cv_actual.add( peticion.getCurriculum());
                ArrayList<Oferta> oferta_actual = new ArrayList<Oferta>();
                ArrayList<Usuario> usuario_actual = new ArrayList<Usuario>();
                usuario_actual.add(peticion.getUsuario_demandante());
                oferta_actual.add(peticion.getOferta());
                uiModel.addAttribute("peticionOferta", peticion);
                uiModel.addAttribute("cvs", cv_actual);
                uiModel.addAttribute("ofertas", oferta_actual);
                uiModel.addAttribute("usuarios", usuario_actual);
                uiModel.addAttribute("estadopeticionofertas", Arrays.asList(EstadoPeticionOferta.values()));
                return "peticionofertas/update";
            }
            else
                return "redirect:/";
        }
        else
            return "redirect:/peticionofertas/todas";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PeticionOferta peticionOferta, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, peticionOferta);
            return "peticionofertas/update";
        }
        uiModel.asMap().clear();
        // TODO Mejorar envío
        Usuario usuario = peticionOferta.getUsuario_demandante();
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext.xml");
        String frase = "Hola " + usuario.getNombre() + ", el estado de tu petición a la oferta " +
                peticionOferta.getOferta().getNombre() + " ha cambiado a " + peticionOferta.getEstado().toString() + ".";

        MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMail("erwoltest@gmail.com",
                usuario.getEmail(),
                "Cambio de estado en oferta", frase
        );

        peticionOferta.merge();
        return "redirect:/peticionofertas/" + encodeUrlPathSegment(peticionOferta.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/mis-peticiones", produces = "text/html")
    public String misPeticiones(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        // Acceso restringido por ruta
        Usuario usuario = UsuarioController.getUsuario();
        uiModel.addAttribute("peticionofertas", PeticionOferta.findPeticionOfertasByUsuario_demandante(usuario).getResultList());
        return "peticionofertas/list";
    }



    @RequestMapping(value = "/todas", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("peticionofertas", PeticionOferta.findPeticionOfertaEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) PeticionOferta.countPeticionOfertas() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("peticionofertas", PeticionOferta.findAllPeticionOfertas(sortFieldName, sortOrder));
        }
        return "peticionofertas/list";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid PeticionOferta peticionOferta, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, peticionOferta);
            return "peticionofertas/create";
        }
        uiModel.asMap().clear();

        Usuario usuario = UsuarioController.getUsuario();
        peticionOferta.setUsuario_demandante(usuario);
        peticionOferta.persist();

        // TODO Mejorar envío
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext.xml");
        String frase = "Hola " + usuario.getNombre() + ", te has inscrito en la oferta " + peticionOferta.getOferta().getNombre() + ".";
        MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMail("erwoltest@gmail.com",
                UsuarioController.getUsuario().getEmail(),
                "Nuevo registro en oferta", frase
                );

        return "redirect:/peticionofertas/mis-peticiones";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, Model uiModel) {
        // Restringimos el borrado al usuario demandante que creó la petición
        if(UsuarioController.hasRole("DEMANDANTE")){
            if(PeticionOferta.findPeticionOferta(id).getUsuario_demandante().equals(UsuarioController.getUsuario())){
                PeticionOferta peticionOferta = PeticionOferta.findPeticionOferta(id);
                peticionOferta.remove();
                uiModel.asMap().clear();
                return "redirect:/peticionofertas/mis-peticiones";
            }
            else
                return "redirect:/peticionofertas/mis-peticiones";
        }
        else
            return "redirect:/";
    }
}
