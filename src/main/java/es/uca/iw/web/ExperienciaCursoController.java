package es.uca.iw.web;
import es.uca.iw.domain.ExperienciaCurso;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/experienciacursoes")
@Controller
@RooWebScaffold(path = "experienciacursoes", formBackingObject = ExperienciaCurso.class)
public class ExperienciaCursoController {
}
