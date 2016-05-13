package es.uca.iw.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ExperienciaIdioma {

    /**
     */
    @Size(min = 3, max = 30)
    private String nombre_titulo;

    /**
     */
    @Size(min = 0, max = 50)
    private String descripcion;

    /**
     */
    @Size(min = 3, max = 30)
    private String entidad_emisora;

    /**
     */
    @Size(min = 1, max = 10)
    private String nivel;

    /**
     */
    @ManyToOne
    private Curriculum curriculum;
}
