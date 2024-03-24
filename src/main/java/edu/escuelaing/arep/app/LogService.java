package edu.escuelaing.arep.app;

import static spark.Spark.*;

import java.util.Date;

import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;


/**
 * Clase que representa un servicio de registro de logs.
 */
public class LogService {


    /**
     * Método principal que inicia el servicio de registro de logs.
     * @param args argumentos de la línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/logservice", (req, res) -> {
            String val = req.queryParams("message");
            return logMessage(val);
        });

    }

    /**
     * Registra un mensaje en la base de datos y devuelve los últimos registros.
     * @param val el mensaje a registrar
     * @return una cadena JSON con los últimos registros
     */
    private static String logMessage(String val) {
        try (MongoClient client = new MongoClient("mongo-db", 27017)) {
            MongoDatabase database = client.getDatabase("logDB");
            MongoCollection<Document> collection = database.getCollection("log");
    
            Document doc = new Document("message", val)
                .append("timestamp", new Date());
            collection.insertOne(doc);
    
            FindIterable<Document> cursor = collection.find()
                .sort(Sorts.descending("timestamp"))
                .limit(10);
    
            StringBuilder htmlTable = new StringBuilder();
            htmlTable.append("<table border='1'>");
            htmlTable.append("<tr><th>Message</th><th>Timestamp</th></tr>");
    
            for (Document document : cursor) {
                String message = document.getString("message");
                Date timestamp = document.getDate("timestamp");
    
                htmlTable.append("<tr>");
                htmlTable.append("<td>").append(message).append("</td>");
                htmlTable.append("<td>").append(timestamp).append("</td>");
                htmlTable.append("</tr>");
            }
    
            htmlTable.append("</table>");
            return htmlTable.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al registrar el log en MongoDB";
        }
    }
    

    /**
     * Obtiene el puerto en el que se ejecutará el servidor web.
     * @return el puerto del servidor web
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}