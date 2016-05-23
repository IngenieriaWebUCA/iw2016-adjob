package es.uca.iw.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import es.uca.iw.domain.Usuario;
import es.uca.iw.reference.TipoUsuario;

public class AdjobAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		String password = (String) authentication.getCredentials();
		if (!StringUtils.hasText(password)) {
		    throw new BadCredentialsException("Please enter password");
		}
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        try {
            Usuario usuario = Usuario.findUsuariosByEmailAndContrasenaEquals(username, password).getSingleResult();
		    //  Demandante, GestorETT, GestorEmpresa, Administrador, SuperAdministrador
            //System.out.println("A ver qué sale..." + usuario.getTipo());
            switch(usuario.getTipo()){
	            case Demandante: 
	            	authorities.add(new SimpleGrantedAuthority("DEMANDANTE"));
	            	//System.out.println("Identificado como DEMANDANTE");
	            	break;
				case Administrador:
					authorities.add(new SimpleGrantedAuthority("ADMINISTRADOR"));
					break;
				case GestorETT:
					authorities.add(new SimpleGrantedAuthority("GESTORETT"));
					break;
				case GestorEmpresa:
					authorities.add(new SimpleGrantedAuthority("GESTOREMPRESA"));
					break;
				case SuperAdministrador:
					authorities.add(new SimpleGrantedAuthority("SUPERADMINISTRADOR"));
					break;
				default:
					break;
	       }
            
            //authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} catch (EmptyResultDataAccessException e) {
		    throw new BadCredentialsException("Invalid username or password");
		} catch (EntityNotFoundException e) {
		    throw new BadCredentialsException("Invalid user");
		} catch (NonUniqueResultException e) {
		    throw new BadCredentialsException("Non-unique user, contact administrator");
		}
		return new User(username, password, true, // enabled
			true, // account not expired
			true, // credentials not expired
			true, // account not locked
		authorities);
    }
	

}
