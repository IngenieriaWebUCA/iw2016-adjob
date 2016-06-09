package es.uca.iw.web;
import es.uca.iw.domain.Usuario;
import es.uca.iw.domain.Empresa;
import es.uca.iw.domain.Cv;
import es.uca.iw.domain.PuestoTrabajo;
import es.uca.iw.domain.Oferta;
import es.uca.iw.domain.PeticionOferta;
import es.uca.iw.domain.MailMail;

/*


import es.uca.iw.domain.Usuario;
import es.uca.iw.domain.Empresa;
import es.uca.iw.domain.Cv;
import es.uca.iw.domain.PuestoTrabajo;
import es.uca.iw.domain.Oferta;
import es.uca.iw.domain.PeticionOferta;
 */

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import es.uca.iw.reference.Sexo;
import es.uca.iw.reference.TipoUsuario;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.apache.commons.codec.binary.Hex;


@RequestMapping("/usuarios")
@Controller
@RooWebScaffold(path = "usuarios", formBackingObject = Usuario.class)
public class UsuarioController {


    private enum TipoUsuario1 {

        Demandante, GestorEmpresa
    }

    private enum TipoUsuario2 {

        GestorETT
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if(getUsuario().getId() == id){
            Usuario usuario = Usuario.findUsuario(id);
            usuario.remove();
            uiModel.asMap().clear();
            uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
            uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
            return "redirect:/resources/j_spring_security_logout";
        }
        else
            return "redirect:/";
    }

    @RequestMapping(value = "/nuevo", produces = "text/html")
    public String createForm(Model uiModel) {
        uiModel.addAttribute("usuario", new Usuario());
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("cvs", Cv.findAllCvs());
        uiModel.addAttribute("empresas", Empresa.findAllEmpresas());
        uiModel.addAttribute("sexoes", Arrays.asList(Sexo.values()));
        uiModel.addAttribute("tipousuarios", Arrays.asList(TipoUsuario1.values()));
        return "usuarios/create";
    }


    // https://jira.spring.io/browse/ROO-3456
    @RequestMapping(value = "/interesados/{id}", produces = "text/html")
    public String interesados(@PathVariable("id") Long id, Model uiModel) {
        // Lista los usuarios interesados en la oferta {id}
        // puestos_posibles
        List<Cv> cvs = Cv.findAllCvs();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        for(Cv cv:cvs){
            Set<PuestoTrabajo> puestos = cv.getPuestos_posibles();
            if(puestos.contains(Oferta.findOferta(id).getPuesto_buscado()))
                usuarios.add(cv.getUsuario());
        }
        uiModel.addAttribute("usuarios", usuarios);

        addDateTimeFormatPatterns(uiModel);
        return "usuarios/list";
    }


    @RequestMapping(value = "/adscritos/{id}", produces = "text/html")
    public String adscritos(@PathVariable("id") Long id, Model uiModel) {
        // Lista los usuarios interesados en la oferta {id}
        List<PeticionOferta> peticiones = PeticionOferta.findPeticionOfertasByOferta(Oferta.findOferta(id)).getResultList();
        ArrayList<Usuario> adscritos = new ArrayList<Usuario>();
        for(PeticionOferta peticion:peticiones)
            adscritos.add(peticion.getUsuario_demandante());
        uiModel.addAttribute("usuarios", adscritos);
        addDateTimeFormatPatterns(uiModel);
        return "usuarios/list";
    }


    @RequestMapping(value = "/nuevo-ett", produces = "text/html")
    public String createett(Model uiModel) {
        ArrayList<Empresa> empresas = new ArrayList<Empresa>(getUsuario().getEmpresas_gestionadas());
        uiModel.addAttribute("usuario", new Usuario());
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("empresas", empresas);
        uiModel.addAttribute("sexoes", Arrays.asList(Sexo.values()));
        uiModel.addAttribute("tipousuarios", Arrays.asList(TipoUsuario2.values()));
        return "usuarios/createett";
    }


    @RequestMapping(value = "/mi-cuenta", produces = "text/html")
    public String mostrar(Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = Usuario.findUsuariosByEmail(email).getSingleResult();
        uiModel.addAttribute("usuario", usuario);
        uiModel.addAttribute("itemId", usuario.getId());
        return "usuarios/show";
    }


    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Usuario usuario, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, usuario);
            return "usuarios/create";
        }
        uiModel.asMap().clear();
        try{
            usuario.setContrasena(sha256(usuario.getContrasena()));
        }
        catch(Exception e){

        }

        usuario.persist();
        return "redirect:/usuarios/" + encodeUrlPathSegment(usuario.getId().toString(), httpServletRequest);
    }


    @RequestMapping(value="/mis-etts", produces = "text/html")
    public String misETTS(Model uiModel) {
        Usuario usuario = getUsuario();
        Set<Usuario> mis_gestores = null;
        Set<Empresa> mis_empresas = usuario.getEmpresas_gestionadas();
        TipoUsuario gestor = TipoUsuario.GestorETT;
        List<Usuario> gestores = Usuario.findUsuariosByTipo(gestor).getResultList();
        for(Empresa empresa:mis_empresas){
            for(Usuario gest:gestores){
                Set<Empresa> empres = gest.getEmpresas_gestionadas();
                for(Empresa x:empres)
                    if(mis_empresas.contains(x))
                        mis_gestores.add(gest);
            }

        }

        uiModel.addAttribute("usuarios", mis_gestores);


        addDateTimeFormatPatterns(uiModel);
        return "usuarios/list";
    }


    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        if(UsuarioController.getUsuario().getId() == id){
            uiModel.addAttribute("usuario", Usuario.findUsuario(id));
            addDateTimeFormatPatterns(uiModel);
            //uiModel.addAttribute("cvs", Cv.findAllCvs());
            uiModel.addAttribute("empresas", Empresa.findAllEmpresas());
            uiModel.addAttribute("sexoes", Arrays.asList(Sexo.values()));
            uiModel.addAttribute("tipousuarios", Arrays.asList(TipoUsuario1.values()));
            return "usuarios/update";
        }
        else
            return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Usuario usuario, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            System.out.println("ERRORES AL EDITAR: " + bindingResult.getAllErrors());
            uiModel.addAttribute("usuario", usuario);
            addDateTimeFormatPatterns(uiModel);
            //uiModel.addAttribute("cvs", Cv.findAllCvs());
            uiModel.addAttribute("empresas", Empresa.findAllEmpresas());
            uiModel.addAttribute("sexoes", Arrays.asList(Sexo.values()));
            uiModel.addAttribute("tipousuarios", Arrays.asList(TipoUsuario1.values()));
            return "usuarios/update";
        }
        uiModel.asMap().clear();
        usuario.merge();
        return "redirect:/usuarios/mi-cuenta";
    }


    public static Usuario getUsuario(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = Usuario.findUsuariosByEmail(email).getSingleResult();
        return usuario;
    }

    public String sha256(String original) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        return new String(Hex.encodeHexString(digest));
    }

    public static boolean hasRole(String role) {
        // get security context from thread local
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null)
            return false;

        Authentication authentication = context.getAuthentication();
        if (authentication == null)
            return false;

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            // System.out.println("Comparando autorización >" + auth.getAuthority() + "< con >" + role + "<.");
            if (role.equals(auth.getAuthority()))
                return true;
        }
        return false;
    }
}
