package es.uca.iw.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Curriculum {

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
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PuestoTrabajo> puestos_posibles = new HashSet<PuestoTrabajo>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ExperienciaLaboral> experiencia_laboral = new HashSet<ExperienciaLaboral>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ExperienciaCurso> experiencia_curso = new HashSet<ExperienciaCurso>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ExperienciaIdioma> experiencia_idioma = new HashSet<ExperienciaIdioma>();

    /**
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ExperienciaTitulo> experiencia_titulo = new HashSet<ExperienciaTitulo>();
}
