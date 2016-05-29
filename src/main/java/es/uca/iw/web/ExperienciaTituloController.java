package es.uca.iw.web;
import es.uca.iw.domain.ExperienciaTitulo;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/experienciatituloes")
@Controller
@RooWebScaffold(path = "experienciatituloes", formBackingObject = ExperienciaTitulo.class)
public class ExperienciaTituloController {
}
