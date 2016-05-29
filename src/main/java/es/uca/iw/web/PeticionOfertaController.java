package es.uca.iw.web;
import es.uca.iw.domain.PeticionOferta;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/peticionofertas")
@Controller
@RooWebScaffold(path = "peticionofertas", formBackingObject = PeticionOferta.class)
public class PeticionOfertaController {
}
