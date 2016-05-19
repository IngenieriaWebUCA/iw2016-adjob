// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.ExperienciaTitulo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ExperienciaTitulo_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager ExperienciaTitulo.entityManager;
    
    public static final List<String> ExperienciaTitulo.fieldNames4OrderClauseFilter = java.util.Arrays.asList("nombre", "entidad_emisora", "fecha_obtencion", "descripcion");
    
    public static final EntityManager ExperienciaTitulo.entityManager() {
        EntityManager em = new ExperienciaTitulo().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long ExperienciaTitulo.countExperienciaTituloes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ExperienciaTitulo o", Long.class).getSingleResult();
    }
    
    public static List<ExperienciaTitulo> ExperienciaTitulo.findAllExperienciaTituloes() {
        return entityManager().createQuery("SELECT o FROM ExperienciaTitulo o", ExperienciaTitulo.class).getResultList();
    }
    
    public static List<ExperienciaTitulo> ExperienciaTitulo.findAllExperienciaTituloes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ExperienciaTitulo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ExperienciaTitulo.class).getResultList();
    }
    
    public static ExperienciaTitulo ExperienciaTitulo.findExperienciaTitulo(Long id) {
        if (id == null) return null;
        return entityManager().find(ExperienciaTitulo.class, id);
    }
    
    public static List<ExperienciaTitulo> ExperienciaTitulo.findExperienciaTituloEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ExperienciaTitulo o", ExperienciaTitulo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<ExperienciaTitulo> ExperienciaTitulo.findExperienciaTituloEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ExperienciaTitulo o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ExperienciaTitulo.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void ExperienciaTitulo.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void ExperienciaTitulo.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            ExperienciaTitulo attached = ExperienciaTitulo.findExperienciaTitulo(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void ExperienciaTitulo.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void ExperienciaTitulo.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public ExperienciaTitulo ExperienciaTitulo.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ExperienciaTitulo merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}