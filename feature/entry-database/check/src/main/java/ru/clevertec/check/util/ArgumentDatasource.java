package ru.clevertec.check.util;

public class ArgumentDatasource {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    public static void create(String[] args) {
        URL = ParsingArgs.parseArgsToDatasourceURl(args);
        USER = ParsingArgs.parseArgsToDatasourceUsername(args);
        PASSWORD = ParsingArgs.parseArgsToDatasourcePassword(args);
    }

    public static String getURL() {
        return URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
