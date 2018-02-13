import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *  Similar to SingleSite, but for an email address instead of a website
 */
public class SingleAccount {

    private List<String> titleList;
    private List<String> domainList;
    private List<String> breachDateList;

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

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) conn.getContent()));
            JsonArray rootobj = root.getAsJsonArray();

            SingleAccount helper = new SingleAccount();
            helper.titleList(rootobj);
            helper.domainList(rootobj);
            helper.breachDateList(rootobj);

            System.out.println(helper.getBreachDateList());



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

    public void titleList(JsonArray rootobj) {
        List<String> title = new ArrayList<>();
        titleList = title;
        for (int i = 0; i < rootobj.size() ; i++) {
            JsonObject propertiesJson = (JsonObject) rootobj.get(i);
            String value = propertiesJson.get("Title").getAsString();
            title.add(value);
        }
    }

    public void domainList(JsonArray rootobj) {
        List<String> domain = new ArrayList<>();
        domainList = domain;
        for (int i = 0; i < rootobj.size() ; i++) {
            JsonObject propertiesJson = (JsonObject) rootobj.get(i);
            String value = propertiesJson.get("Domain").getAsString();
            domain.add(value);
        }
    }

    public void breachDateList(JsonArray rootobj) {
        List<String> breach = new ArrayList<>();
        breachDateList = breach;
        for (int i = 0; i < rootobj.size() ; i++) {
            JsonObject propertiesJson = (JsonObject) rootobj.get(i);
            String value = propertiesJson.get("BreachDate").getAsString();
            breach.add(value);
        }
    }

    public static void formatStrings(String title, String domain, String breachDate,
                                     String pwnCount, Boolean isVerified) {
        String answer = " ";
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

    public List<String> getTitleList() {
        return titleList;
    }

    public List<String> getDomainList() {
        return domainList;
    }

    public List<String> getBreachDateList() {
        return breachDateList;
    }
}
