package es.uca.iw.web;
import es.uca.iw.domain.ExperienciaIdioma;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/experienciaidiomas")
@Controller
@RooWebScaffold(path = "experienciaidiomas", formBackingObject = ExperienciaIdioma.class)
public class ExperienciaIdiomaController {
}
