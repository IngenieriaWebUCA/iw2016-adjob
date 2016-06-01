// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.web;

import es.uca.iw.domain.Idiomas;
import es.uca.iw.domain.Usuario;
import es.uca.iw.web.IdiomasController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect IdiomasController_Roo_Controller {
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String IdiomasController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("idiomas", Idiomas.findIdiomas(id));
        uiModel.addAttribute("itemId", id);
        return "idiomases/show";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String IdiomasController.update(@Valid Idiomas idiomas, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, idiomas);
            return "idiomases/update";
        }
        uiModel.asMap().clear();
        idiomas.merge();
        return "redirect:/idiomases/" + encodeUrlPathSegment(idiomas.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String IdiomasController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Idiomas.findIdiomas(id));
        return "idiomases/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String IdiomasController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Idiomas idiomas = Idiomas.findIdiomas(id);
        idiomas.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/idiomases";
    }
    
    void IdiomasController.populateEditForm(Model uiModel, Idiomas idiomas) {
        uiModel.addAttribute("idiomas", idiomas);
        uiModel.addAttribute("usuarios", Usuario.findAllUsuarios());
    }
    
    String IdiomasController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}