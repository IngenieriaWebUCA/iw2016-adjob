package es.uca.iw.domain;
import org.hibernate.validator.constraints.Email;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import es.uca.iw.reference.TipoUsuario;
import javax.persistence.Enumerated;
import es.uca.iw.reference.Sexo;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUsuariosByEmailAndContrasenaEquals", "findUsuariosByEmail", "findUsuariosByTipo" })
public class Usuario {

    /**
     */
    @Size(min = 3, max = 30)
    private String nombre;

    /**
     */
    @Size(min = 3, max = 50)
    private String apellidos;

    /**
     */
    @Email
    @Size(min = 3, max = 30)
    private String email;

    /**
     */
    @Size(min = 9, max = 9)
    private String dni;

    /**
     */
    @NotNull
    private String contrasena;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date fechaNacimiento;

    /**
     */
    @Size(min = 3, max = 30)
    private String direccion;

    /**
     */
    private Integer telefono;

    /**
     */
    @Enumerated
    private TipoUsuario tipo;

    /**
     */
    @Enumerated
    private Sexo sexo;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Empresa> empresas_gestionadas = new HashSet<Empresa>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Set<Cv> cvs = new HashSet<Cv>();
}
