package es.uca.iw.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import es.uca.iw.reference.Sexo;
import es.uca.iw.reference.TipoUsuario;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
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
    @Size(min = 3, max = 30)
    private String email;

    /**
     */
    @Size(min = 3, max = 30)
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

    @Enumerated
    private TipoUsuario tipo;

    /**
     */
    @Enumerated
    private Sexo sexo;

    /**
     */
    @Size(min = 9, max = 9)
    private String dni;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Curriculum> curriculums = new HashSet<Curriculum>();

    /**
     * Una empresa puede estar gestionada por múltiples gestores
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Empresa> empresas = new HashSet<Empresa>();
}
