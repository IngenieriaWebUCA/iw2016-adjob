// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.Curriculum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Curriculum_Roo_Jpa_Entity {
    
    declare @type: Curriculum: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Curriculum.id;
    
    @Version
    @Column(name = "version")
    private Integer Curriculum.version;
    
    public Long Curriculum.getId() {
        return this.id;
    }
    
    public void Curriculum.setId(Long id) {
        this.id = id;
    }
    
    public Integer Curriculum.getVersion() {
        return this.version;
    }
    
    public void Curriculum.setVersion(Integer version) {
        this.version = version;
    }
    
}
