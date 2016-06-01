// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.Titulos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Titulos_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Titulos.entityManager;
    
    public static final List<String> Titulos.fieldNames4OrderClauseFilter = java.util.Arrays.asList("nombre", "entidad_emisora", "descripcion", "fecha_obtencion", "usuario");
    
    public static final EntityManager Titulos.entityManager() {
        EntityManager em = new Titulos().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Titulos.countTituloses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Titulos o", Long.class).getSingleResult();
    }
    
    public static List<Titulos> Titulos.findAllTituloses() {
        return entityManager().createQuery("SELECT o FROM Titulos o", Titulos.class).getResultList();
    }
    
    public static List<Titulos> Titulos.findAllTituloses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Titulos o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Titulos.class).getResultList();
    }
    
    public static Titulos Titulos.findTitulos(Long id) {
        if (id == null) return null;
        return entityManager().find(Titulos.class, id);
    }
    
    public static List<Titulos> Titulos.findTitulosEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Titulos o", Titulos.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Titulos> Titulos.findTitulosEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Titulos o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Titulos.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Titulos.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Titulos.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Titulos attached = Titulos.findTitulos(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Titulos.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Titulos.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Titulos Titulos.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Titulos merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
