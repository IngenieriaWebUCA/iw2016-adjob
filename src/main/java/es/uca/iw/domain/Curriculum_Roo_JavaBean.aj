// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.Curriculum;
import es.uca.iw.domain.Usuario;

privileged aspect Curriculum_Roo_JavaBean {
    
    public String Curriculum.getFoto() {
        return this.foto;
    }
    
    public void Curriculum.setFoto(String foto) {
        this.foto = foto;
    }
    
    public String Curriculum.getTrayectoria() {
        return this.trayectoria;
    }
    
    public void Curriculum.setTrayectoria(String trayectoria) {
        this.trayectoria = trayectoria;
    }
    
    public Usuario Curriculum.getUsuario() {
        return this.usuario;
    }
    
    public void Curriculum.setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
