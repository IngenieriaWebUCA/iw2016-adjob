// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.ExperienciaCurso;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect ExperienciaCurso_Roo_Jpa_Entity {
    
    declare @type: ExperienciaCurso: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long ExperienciaCurso.id;
    
    @Version
    @Column(name = "version")
    private Integer ExperienciaCurso.version;
    
    public Long ExperienciaCurso.getId() {
        return this.id;
    }
    
    public void ExperienciaCurso.setId(Long id) {
        this.id = id;
    }
    
    public Integer ExperienciaCurso.getVersion() {
        return this.version;
    }
    
    public void ExperienciaCurso.setVersion(Integer version) {
        this.version = version;
    }
    
}
