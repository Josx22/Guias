
package sv.org.arrupe.util;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchTelephone {
    public static void main(String[] args){
        compareTelephone("8328-3939");
    }
    public static boolean compareTelephone(String telefono){
        String expresion="(2|7)\\d{3}-\\d{4}";
        Pattern pat = Pattern.compile(expresion);
        Matcher mat = pat.matcher(telefono);
        if(mat.matches()){
            System.out.println("SI");
            return TRUE;
        }
        return FALSE;
    }
}
