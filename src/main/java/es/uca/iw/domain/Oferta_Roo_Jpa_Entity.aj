// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.Oferta;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Oferta_Roo_Jpa_Entity {
    
    declare @type: Oferta: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Oferta.id;
    
    @Version
    @Column(name = "version")
    private Integer Oferta.version;
    
    public Long Oferta.getId() {
        return this.id;
    }
    
    public void Oferta.setId(Long id) {
        this.id = id;
    }
    
    public Integer Oferta.getVersion() {
        return this.version;
    }
    
    public void Oferta.setVersion(Integer version) {
        this.version = version;
    }
    
}
