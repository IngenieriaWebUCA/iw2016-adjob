package es.uca.iw.web;
import es.uca.iw.domain.Idiomas;
import es.uca.iw.domain.Usuario;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = Usuario.findUsuariosByEmail(auth.getName()).getSingleResult();

        idiomas.setUsuario(usuario);
        idiomas.persist();
        return "redirect:/idiomases/" + encodeUrlPathSegment(idiomas.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/nuevo", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Idiomas());
        return "idiomases/create";
    }

    @RequestMapping(value = "/mis-idiomas", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
       /* if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("idiomases", Idiomas.findIdiomasEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Idiomas.countIdiomases() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("idiomases", Idiomas.findAllIdiomases(sortFieldName, sortOrder));
        }*/

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = Usuario.findUsuariosByEmail(auth.getName()).getSingleResult();
        List<Idiomas> idiomas = Idiomas.findIdiomasesByUsuario(usuario).getResultList();
        uiModel.addAttribute("idiomases", idiomas);
        return "idiomases/list";
    }
}
