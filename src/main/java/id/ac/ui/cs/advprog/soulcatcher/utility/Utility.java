package id.ac.ui.cs.advprog.soulcatcher.utility;

import id.ac.ui.cs.advprog.soulcatcher.payload.ForgotPasswordRequest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static String getSiteURL(@RequestBody ForgotPasswordRequest request){
        if(request instanceof HttpServletRequest){
            String siteURL = ((HttpServletRequest)request).getRequestURL().toString();
            return siteURL.replace(((HttpServletRequest)request).getServletPath(),"");
        }
        return null;
    }
}
