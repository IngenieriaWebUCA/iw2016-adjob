package es.uca.iw.web;
import es.uca.iw.domain.Empresa;
import es.uca.iw.domain.Oferta;
import es.uca.iw.domain.Usuario;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequestMapping("/empresas")
@Controller
@RooWebScaffold(path = "empresas", formBackingObject = Empresa.class)
public class EmpresaController {

    @RequestMapping(value="/nueva", produces = "text/html")
    public String createForm(Model uiModel) {
        if(UsuarioController.hasRole("GESTOREMPRESA") || UsuarioController.hasRole("GESTORETT")){
            populateEditForm(uiModel, new Empresa());
            return "empresas/create";
        }
        else
            return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Empresa empresa, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, empresa);
            return "empresas/create";
        }
        Usuario usuario = UsuarioController.getUsuario();
        Set<Empresa> empresas = usuario.getEmpresas_gestionadas();
        empresas.add(empresa);
        usuario.setEmpresas_gestionadas(empresas);
        uiModel.asMap().clear();
        empresa.persist();
        return "redirect:/empresas/" + encodeUrlPathSegment(empresa.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/mis-empresas", produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        uiModel.addAttribute("empresas", new ArrayList<Empresa>(UsuarioController.getUsuario().getEmpresas_gestionadas()));
        return "empresas/list";
    }

    @RequestMapping(value = "/todas", produces = "text/html")
    public String listTodo(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("empresas", Empresa.findEmpresaEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Empresa.countEmpresas() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("empresas", Empresa.findAllEmpresas(sortFieldName, sortOrder));
        }
        return "empresas/list";
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        if(UsuarioController.hasRole("GESTOREMPRESA") || UsuarioController.hasRole("GESTORETT")) {
            if (UsuarioController.getUsuario().getEmpresas_gestionadas().contains(Empresa.findEmpresa(id))) {
                populateEditForm(uiModel, Empresa.findEmpresa(id));
                return "empresas/update";
            } else
                return "redirect:/empresas/mis-empresas";
        }
        else
            return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (UsuarioController.hasRole("GESTOREMPRESA") || UsuarioController.hasRole("GESTORETT")){
            Empresa empresa = Empresa.findEmpresa(id);
            // Eliminamos la empresa de la lista de gestionadas de sus usuarios
            // TODO Mejorar orden SQL
            for(Usuario usuario:Usuario.findAllUsuarios())
                if(usuario.getEmpresas_gestionadas().contains(empresa)){
                    Set<Empresa> empresas = usuario.getEmpresas_gestionadas();
                    empresas.remove(empresa);
                    usuario.setEmpresas_gestionadas(empresas);
                    usuario.merge();
                }
            for(Oferta oferta:empresa.getOfertas())
                oferta.remove();
            empresa.remove();
            return "redirect:/empresas/mis-empresas";
        }
        else
            return "redirect:/";
    }

    void populateEditForm(Model uiModel, Empresa empresa) {
        ArrayList<Oferta> ofertas = new ArrayList<Oferta>(empresa.getOfertas());
        uiModel.addAttribute("empresa", empresa);
        uiModel.addAttribute("ofertas", ofertas);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Empresa empresa, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, empresa);
            return "empresas/update";
        }
        uiModel.asMap().clear();
        empresa.merge();
        /*
        Usuario usuario = UsuarioController.getUsuario();
        Set<Empresa> empresas = usuario.getEmpresas_gestionadas();
        empresas.add(empresa);
        usuario.setEmpresas_gestionadas(empresas);
        usuario.merge();
        */
        return "redirect:/empresas/" + encodeUrlPathSegment(empresa.getId().toString(), httpServletRequest);
    }
}
