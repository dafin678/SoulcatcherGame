package id.ac.ui.cs.advprog.soulcatcher.utility;

import id.ac.ui.cs.advprog.soulcatcher.payload.ForgotPasswordRequest;
import id.ac.ui.cs.advprog.soulcatcher.payload.RegisterRequest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public class Utility {
    public static String getSiteURL(@Valid @RequestBody ForgotPasswordRequest request){
            String siteURL = ((HttpServletRequest)request).getRequestURL().toString();
            return siteURL.replace(((HttpServletRequest)request).getServletPath(),"");
    }
}
