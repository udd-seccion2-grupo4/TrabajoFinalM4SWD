package com.devops.dxc.devops.model;

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
     * @param impuesto
     * @return
     * @throws Exception
     */
    public static int getDxc(int ahorro, int sueldo, int uf) {
        if (((ahorro * 0.1) / uf) > 150) {
            return (int) (150 * uf);
            // en texto no sale claro que el retiro es de 1M
        } else if ((ahorro * 0.1) <= 35 * uf && ahorro >= 35 * uf) {
            return (int) 35 * uf;
        } else if (ahorro <= 35 * uf) {
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
        return sueldo >= 1500000 ? (int) (dxc * 0.19) : 0;
    }

    /**
     * Método para obtener saldo restante luego de retirar el 10%
     */
    public static int getSaldo(int ahorro, int dxc, int impuesto) {
        return ahorro - dxc - impuesto;
    }
}
