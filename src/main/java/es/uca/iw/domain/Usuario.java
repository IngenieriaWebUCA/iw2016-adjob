package es.uca.iw.domain;
<<<<<<< HEAD
import org.hibernate.validator.constraints.Email;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import java.util.Date;
=======
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import es.uca.iw.reference.Sexo;
import es.uca.iw.reference.TipoUsuario;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Enumerated;
>>>>>>> 6cfd50639538555ce28e1e7bf8ef397311f4aa3a
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
<<<<<<< HEAD
import es.uca.iw.reference.TipoUsuario;
import javax.persistence.Enumerated;
import es.uca.iw.reference.Sexo;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
=======
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
>>>>>>> 6cfd50639538555ce28e1e7bf8ef397311f4aa3a
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
<<<<<<< HEAD
@RooJpaActiveRecord(finders = { "findUsuariosByEmailAndContrasenaEquals", "findUsuariosByEmail", "findUsuariosByTipo" })
=======
@RooJpaActiveRecord(finders = { "findUsuariosByEmailAndContrasenaEquals" })
>>>>>>> 6cfd50639538555ce28e1e7bf8ef397311f4aa3a
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
<<<<<<< HEAD
    @Email
=======
>>>>>>> 6cfd50639538555ce28e1e7bf8ef397311f4aa3a
    @Size(min = 3, max = 30)
    private String email;

    /**
     */
<<<<<<< HEAD
    @Size(min = 9, max = 9)
    private String dni;

    /**
     */
    @NotNull
=======
    @Size(min = 3, max = 30)
>>>>>>> 6cfd50639538555ce28e1e7bf8ef397311f4aa3a
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
<<<<<<< HEAD
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
=======
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
     */
    private Boolean is_demandante = true;

    /**
     */
    private Boolean is_gestor_ett = false;

    /**
     */
    private Boolean is_gestor_empresa = false;

    /**
     */
    private Boolean is_administrador = false;

    /**
     */
    private Boolean is_superadministrador = false;
>>>>>>> 6cfd50639538555ce28e1e7bf8ef397311f4aa3a
}
