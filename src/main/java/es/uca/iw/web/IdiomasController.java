package es.uca.iw.web;
import es.uca.iw.domain.Idiomas;
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

@RequestMapping("/idiomases")
@Controller
@RooWebScaffold(path = "idiomases", formBackingObject = Idiomas.class)
public class IdiomasController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Idiomas idiomas, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, idiomas);
            return "idiomases/create";
        }
        uiModel.asMap().clear();
        idiomas.setUsuario(UsuarioController.getUsuario());
        idiomas.persist();
        return "redirect:/idiomases/" + encodeUrlPathSegment(idiomas.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/nuevo", produces = "text/html")
    public String createForm(Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE")){
            populateEditForm(uiModel, new Idiomas());
            return "idiomases/create";
        }
        else
            return "redirect:/";
    }

    @RequestMapping(value = "/mis-idiomas", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        List<Idiomas> idiomas = Idiomas.findIdiomasesByUsuario(UsuarioController.getUsuario()).getResultList();
        uiModel.addAttribute("idiomases", idiomas);
        return "idiomases/list";
    }



    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Idiomas idiomas, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, idiomas);
            return "idiomases/update";
        }
        uiModel.asMap().clear();
        idiomas.setUsuario(UsuarioController.getUsuario());
        idiomas.merge();
        return "redirect:/idiomases/" + encodeUrlPathSegment(idiomas.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE"))
            if(Idiomas.findIdiomasesByUsuario(UsuarioController.getUsuario()).getResultList().contains(Idiomas.findIdiomas(id))){
                populateEditForm(uiModel, Idiomas.findIdiomas(id));
                return "idiomases/update";
            }
            else
                return "redirect:/idiomases/mis-idiomas";
        else
            return "redirect:/";


    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if(UsuarioController.hasRole("DEMANDANTE")){
            if(Idiomas.findIdiomasesByUsuario(UsuarioController.getUsuario()).getResultList().contains(Idiomas.findIdiomas(id))){
                Idiomas idiomas = Idiomas.findIdiomas(id);
                idiomas.remove();
                uiModel.asMap().clear();
                uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
                uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
                return "redirect:/idiomases";
            }
            else
                return "redirect:/idiomases/mis-idiomas";
        }
        else
            return "redirect:/";
    }
}
