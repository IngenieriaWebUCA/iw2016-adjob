package es.uca.iw.web;
import es.uca.iw.domain.Titulos;
import es.uca.iw.domain.Usuario;
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

@RequestMapping("/tituloses")
@Controller
@RooWebScaffold(path = "tituloses", formBackingObject = Titulos.class)
public class TitulosController {
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Titulos titulos, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, titulos);
            return "tituloses/create";
        }
        uiModel.asMap().clear();
        titulos.setUsuario(UsuarioController.getUsuario());
        titulos.persist();
        return "redirect:/tituloses/" + encodeUrlPathSegment(titulos.getId().toString(), httpServletRequest);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Titulos titulos, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, titulos);
            return "tituloses/update";
        }
        uiModel.asMap().clear();
        titulos.setUsuario(UsuarioController.getUsuario());
        titulos.merge();
        return "redirect:/tituloses/" + encodeUrlPathSegment(titulos.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/nuevo", produces = "text/html")
    public String createForm(Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE")){
            populateEditForm(uiModel, new Titulos());
            return "tituloses/create";
        }
        else
            return "redirect:/";
    }


    @RequestMapping(value = "mis-titulos", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        List<Titulos> titulos = Titulos.findTitulosesByUsuario(UsuarioController.getUsuario()).getResultList();
        uiModel.addAttribute("tituloses", titulos);
        return "tituloses/list";
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE")){
            if(Titulos.findTitulosesByUsuario(UsuarioController.getUsuario()).getResultList().contains(Titulos.findTitulos(id))){
                populateEditForm(uiModel, Titulos.findTitulos(id));
                return "tituloses/update";
            }
            else
                return "tituloses/mis-titulos";

        }
        else{
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE")){
            if(Titulos.findTitulosesByUsuario(UsuarioController.getUsuario()).getResultList().contains(Titulos.findTitulos(id))){
                Titulos titulos = Titulos.findTitulos(id);
                titulos.remove();
                uiModel.asMap().clear();
                uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
                uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
                return "redirect:/tituloses";
            }
            else
                return "redirect:/tituloses/mis-titulos";
        }
        else
            return "redirect:/";
    }
}
