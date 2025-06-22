package salud.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorCampos {
	
	// Email
	
	private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean validarEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    
    // Dni
    
    private static final String DNI_REGEX = "^[0-9]{8}[A-Z]$";
    private static final Pattern DNI_PATTERN = Pattern.compile(DNI_REGEX);
    
    public static boolean validarDni(String dni) {
        if (dni == null) {
            return false;
        }
        Matcher matcher = DNI_PATTERN.matcher(dni);
        return matcher.matches();
    }
    
    // Nss
    
    private static final String NSS_REGEX = "^[0-9]{12}$";
    private static final Pattern NSS_PATTERN = Pattern.compile(NSS_REGEX);
    
    public static boolean validarNss(String nss) {
        if (nss == null) {
            return false;
        }
        Matcher matcher = NSS_PATTERN.matcher(nss);
        return matcher.matches();
    }
    
    // Ncol
    
    private static final String NCOL_REGEX = "^[0-9]{9}$";
    private static final Pattern NCOL_PATTERN = Pattern.compile(NCOL_REGEX);
    
    public static boolean validarNcol(String ncol) {
        if (ncol == null) {
            return false;
        }
        Matcher matcher = NCOL_PATTERN.matcher(ncol);
        return matcher.matches();
    }
}
