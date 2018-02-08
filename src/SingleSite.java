
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import com.google.gson.*;
import org.json.simple.JSONObject;

/**     Getting data from a single breached site.
 *
 *      Format: GET https://haveibeenpwned.com/api/v2/breach/{name}
 */

public class SingleSite {
    // JSON properties to be converted to Java
    private String Title;
    private String Name;
    private String Domain;
    private String BreachDate;
    private String AddedDate;
    private String PwnCount;
    private String Description;
    private boolean IsVerified;
    private boolean IsFabricated;
    private boolean IsSensitive;
    private boolean IsActive;
    private boolean IsRetired;
    private boolean IsSpamList;
    private JsonObject Object;

    public static void Connection(String name) {
        try {
            // New URL object with the URL set - next few lines are all using native java lib
            URL url = new URL("https://haveibeenpwned.com/api/v2/breach/" + name);
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
                    System.out.println("Not found - the domain could not be found and has " +
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
            
            String title = rootobj.get("Title").getAsString();
            String domain = rootobj.get("Domain").getAsString();
            String breachDate = rootobj.get("BreachDate").getAsString();
            String pwnCount = rootobj.get("PwnCount").getAsString();
            Boolean isVerified = rootobj.get("IsVerified").getAsBoolean();

            formatStrings(title, domain, breachDate, pwnCount, isVerified);

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

    public static void formatStrings(String title, String domain, String breachDate,
                                        String pwnCount, Boolean isVerified) {
        String answer = "";
        if(isVerified == true) {
            answer = "Yes, it is verified.";
        }
        else {
            answer = "No, it is not verified.";
        }

        System.out.println("- Title: " + title);
        System.out.println("- Domain: " + domain);
        System.out.println("- When was it breached?: " + breachDate);
        System.out.println("- Pwn Count: " + pwnCount);
        System.out.println("- Is it verified?: " + answer);
    }
}
