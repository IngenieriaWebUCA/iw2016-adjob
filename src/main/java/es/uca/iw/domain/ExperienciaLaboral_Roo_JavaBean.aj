// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.Curriculum;
import es.uca.iw.domain.ExperienciaLaboral;
import es.uca.iw.domain.PuestoTrabajo;
import java.util.Date;

privileged aspect ExperienciaLaboral_Roo_JavaBean {
    
    public String ExperienciaLaboral.getNombre_empresa() {
        return this.nombre_empresa;
    }
    
    public void ExperienciaLaboral.setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }
    
    public Date ExperienciaLaboral.getFecha_inicio() {
        return this.fecha_inicio;
    }
    
    public void ExperienciaLaboral.setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    
    public Date ExperienciaLaboral.getFecha_fin() {
        return this.fecha_fin;
    }
    
    public void ExperienciaLaboral.setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    public Curriculum ExperienciaLaboral.getCurriculum() {
        return this.curriculum;
    }
    
    public void ExperienciaLaboral.setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
    
    public PuestoTrabajo ExperienciaLaboral.getPuesto() {
        return this.puesto;
    }
    
    public void ExperienciaLaboral.setPuesto(PuestoTrabajo puesto) {
        this.puesto = puesto;
    }
    
}
