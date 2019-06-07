package com.qualitysolutions.fresh_and_clean_web_app.servicios;

import com.qualitysolutions.fresh_and_clean_web_app.dao.IEmpleadoDao;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class JpaUsuarioServicio implements UserDetailsService {

    @Autowired
    private IEmpleadoDao empleadoDao;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Empleado empleado = empleadoDao.findUsuarioByUsernameAndEstaActivo(nombreUsuario);
        switch (empleado.getTipoEmpleado().getIdTipo()) {
            case 1:
                authorities.add(new SimpleGrantedAuthority("ROLE_BARBERO"));
                return new User(empleado.getIdEmpleado().toString(), empleado.getPasswordEmpleado(), true, true, true, true, authorities);
            case 2:
                authorities.add(new SimpleGrantedAuthority("ROLE_GERENTE"));
                return new User(empleado.getIdEmpleado().toString(), empleado.getPasswordEmpleado(), true, true, true, true, authorities);
            case 3:
                authorities.add(new SimpleGrantedAuthority("ROLE_INVENTARIO"));
                return new User(empleado.getIdEmpleado().toString(), empleado.getPasswordEmpleado(), true, true, true, true, authorities);
                default:
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
                    return new User(empleado.getIdEmpleado().toString(), empleado.getPasswordEmpleado(), true, true, true, true, authorities);
        }

    }
}
