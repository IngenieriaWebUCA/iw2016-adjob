// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.Cursos;
import es.uca.iw.domain.Cv;
import es.uca.iw.domain.Experiencia;
import es.uca.iw.domain.Idiomas;
import es.uca.iw.domain.PuestoTrabajo;
import es.uca.iw.domain.Titulos;
import es.uca.iw.domain.Usuario;
import java.util.Set;

privileged aspect Cv_Roo_JavaBean {
    
    public String Cv.getFoto() {
        return this.foto;
    }
    
    public void Cv.setFoto(String foto) {
        this.foto = foto;
    }
    
    public String Cv.getTrayectoria() {
        return this.trayectoria;
    }
    
    public void Cv.setTrayectoria(String trayectoria) {
        this.trayectoria = trayectoria;
    }
    
    public Set<PuestoTrabajo> Cv.getPuestos_posibles() {
        return this.puestos_posibles;
    }
    
    public void Cv.setPuestos_posibles(Set<PuestoTrabajo> puestos_posibles) {
        this.puestos_posibles = puestos_posibles;
    }
    
    public Set<Experiencia> Cv.getExperiencia() {
        return this.experiencia;
    }
    
    public void Cv.setExperiencia(Set<Experiencia> experiencia) {
        this.experiencia = experiencia;
    }
    
    public Set<Cursos> Cv.getCursos() {
        return this.cursos;
    }
    
    public void Cv.setCursos(Set<Cursos> cursos) {
        this.cursos = cursos;
    }
    
    public Set<Idiomas> Cv.getIdiomas() {
        return this.idiomas;
    }
    
    public void Cv.setIdiomas(Set<Idiomas> idiomas) {
        this.idiomas = idiomas;
    }
    
    public Set<Titulos> Cv.getTitulos() {
        return this.titulos;
    }
    
    public void Cv.setTitulos(Set<Titulos> titulos) {
        this.titulos = titulos;
    }
    
    public Usuario Cv.getUsuario() {
        return this.usuario;
    }
    
    public void Cv.setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}