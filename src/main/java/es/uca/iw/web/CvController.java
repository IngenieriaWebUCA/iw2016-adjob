package es.uca.iw.web;
import es.uca.iw.domain.Cv;
import es.uca.iw.domain.Idiomas;
import es.uca.iw.domain.Cursos;
import es.uca.iw.domain.Experiencia;
import es.uca.iw.domain.Usuario;
import es.uca.iw.domain.PuestoTrabajo;
import es.uca.iw.domain.Titulos;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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


    // TODO: No funciona listar sólo los cursos y demás del usuario. Se borra el AJ
    @RequestMapping(value = "/nuevo", produces = "text/html")
    public String createForm(Model uiModel) {
        Usuario usuario = UsuarioController.getUsuario();
        uiModel.addAttribute("cv", new Cv());
        uiModel.addAttribute("cursoses", Cursos.findCursosesByUsuario(usuario).getResultList());
        uiModel.addAttribute("experiencias", Experiencia.findExperienciasByUsuario(usuario).getResultList());
        uiModel.addAttribute("idiomases", Idiomas.findIdiomasesByUsuario(usuario).getResultList());
        uiModel.addAttribute("puestotrabajoes", PuestoTrabajo.findAllPuestoTrabajoes());
        uiModel.addAttribute("tituloses", Titulos.findTitulosesByUsuario(usuario).getResultList());
        uiModel.addAttribute("usuarios", Usuario.findAllUsuarios());
        return "cvs/create";
    }




    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Cv cv, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cv);
            return "cvs/create";
        }
        uiModel.asMap().clear();
        cv.setUsuario(UsuarioController.getUsuario());
        cv.persist();
        return "redirect:/cvs/" + encodeUrlPathSegment(cv.getId().toString(), httpServletRequest);
    }
}