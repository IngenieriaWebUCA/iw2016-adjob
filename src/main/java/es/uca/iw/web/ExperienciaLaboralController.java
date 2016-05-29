package es.uca.iw.web;
import es.uca.iw.domain.ExperienciaLaboral;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/experiencialaborals")
@Controller
@RooWebScaffold(path = "experiencialaborals", formBackingObject = ExperienciaLaboral.class)
public class ExperienciaLaboralController {
}
