// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.ExperienciaIdioma;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ExperienciaIdioma_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager ExperienciaIdioma.entityManager;
    
    public static final List<String> ExperienciaIdioma.fieldNames4OrderClauseFilter = java.util.Arrays.asList("nombre_titulo", "descripcion", "entidad_emisora", "nivel");
    
    public static final EntityManager ExperienciaIdioma.entityManager() {
        EntityManager em = new ExperienciaIdioma().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long ExperienciaIdioma.countExperienciaIdiomas() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ExperienciaIdioma o", Long.class).getSingleResult();
    }
    
    public static List<ExperienciaIdioma> ExperienciaIdioma.findAllExperienciaIdiomas() {
        return entityManager().createQuery("SELECT o FROM ExperienciaIdioma o", ExperienciaIdioma.class).getResultList();
    }
    
    public static List<ExperienciaIdioma> ExperienciaIdioma.findAllExperienciaIdiomas(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ExperienciaIdioma o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ExperienciaIdioma.class).getResultList();
    }
    
    public static ExperienciaIdioma ExperienciaIdioma.findExperienciaIdioma(Long id) {
        if (id == null) return null;
        return entityManager().find(ExperienciaIdioma.class, id);
    }
    
    public static List<ExperienciaIdioma> ExperienciaIdioma.findExperienciaIdiomaEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ExperienciaIdioma o", ExperienciaIdioma.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<ExperienciaIdioma> ExperienciaIdioma.findExperienciaIdiomaEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ExperienciaIdioma o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ExperienciaIdioma.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void ExperienciaIdioma.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void ExperienciaIdioma.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            ExperienciaIdioma attached = ExperienciaIdioma.findExperienciaIdioma(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void ExperienciaIdioma.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void ExperienciaIdioma.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public ExperienciaIdioma ExperienciaIdioma.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ExperienciaIdioma merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
