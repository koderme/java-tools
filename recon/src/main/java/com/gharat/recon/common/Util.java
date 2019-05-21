package com.gharat.recon.common;

/**
 *
 */
public class Util {

    /**
     * Checks if the specified dataStr in Numeric.
     *
     * @param dataStr String to be checked.
     * @return true if its numeric, false otherwise.
     */
    public static boolean isNumeric(String dataStr) {
        try {
            return dataStr.matches("[+-]?\\d*(\\.\\d+)?");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the specified dataStr is integer.
     *
     * @param dataStr String to be checked.
     * @return true if its integer, false otherwise.
     */
    public static boolean isInteger(String dataStr) {
        try {
            return isNumeric(dataStr) && dataStr.indexOf('.') < 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the specified dataStr is date.
     *
     * @param dataStr String to be checked.
     * @param format  String representing the format.
     * @return true if its date, false otherwise.
     */
    public static boolean isDate(String dataStr, String format) {
        try {
            return StringConvertor.toDate.convert(dataStr, format) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the class that is closest match for holding specified data.
     *
     * @param dataStr String that contains data.
     * @return Class
     */
    public static Class getType(String dataStr) {
        if (isNumeric(dataStr))
            return Double.class;
        else if (isInteger(dataStr))
            return Integer.class;
        else
            return String.class;
    }

    /**
     * Converts dataStr to appropriate object.
     *
     * @param dataStr String to be converted.
     * @return Returns the converted value.
     */
    public static Object convert(String dataStr) {
        if (isNumeric(dataStr))
            return StringConvertor.toDouble.convert(dataStr);
        else if (isInteger(dataStr))
            return StringConvertor.toInteger.convert(dataStr);
        else if (isDate(dataStr, ReconConstants.DEFAULT_DATE_FORMAT))
            return StringConvertor.toDate.convert(dataStr, ReconConstants.DEFAULT_DATE_FORMAT);
        else
            return dataStr;

    }
}
