// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.Idiomas;
import es.uca.iw.domain.Usuario;

privileged aspect Idiomas_Roo_JavaBean {
    
    public String Idiomas.getNombre_titulo() {
        return this.nombre_titulo;
    }
    
    public void Idiomas.setNombre_titulo(String nombre_titulo) {
        this.nombre_titulo = nombre_titulo;
    }
    
    public String Idiomas.getDescripcion() {
        return this.descripcion;
    }
    
    public void Idiomas.setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String Idiomas.getEntidad_emisora() {
        return this.entidad_emisora;
    }
    
    public void Idiomas.setEntidad_emisora(String entidad_emisora) {
        this.entidad_emisora = entidad_emisora;
    }
    
    public String Idiomas.getNivel() {
        return this.nivel;
    }
    
    public void Idiomas.setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    public Usuario Idiomas.getUsuario() {
        return this.usuario;
    }
    
    public void Idiomas.setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
