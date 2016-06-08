package es.uca.iw.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.FetchType;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findCvsByUsuario" })
public class Cv {

    /**
     */
    @Size(min = 0, max = 30)
    private String foto;

    /**
     */
    @Size(min = 10, max = 300)
    private String trayectoria;

    /**
     */
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<PuestoTrabajo> puestos_posibles = new HashSet<PuestoTrabajo>();

    /**
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Experiencia> experiencia = new HashSet<Experiencia>();

    /**
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Cursos> cursos = new HashSet<Cursos>();

    /**
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Idiomas> idiomas = new HashSet<Idiomas>();

    /**
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // OK
    private Set<Titulos> titulos = new HashSet<Titulos>();

    /**
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
}
