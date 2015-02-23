// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ar.com.norrmann.coqui.model;

import ar.com.norrmann.coqui.model.Automotor;
import ar.com.norrmann.coqui.model.Cliente;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

privileged aspect Cliente_Roo_DbManaged {
    
    @OneToMany(mappedBy = "propietarioId")
    private Set<Automotor> Cliente.automotors;
    
    @Column(name = "apellido", length = 255)
    @NotNull
    private String Cliente.apellido;
    
    @Column(name = "domicilio", length = 255)
    private String Cliente.domicilio;
    
    @Column(name = "email", length = 255)
    private String Cliente.email;
    
    @Column(name = "nombres", length = 255)
    @NotNull
    private String Cliente.nombres;
    
    @Column(name = "telefono", length = 255)
    private String Cliente.telefono;
    
    @Column(name = "cuit", length = 255)
    private String Cliente.cuit;
    
    public Set<Automotor> Cliente.getAutomotors() {
        return automotors;
    }
    
    public void Cliente.setAutomotors(Set<Automotor> automotors) {
        this.automotors = automotors;
    }
    
    public String Cliente.getApellido() {
        return apellido;
    }
    
    public void Cliente.setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String Cliente.getDomicilio() {
        return domicilio;
    }
    
    public void Cliente.setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    public String Cliente.getEmail() {
        return email;
    }
    
    public void Cliente.setEmail(String email) {
        this.email = email;
    }
    
    public String Cliente.getNombres() {
        return nombres;
    }
    
    public void Cliente.setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String Cliente.getTelefono() {
        return telefono;
    }
    
    public void Cliente.setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String Cliente.getCuit() {
        return cuit;
    }
    
    public void Cliente.setCuit(String cuit) {
        this.cuit = cuit;
    }
    
}