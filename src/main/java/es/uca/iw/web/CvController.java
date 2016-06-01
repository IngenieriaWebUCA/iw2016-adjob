package es.uca.iw.web;
import es.uca.iw.domain.Cv;
import es.uca.iw.domain.Usuario;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RequestMapping("/cvs")
@Controller
@RooWebScaffold(path = "cvs", formBackingObject = Cv.class)
public class CvController {


    @RequestMapping(value = "/mis-cvs", produces = "text/html")
    public String list(Model uiModel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = Usuario.findUsuariosByEmail(email).getSingleResult();
        List<Cv> cvs = Cv.findCvsByUsuario(usuario).getResultList();
        uiModel.addAttribute("cvs", cvs);
        // TODO Mostrar sólo los cursos y etc de este usuario
        return "cvs/list";
    }


    @RequestMapping(value = "/nuevo", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Cv());
        return "cvs/create";
    }



    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Cv cv, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, cv);
            return "cvs/create";
        }
        uiModel.asMap().clear();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = Usuario.findUsuariosByEmail(email).getSingleResult();

        cv.setUsuario(usuario);
        cv.persist();
        return "redirect:/cvs/" + encodeUrlPathSegment(cv.getId().toString(), httpServletRequest);
    }
}