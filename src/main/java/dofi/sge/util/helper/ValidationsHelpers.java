package dofi.sge.util.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationsHelpers {
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
