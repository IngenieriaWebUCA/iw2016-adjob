package es.uca.iw.web;
import es.uca.iw.domain.Cv;
import es.uca.iw.domain.PeticionOferta;
import es.uca.iw.domain.Usuario;
import es.uca.iw.domain.Cursos;
import es.uca.iw.domain.Experiencia;
import es.uca.iw.domain.Idiomas;
import es.uca.iw.domain.PuestoTrabajo;
import es.uca.iw.domain.Titulos;

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
import java.util.List;

@RequestMapping("/cvs")
@Controller
@RooWebScaffold(path = "cvs", formBackingObject = Cv.class)
public class CvController {


    @RequestMapping(value = "/mis-cvs", produces = "text/html")
    public String misCvs(Model uiModel) {
        uiModel.addAttribute("cvs", Cv.findCvsByUsuario(UsuarioController.getUsuario()).getResultList());
        return "cvs/list";
    }


    @RequestMapping(value = "/nuevo", produces = "text/html")
    public String createForm(Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE")){
            populateEditForm(uiModel, new Cv(), UsuarioController.getUsuario());
            return "cvs/create";
        }
        else{
            return "redirect:/";
        }
    }

    void populateEditForm(Model uiModel, Cv cv, Usuario usuario) {
        uiModel.addAttribute("cv", cv);
        uiModel.addAttribute("cursoses", Cursos.findCursosesByUsuario(usuario).getResultList());
        uiModel.addAttribute("experiencias", Experiencia.findExperienciasByUsuario(usuario).getResultList());
        uiModel.addAttribute("idiomases", Idiomas.findIdiomasesByUsuario(usuario).getResultList());
        uiModel.addAttribute("puestotrabajoes", PuestoTrabajo.findAllPuestoTrabajoes());
        uiModel.addAttribute("tituloses", Titulos.findTitulosesByUsuario(usuario).getResultList());
        uiModel.addAttribute("usuarios", Usuario.findAllUsuarios());
    }


    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Cv cv, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cv, UsuarioController.getUsuario());
            return "cvs/create";
        }
        uiModel.asMap().clear();
        cv.setUsuario(UsuarioController.getUsuario());
        cv.persist();
        return "redirect:/cvs/" + encodeUrlPathSegment(cv.getId().toString(), httpServletRequest);
    }







    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Cv cv, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cv, UsuarioController.getUsuario());
            return "cvs/update";
        }
        // TODO Guardar los cursos experiencias e idiomas que el usuario tenía antes de actualizar el CV
        uiModel.asMap().clear();
        cv.setUsuario(UsuarioController.getUsuario());
        cv.merge();
        return "redirect:/cvs/" + encodeUrlPathSegment(cv.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE"))
            if(Cv.findCvsByUsuario(UsuarioController.getUsuario()).getResultList().contains(Cv.findCv(id))){
                populateEditForm(uiModel, Cv.findCv(id), UsuarioController.getUsuario());
                return "cvs/update";
            }
            else
                return "redirect:/cvs/mis-cvs";
        else
            return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE"))
            if(Cv.findCvsByUsuario(UsuarioController.getUsuario()).getResultList().contains(Cv.findCv(id))){
                Cv cv = Cv.findCv(id);
                // Buscamos todas las peticiones de oferta que haya realizado el usuario con este CV
                ArrayList<PeticionOferta> peticiones = new ArrayList<PeticionOferta>(PeticionOferta.findPeticionOfertasByCurriculum(cv).getResultList());
                for(PeticionOferta peticion:peticiones)
                    peticion.remove();
                cv.remove();
                uiModel.asMap().clear();
                uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
                uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
                return "redirect:/cvs/mis-cvs";
            }
            else
                return "redirect:/cvs/mis-cvs";
        else
            return "redirect:/";
    }
}