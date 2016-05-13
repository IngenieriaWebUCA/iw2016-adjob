// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package es.uca.iw.web;

import es.uca.iw.domain.Empresa;
import es.uca.iw.domain.Usuario;
import es.uca.iw.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<Empresa, String> ApplicationConversionServiceFactoryBean.getEmpresaToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<es.uca.iw.domain.Empresa, java.lang.String>() {
            public String convert(Empresa empresa) {
                return new StringBuilder().append(empresa.getNombre()).append(' ').append(empresa.getCif()).append(' ').append(empresa.getActividad_profesional()).append(' ').append(empresa.getNumero_empleados()).toString();
            }
        };
    }
    
    public Converter<Long, Empresa> ApplicationConversionServiceFactoryBean.getIdToEmpresaConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, es.uca.iw.domain.Empresa>() {
            public es.uca.iw.domain.Empresa convert(java.lang.Long id) {
                return Empresa.findEmpresa(id);
            }
        };
    }
    
    public Converter<String, Empresa> ApplicationConversionServiceFactoryBean.getStringToEmpresaConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, es.uca.iw.domain.Empresa>() {
            public es.uca.iw.domain.Empresa convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Empresa.class);
            }
        };
    }
    
    public Converter<Usuario, String> ApplicationConversionServiceFactoryBean.getUsuarioToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<es.uca.iw.domain.Usuario, java.lang.String>() {
            public String convert(Usuario usuario) {
                return new StringBuilder().append(usuario.getNombre()).append(' ').append(usuario.getApellidos()).append(' ').append(usuario.getEmail()).append(' ').append(usuario.getContrasena()).toString();
            }
        };
    }
    
    public Converter<Long, Usuario> ApplicationConversionServiceFactoryBean.getIdToUsuarioConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, es.uca.iw.domain.Usuario>() {
            public es.uca.iw.domain.Usuario convert(java.lang.Long id) {
                return Usuario.findUsuario(id);
            }
        };
    }
    
    public Converter<String, Usuario> ApplicationConversionServiceFactoryBean.getStringToUsuarioConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, es.uca.iw.domain.Usuario>() {
            public es.uca.iw.domain.Usuario convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Usuario.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getEmpresaToStringConverter());
        registry.addConverter(getIdToEmpresaConverter());
        registry.addConverter(getStringToEmpresaConverter());
        registry.addConverter(getUsuarioToStringConverter());
        registry.addConverter(getIdToUsuarioConverter());
        registry.addConverter(getStringToUsuarioConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
