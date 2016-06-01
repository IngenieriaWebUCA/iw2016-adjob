package es.uca.iw.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.OneToOne;
import es.uca.iw.reference.EstadoPeticionOferta;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findPeticionOfertasByUsuario_demandante", "findPeticionOfertasByOferta" })
public class PeticionOferta {

    /**
     */
    @OneToOne
    private Usuario usuario_demandante;

    /**
     */
    @Enumerated
    private EstadoPeticionOferta estado;

    /**
     */
    @OneToOne
    private Cv curriculum;

    /**
     */
    @OneToOne
    private Oferta oferta;
}
