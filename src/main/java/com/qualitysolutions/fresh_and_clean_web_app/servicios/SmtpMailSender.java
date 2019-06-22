package com.qualitysolutions.fresh_and_clean_web_app.servicios;

import com.qualitysolutions.fresh_and_clean_web_app.modelos.PeticionHora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;
    public void send(String to, String subject, String body) throws MessagingException
    {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, multipart, "utf-8");
        helper.setFrom("freshandcleanbarber@gmail.com");
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body,true);
        javaMailSender.send(mensaje);
    }

    public String bodyRechazado(PeticionHora peticionHora,String motivo,String fechaHora,String horaAtencion)
    {
        String body = "<div>\n" +
                "<div style=\"float: right;\">\n" +
                "<img src=\"https://i.ibb.co/1Jk53Hs/logo-negro.png\" style=\"width:30%; float: right;\">\n" +
                "</div>\n" +
                "\n" +
                "<div style=\"float:left;\">\n" +
                "<h3 style=\"font-family: Arial, Helvetica, sans-serif; margin-top: 0; margin-bottom: 0.5rem;\">Solicitud de\n" +
                "hora</h3>\n" +
                "<div style=\"font-family: Arial, Helvetica, sans-serif;\">\n" +
                "<p>Su solicitud de hora dentro de la barberia Fresh & Clean ha sido rechazada</p>\n" +
                "<p>El motivo de rechazo de esta solicitud es: "+motivo+"</p>\n" +
                "<p>Si necesita informacion, no dude en contactarnos al número:\n" +
                "+569 9123 4567</p>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<h3 style=\"margin-top: 0; margin-bottom: 0.5rem;font-family: Arial, Helvetica, sans-serif;\">Informacion\n" +
                "sobre la hora</h3>\n" +
                "</div>\n" +
                "<table\n" +
                "style=\"font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; max-width: 100%; margin-bottom: 1rem; background-color: transparent; padding: 0.75rem; vertical-align: top; background-color: #fff; border: 1px solid #dee2e6 !important; border-bottom-width: 2px;\">\n" +
                "<tr>\n" +
                "<td\n" +
                "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Nombre</td>\n" +
                "<td\n" +
                "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getCliente().getPersona().getNombre()+"\n" +
                ""+peticionHora.getCliente().getPersona().getApellido()+"</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td\n" +
                "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Email</td>\n" +
                "<td\n" +
                "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getCliente().getEmailCliente()+"</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td\n" +
                "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Telefono</td>\n" +
                "<td\n" +
                "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getCliente().getTelefonoCliente()+"</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td\n" +
                "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Barbero</td>\n" +
                "<td\n" +
                "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getEmpleado().getPersona().getNombre()+" "+peticionHora.getEmpleado().getPersona().getApellido()+"</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td\n" +
                "style=\"border: 1px solid #dee2e6; border-bottom: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top;\">Servicios</td>\n" +
                peticionHora.obtenerServiciosCorreo(peticionHora)+
                "</tr>\n" +
                "\n" +
                "<tr>\n" +
                "<td\n" +
                "style=\"border: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">Fecha\n" +
                "atencion</td>\n" +
                "<td\n" +
                "style=\"border: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">"+fechaHora+"</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td\n" +
                "style=\"border: 1px solid #ffd24d; border-top: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">Hora\n" +
                "de atencion</td>\n" +
                "<td\n" +
                "style=\"border: 1px solid #ffd24d; border-top: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">"+horaAtencion+"\n" +
                "am</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td\n" +
                "style=\"border: 1px solid #ff1a1a; border-top: 1px solid #ff1a1a; padding: 0.75rem; vertical-align: top; background-color: #ff8080;\">Estado\n" +
                "de Hora</td>\n" +
                "<td\n" +
                "style=\"border: 1px solid #ff1a1a; border-top: 1px solid #ff1a1a; padding: 0.75rem; vertical-align: top; background-color: #ff8080;\">"+peticionHora.getEstado()+"</td>\n" +
                "</tr>\n" +
                "<tfoot>\n" +
                "<tr>\n" +
                "<th colspan=\"2\" style=\"padding: 0.9rem;\">\n" +
                "<button\n" +
                "style=\"display: inline-block; font-weight: 400; cursor: pointer; text-align: center; white-space: nowrap; vertical-align: middle; -webkit-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; border: 1px solid transparent; padding: 0.375rem 0.75rem; font-size: 1rem; line-height: 1.5; border-radius: 0.25rem; transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; color: #fff; background-color: #6c757d; border-color: #6c757d; float: right;\">Para\n" +
                "mas informacion, visite nuestro sitio web</button>\n" +
                "</th>\n" +
                "</tr>\n" +
                "</tfoot>\n" +
                "</table>\n" +
                "\n" +
                "</div>";
        return body;
    }

    public String bodyAceptado(PeticionHora peticionHora,String fechaHora, String horaAtencion)
    {
        String body = "<div style=\"float: right;\">\n" +
            "<img src='https://i.ibb.co/1Jk53Hs/logo-negro.png' style=\"width:30%; float: right;\">\n" +
            "</div>\n" +
            "\n" +
            "<div style=\"float:left;\">\n" +
            "<h3 style=\"font-family: Arial, Helvetica, sans-serif; margin-top: 0; margin-bottom: 0.5rem;\">Solicitud de\n" +
            "hora</h3>\n" +
            "<div style=\"font-family: Arial, Helvetica, sans-serif;\">\n" +
            "<p>¡Su solicitud de hora dentro de la barberia Fresh & Clean se ha\n" +
            "aceptado exitosamente!</p>\n" +
            "<p>Si necesita informacion, no dude en contactarnos al número:\n" +
            "+569 9123 4567</p>\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "\n" +
            "<h3 style=\"margin-top: 0; margin-bottom: 0.5rem;font-family: Arial, Helvetica, sans-serif;\">Informacion\n" +
            "sobre la hora</h3>\n" +
            "</div>\n" +
            "<table\n" +
            "style=\"font-family: Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; max-width: 100%; margin-bottom: 1rem; background-color: transparent; padding: 0.75rem; vertical-align: top; background-color: #fff; border: 1px solid #dee2e6 !important; border-bottom-width: 2px;\">\n" +
            "<tr>\n" +
            "<td\n" +
            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Nombre</td>\n" +
            "<td\n" +
            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getCliente().getPersona().getNombre()+"\n" +
            ""+peticionHora.getCliente().getPersona().getApellido()+"</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td\n" +
            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Email</td>\n" +
            "<td\n" +
            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getCliente().getEmailCliente()+"</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td\n" +
            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Telefono</td>\n" +
            "<td\n" +
            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getCliente().getTelefonoCliente()+"</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td\n" +
            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">Barbero</td>\n" +
            "<td\n" +
            "style=\"border: 1px solid #dee2e6; padding: 0.75rem; vertical-align: top;\">"+peticionHora.getEmpleado().getPersona().getNombre()+"\n" +
            ""+peticionHora.getEmpleado().getPersona().getApellido()+"</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td\n" +
            "style=\"border: 1px solid #dee2e6; border-bottom: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top;\">Servicios</td>\n" +
            peticionHora.obtenerServiciosCorreo(peticionHora)+
            "</tr>\n" +
            "\n" +
            "<tr>\n" +
            "<td\n" +
            "style=\"border: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">Fecha\n" +
            "atencion</td>\n" +
            "<td\n" +
            "style=\"border: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">"+fechaHora+"</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td\n" +
            "style=\"border: 1px solid #ffd24d; border-top: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">Hora\n" +
            "de atencion</td>\n" +
            "<td\n" +
            "style=\"border: 1px solid #ffd24d; border-top: 1px solid #ffd24d; padding: 0.75rem; vertical-align: top; background-color: #ffeeba;\">"+horaAtencion+"\n" +
            "am</td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "<td\n" +
            "style=\"border: 1px solid #5dbb73; border-top: 1px solid #5dbb73; padding: 0.75rem; vertical-align: top; background-color: #c3e6cb;\">Estado\n" +
            "de Hora</td>\n" +
            "<td\n" +
            "style=\"border: 1px solid #5dbb73; border-top: 1px solid #5dbb73; padding: 0.75rem; vertical-align: top; background-color: #c3e6cb;\">Aprobada</td>\n" +
            "</tr>\n" +
            "<tfoot>\n" +
            "<tr>\n" +
            "<th colspan=\"2\" style=\"padding: 0.9rem;\">\n" +
            "<button\n" +
            "style=\"display: inline-block; font-weight: 400; cursor: pointer; text-align: center; white-space: nowrap; vertical-align: middle; -webkit-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; border: 1px solid transparent; padding: 0.375rem 0.75rem; font-size: 1rem; line-height: 1.5; border-radius: 0.25rem; transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; color: #fff; background-color: #6c757d; border-color: #6c757d; float: right;\">Para\n" +
            "mas informacion, visite nuestro sitio web</button>\n" +
            "</th>\n" +
            "</tr>\n" +
            "</tfoot>\n" +
            "</table>";
        return body;
    }


}
