package space.personal.youssefalmkari.databindingrecyclerview.utils;

public class BindingUtils {

    /**
     * Converts number to K, M suffix
     * @param number number to convert
     * @return number with converted suffix
     */
    public static String convertToSuffix (Long number) {
        if (number < 1000) return "" + number;

        int exp = (int) ((Math.log(number) / Math.log(1000)));
        String format = "%.1f%c";

        return String.format(format, number / Math.pow(1000, exp), "kmgtpe".charAt(exp - 1));

    }
}
