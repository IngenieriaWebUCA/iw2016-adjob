// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.Titulos;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Titulos_Roo_Jpa_Entity {
    
    declare @type: Titulos: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Titulos.id;
    
    @Version
    @Column(name = "version")
    private Integer Titulos.version;
    
    public Long Titulos.getId() {
        return this.id;
    }
    
    public void Titulos.setId(Long id) {
        this.id = id;
    }
    
    public Integer Titulos.getVersion() {
        return this.version;
    }
    
    public void Titulos.setVersion(Integer version) {
        this.version = version;
    }
    
}
