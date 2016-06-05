package es.uca.iw.web;
import es.uca.iw.domain.Cursos;
import es.uca.iw.domain.Usuario;
import es.uca.iw.reference.TipoUsuario;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RequestMapping("/cursoses")
@Controller
@RooWebScaffold(path = "cursoses", formBackingObject = Cursos.class)
public class CursosController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Cursos cursos, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cursos);
            return "cursoses/create";
        }
        uiModel.asMap().clear();
        cursos.setUsuario(UsuarioController.getUsuario());
        cursos.persist();
        return "redirect:/cursoses/" + encodeUrlPathSegment(cursos.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/nuevo", produces = "text/html")
    public String createForm(Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE")){
            populateEditForm(uiModel, new Cursos());
            return "cursoses/create";
        }
        else{
            return "redirect:/";
        }


    }

    @RequestMapping(value = "/mis-cursos", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        List<Cursos> cursos = Cursos.findCursosesByUsuario(UsuarioController.getUsuario()).getResultList();
        uiModel.addAttribute("cursoses", cursos);
        return "cursoses/list";
    }

    void populateEditForm(Model uiModel, Cursos cursos) {
        uiModel.addAttribute("cursos", cursos);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("usuarios", Usuario.findAllUsuarios());
    }


    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE")){
            List<Cursos> cursos = Cursos.findCursosesByUsuario(UsuarioController.getUsuario()).getResultList();
            System.out.println(cursos);
            if (cursos.contains(Cursos.findCursos(id))){
                populateEditForm(uiModel, Cursos.findCursos(id));
                return "cursoses/update";
            }
            else
                return "redirect:/cursoses/mis-cursos";
        }
        else
            return "redirect:/";

    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Cursos cursos, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cursos);
            return "cursoses/update";
        }
        uiModel.asMap().clear();
        cursos.setUsuario(UsuarioController.getUsuario());
        cursos.merge();
        return "redirect:/cursoses/" + encodeUrlPathSegment(cursos.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE")){
            List<Cursos> cursos = Cursos.findCursosesByUsuario(UsuarioController.getUsuario()).getResultList();
            if (cursos.contains(Cursos.findCursos(id))){
                Cursos curso = Cursos.findCursos(id);
                curso.remove();
                uiModel.asMap().clear();
                uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
                uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
                return "redirect:/cursoses/mis-cursos";
            }
            else
                return "redirect:/cursoses/mis-cursos";
        }
        else
            return "redirect:/";
    }

}
