package com.devops.dxc.devops.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

    /**
     * Método para cacular el 10% del ahorro en la AFP.
     * 
     * """
     * ¿CUÁNTO SE PUEDE RETIRAR?
     * La Ley estableció un mínimo de entre 0 y 35 UF, y un máximo de 150 UF.
     * Es decir, entre $1 millón y $4.3 millones aproximadamente.
     * Para aquellos que tengan un saldo en su cuenta individual menor a $1 millón,
     * podrán hacer el retiro total de fondos en una cuota.
     * """
     * Fuente:
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @param sueldo
     * @return
     * @throws Exception
     */
    public static int getDxc(int ahorro, int sueldo) throws Exception {
        // TODO: ver condición de fecha inicio (jueves10 diciembre con un año para
        // retiro)
        if (((ahorro * 0.1) / getUf()) > 150) {
            return (int) (150 * getUf());
            // TODO: revisar condición para (ahorro*0.1)<=1000000 && ahorro >=1000000
            // en texto no sale claro que el retiro es de 1M
        } else if ((ahorro * 0.1) <= 1000000 && ahorro >= 1000000) {
            return (int) 1000000;
        } else if (ahorro <= 1000000) {
            return (int) ahorro;
        } else {
            return (int) (ahorro * 0.1);
        }
    }

    /**
     * Método para obtener el impuesto en el retiro del 10%
     * 
     * """
     * ¿DEBO PAGAR IMPUESTOS?
     * Sólo deberán hacerlo, aquellas personas que reciban ingresos iguales o
     * superiores
     * a $1.500.000 mensuales, y tendrán que cumplir con ese pago en la Operación
     * Renta del año entrante.
     * """
     * Fuente:
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     **/
    public static int getImpuesto(int sueldo, int dxc) {
        return sueldo >= 1_500_000 ? (int) (dxc * 0.19) : 0;
    }

    /**
     * Método para obtener saldo restante luego de retirar el 10%
     */
    public static int getSaldo(int ahorro, int dxc, int impuesto) {
        return ahorro - dxc - impuesto;
    }

    /**
     * Método que retorna el valor de la UF. Este método debe ser refactorizado por
     * una integración a un servicio
     * que retorne la UF en tiempo real. Por ejemplo mindicador.cl
     * 
     * @return
     * @throws Exception
     */
    public static int getUf() throws Exception {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        String jsonUf = peticionHttpGet("https://mindicador.cl/api/uf/" + formatter.format(date));

        ObjectMapper objectMapper = new ObjectMapper();
        // UF valor = objectMapper.readValue(jsonUf, UF.class);
        JsonNode jsonNode = objectMapper.readTree(jsonUf);
        return jsonNode.get("serie").get(0).get("valor").asInt();
    }

    public static String peticionHttpGet(String urlParaVisitar) throws Exception {
        // Esto es lo que vamos a devolver
        StringBuilder resultado = new StringBuilder();
        // Crear un objeto de tipo URL
        URL url = new URL(urlParaVisitar);

        // Abrir la conexión e indicar que será de tipo GET
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        // Búferes para leer
        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        // Cerrar el BufferedReader
        rd.close();
        return resultado.toString();
    }

}
