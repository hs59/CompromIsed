import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 *  Similar to SingleSite, but for an email address instead of a website
 */
public class SingleAccount {
    public static void Connection(String name) {
        try {
            // New URL object with the URL set - next few lines are all using native java lib
            URL url = new URL("https://haveibeenpwned.com/api/v2/breachedaccount/" + name);
            // New HttpURLConnection object, opening the url
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Setting the User Agent - otherwise, Error 403 (See docs)
            conn.setRequestProperty("User-Agent","ComprimIsed");
            // Set as a GET method
            conn.setRequestMethod("GET");
            // Variable to hold the reponse code
            int responseCode = conn.getResponseCode();
            conn.connect();

            // Test reponseCode - continue if good
            if(responseCode != 200) {
                if(responseCode == 404) {
                    System.out.println("Not found - the account could not be found and has " +
                            "therefore not been pwned.");
                    System.exit(1);
                }
                else {
                    throw new RuntimeException("Failed: HTTP error code : "
                            + conn.getResponseCode());
                }
            }

            // Convert to a JSON object to print data
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) conn.getContent()));
            JsonObject rootobj = root.getAsJsonObject();

            // Do some stuff here

            // Disconect from connection
            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
