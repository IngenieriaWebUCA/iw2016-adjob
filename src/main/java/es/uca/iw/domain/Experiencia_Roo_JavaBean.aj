// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.Experiencia;
import es.uca.iw.domain.PuestoTrabajo;
import es.uca.iw.domain.Usuario;
import java.util.Date;

privileged aspect Experiencia_Roo_JavaBean {
    
    public String Experiencia.getNombre_empresa() {
        return this.nombre_empresa;
    }
    
    public void Experiencia.setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }
    
    public Date Experiencia.getFecha_inicio() {
        return this.fecha_inicio;
    }
    
    public void Experiencia.setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    
    public Date Experiencia.getFecha_fin() {
        return this.fecha_fin;
    }
    
    public void Experiencia.setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    public PuestoTrabajo Experiencia.getPuesto() {
        return this.puesto;
    }
    
    public void Experiencia.setPuesto(PuestoTrabajo puesto) {
        this.puesto = puesto;
    }
    
    public Usuario Experiencia.getUsuario() {
        return this.usuario;
    }
    
    public void Experiencia.setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}