import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**  From API Docs:
        "A 'breach' is an instance of a system having been compromised by an
        attacker and the data disclosed. For example, Adobe was a breach, Gawker
        was a breach etc. It is possible to return the details of each of breach
        in the system which currently stands at 265 breaches."
 */

public class SystemBreaches {
    public static void SysBreaches() {
        try {
            // New URL object with the URL set
            URL url = new URL("https://haveibeenpwned.com/api/v2/breaches");
            // New HttpURLConnection object, opening the url
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Setting the User Agent - otherwise, Error 403 (See docs)
            conn.setRequestProperty("User-Agent","Pwnage-Checker");
            // Set as a GET method
            conn.setRequestMethod("GET");
            // Variable to hold the reponse code
            int responseCode = conn.getResponseCode();

            // Test reponseCode - continue if good
            if(responseCode != 200) {
                throw new RuntimeException("Failed: HTTP error code : "
                        + conn.getResponseCode());
            }
            // BufferedReader object w/ new InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            // Variable to hold the output from server
            String output;
            // Output..
            System.out.println("Output from Server.. \n");
            // Print..
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            // Disconect
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
