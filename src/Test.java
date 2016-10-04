import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by michalbaran on 28/09/16.
 */
public class Test {

    public static void main(String[] args){
        String s = "   \"lat\" : 52.24149,   ";

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);

        Double lat = Double.valueOf(m.group(1));

        System.out.println(lat);


    }
}
