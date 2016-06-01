package es.uca.iw.web;
import es.uca.iw.domain.Cursos;
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
        // TODO Añadir usuario
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = Usuario.findUsuariosByEmail(auth.getName()).getSingleResult();
        cursos.setUsuario(usuario);
        cursos.persist();
        return "redirect:/cursoses/" + encodeUrlPathSegment(cursos.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/nuevo", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Cursos());
        return "cursoses/create";
    }

    @RequestMapping(value = "/mis-cursos", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {


        /*
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("cursoses", Cursos.findCursosEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Cursos.countCursoses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("cursoses", Cursos.findAllCursoses(sortFieldName, sortOrder));
        }
        */
        // addDateTimeFormatPatterns(uiModel);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = Usuario.findUsuariosByEmail(auth.getName()).getSingleResult();
        List<Cursos> cursos = Cursos.findCursosesByUsuario(usuario).getResultList();
        uiModel.addAttribute("cursoses", cursos);

        return "cursoses/list";
    }

}
