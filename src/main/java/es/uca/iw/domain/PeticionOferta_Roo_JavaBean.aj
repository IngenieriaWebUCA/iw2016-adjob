// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.domain;

import es.uca.iw.domain.Cv;
import es.uca.iw.domain.Oferta;
import es.uca.iw.domain.PeticionOferta;
import es.uca.iw.domain.Usuario;
import es.uca.iw.reference.EstadoPeticionOferta;

privileged aspect PeticionOferta_Roo_JavaBean {
    
    public Usuario PeticionOferta.getUsuario_demandante() {
        return this.usuario_demandante;
    }
    
    public void PeticionOferta.setUsuario_demandante(Usuario usuario_demandante) {
        this.usuario_demandante = usuario_demandante;
    }
    
    public EstadoPeticionOferta PeticionOferta.getEstado() {
        return this.estado;
    }
    
    public void PeticionOferta.setEstado(EstadoPeticionOferta estado) {
        this.estado = estado;
    }
    
    public Cv PeticionOferta.getCurriculum() {
        return this.curriculum;
    }
    
    public void PeticionOferta.setCurriculum(Cv curriculum) {
        this.curriculum = curriculum;
    }
    
    public Oferta PeticionOferta.getOferta() {
        return this.oferta;
    }
    
    public void PeticionOferta.setOferta(Oferta oferta) {
        this.oferta = oferta;
    }
    
}
