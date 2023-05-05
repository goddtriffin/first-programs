public class MyUtils {

    public static boolean isNumeric (String str) {
        if (str == null) {
            return false;
        }

        if (str.equals("")) {
            return false;
        }

        if (str.indexOf("-") <= 0 && str.chars().filter(x -> x == '-').count() <= 1) { //hyphen '-' either doesn't exist, or is at the beginning of the number (and only occurs once)
            if (str.chars().filter(x -> x == '.').count() <= 1) { //period '.' occurs only once

                int allowed = 0;

                for (int i=0; i<str.length(); i++) { //cycles through all of the chars in str
                    if (((int)str.charAt(i) >= 48 && (int)str.charAt(i) <= 57) || (int)str.charAt(i) == 45 || (int)str.charAt(i) == 46) { //char in str is either a number, '.' period , or '-' hyphen
                        allowed++;
                    }
                }

                if (allowed == str.length()) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String formatStr (String str) {
        if (str.length() < 2) {
            return str.toUpperCase();
        }

        return str.toUpperCase().charAt(0) + str.toLowerCase().substring(1);
    }
}
