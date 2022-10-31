package com.fabao.operadora.saude.utils;

        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class DataUtil {

    public static String converterDateToString(Date data, String formato) {

        String dataRetornada = null;
        DateFormat df = new SimpleDateFormat(formato);
        dataRetornada = (String) df.format(data);
        return dataRetornada;
    }
}
