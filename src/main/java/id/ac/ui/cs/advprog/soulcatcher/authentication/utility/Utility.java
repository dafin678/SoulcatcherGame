package id.ac.ui.cs.advprog.soulcatcher.authentication.utility;

import javax.servlet.http.HttpServletRequest;

public class Utility {

    private Utility() {
        throw new IllegalStateException("Utility class");
    }

    public static String getSiteURL(HttpServletRequest request){
            var siteURL = (request).getRequestURL().toString();
            return siteURL.replace((request).getServletPath(),"");
    }
}
