package es.uca.iw.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;

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
    @ManyToOne
    private Usuario usuario;
}
