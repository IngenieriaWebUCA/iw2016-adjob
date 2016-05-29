package es.uca.iw.web;
import es.uca.iw.domain.PuestoTrabajo;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/puestotrabajoes")
@Controller
@RooWebScaffold(path = "puestotrabajoes", formBackingObject = PuestoTrabajo.class)
public class PuestoTrabajoController {
}
