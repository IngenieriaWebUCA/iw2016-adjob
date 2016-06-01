package es.uca.iw.web;
import es.uca.iw.domain.Cv;
import es.uca.iw.domain.Empresa;
import es.uca.iw.domain.Usuario;
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
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.apache.commons.codec.binary.Hex;


@RequestMapping("/usuarios")
@Controller
@RooWebScaffold(path = "usuarios", formBackingObject = Usuario.class)
public class UsuarioController {


    private enum TipoUsuario1 {

        Demandante, GestorETT, GestorEmpresa
    }

    private enum TipoUsuario2 {

        GestorETT
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



    @RequestMapping(value = "/interesados/{id}", produces = "text/html")
    public String interesados(@PathVariable("id") Long id, Model uiModel) {
        // Lista los usuarios interesados en la oferta {id}
        uiModel.addAttribute("usuarios", Usuario.findAllUsuarios());

        addDateTimeFormatPatterns(uiModel);
        return "usuarios/list";
    }


    @RequestMapping(value = "/adscritos/{id}", produces = "text/html")
    public String adscritos(@PathVariable("id") Long id, Model uiModel) {
        // Lista los usuarios interesados en la oferta {id}
        Usuario usuario = getUsuario();
        
        uiModel.addAttribute("usuarios", Usuario.findAllUsuarios());

        addDateTimeFormatPatterns(uiModel);
        return "usuarios/list";
    }



    @RequestMapping(value = "/nuevo-ett", produces = "text/html")
    public String crearGestorETT(Model uiModel) {
        Usuario usuario = getUsuario();
        Set<Empresa> empresas = usuario.getEmpresas_gestionadas();
        uiModel.addAttribute("usuario", new Usuario());
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("cvs", Cv.findAllCvs());
        // Las empresas serán aquellas que estén gestionadas por el usuario
        uiModel.addAttribute("empresas", empresas);
        uiModel.addAttribute("sexoes", Arrays.asList(Sexo.values()));
        // Permitimos que el tipo sea sólo gestor ETT
        uiModel.addAttribute("tipousuarios", Arrays.asList(TipoUsuario2.values()));
        return "usuarios/create";
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


}



/*
*
*
*
*
* */
