package edu.escuelaing.arep.app;

import static spark.Spark.*;

/**
 * Clase principal que inicia el servicio Round Robin.
 */
public class RoundRobin 
{
    /**
     * Método principal que inicia el servicio Round Robin.
     * @param args argumentos de la línea de comandos (no utilizados)
     */
    public static void main( String[] args )
    {
        port(getPort());
        staticFiles.location("/public");
        get("hello", (req,res) -> "Hello Docker!");
        get("/log",(req,res) -> {
            String val = req.queryParams("value");
            return HTTPConection.getLogs(val);
        });
    }
    /**
     * Obtiene el puerto en el que se ejecutará el servidor web.
     * @return el puerto del servidor web
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }
}