package es.uca.iw.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.OneToOne;
import es.uca.iw.reference.EstadoPeticionOferta;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
<<<<<<< HEAD
@RooJpaActiveRecord(finders = { "findPeticionOfertasByUsuario_demandante", "findPeticionOfertasByOferta" })
=======
@RooJpaActiveRecord
>>>>>>> 6cfd50639538555ce28e1e7bf8ef397311f4aa3a
public class PeticionOferta {

    /**
     */
    @OneToOne
    private Usuario usuario_demandante;

    /**
     */
<<<<<<< HEAD
    @Enumerated
    private EstadoPeticionOferta estado;

    /**
     */
    @OneToOne
    private Cv curriculum;
=======
    @OneToOne
    private OfertaTrabajo oferta;

    /**
     */
    @Enumerated
    private EstadoPeticionOferta estado;
>>>>>>> 6cfd50639538555ce28e1e7bf8ef397311f4aa3a

    /**
     */
    @OneToOne
<<<<<<< HEAD
    private Oferta oferta;
=======
    private Curriculum curriculum_publicado;
>>>>>>> 6cfd50639538555ce28e1e7bf8ef397311f4aa3a
}
