package es.uca.iw.web;
import es.uca.iw.domain.Cursos;
import es.uca.iw.domain.Experiencia;
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

@RequestMapping("/experiencias")
@Controller
@RooWebScaffold(path = "experiencias", formBackingObject = Experiencia.class)
public class ExperienciaController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Experiencia experiencia, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, experiencia);
            return "experiencias/create";
        }
        uiModel.asMap().clear();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = Usuario.findUsuariosByEmail(auth.getName()).getSingleResult();
        experiencia.setUsuario(usuario);
        experiencia.persist();
        return "redirect:/experiencias/" + encodeUrlPathSegment(experiencia.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/nueva", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Experiencia());
        return "experiencias/create";
    }


    @RequestMapping(value = "/mis-experiencias", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        /*
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("experiencias", Experiencia.findExperienciaEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Experiencia.countExperiencias() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("experiencias", Experiencia.findAllExperiencias(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);

        */

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = Usuario.findUsuariosByEmail(auth.getName()).getSingleResult();
        List<Experiencia> exp = Experiencia.findExperienciasByUsuario(usuario).getResultList();
        uiModel.addAttribute("experiencias", exp);
        return "experiencias/list";
    }


}
