package vimeoextractor;

class VimeoUtils {
    static boolean isDigitsOnly(CharSequence str) {
        final int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }
}
