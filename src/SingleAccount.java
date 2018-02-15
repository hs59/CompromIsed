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
    private List<Boolean> verifiedList;

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

            // JsonParser object to get the JSON and conver it to a JSON Array
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) conn.getContent()));
            JsonArray rootobj = root.getAsJsonArray();

            // Processing the titles, domains, and breach dates
            SingleAccount helper = new SingleAccount();
            helper.titleList(rootobj);
            helper.domainList(rootobj);
            helper.breachDateList(rootobj);
            helper.verified(rootobj);

            // Get the size of the breachdatelist. This will tell us how many times the
            // email has been breached
            int size = helper.getBreachDateList().size();

            // Get the list of titles, but format them to remove the [ ]
            String titles = helper.getTitleList().toString()
                    .replace("[", "")
                    .replace("]","");

            // Get the list of breach dates, but format them to remove the [ ]
            String breaches = helper.getBreachDateList().toString()
                    .replace("[", "")
                    .replace("]","");

            // SingleAccount object to format everything nicely
            SingleAccount printResults = new SingleAccount();
            printResults.formatStrings(size,titles,helper.getVerifiedList());

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

    // Process the JsonArray and get all the Titles from it
    public void titleList(JsonArray rootobj) {
        List<String> title = new ArrayList<>();
        titleList = title;
        // Loop through the array to get each element that matches the get("String")
        // and add to the ArrayList
        for (int i = 0; i < rootobj.size() ; i++) {
            JsonObject propertiesJson = (JsonObject) rootobj.get(i);
            String value = propertiesJson.get("Title").getAsString();
            title.add(value);
        }
    }

    // Process the JsonArray and get all the Domains from it
    public void domainList(JsonArray rootobj) {
        List<String> domain = new ArrayList<>();
        domainList = domain;
        // Loop through the array to get each element that matches the get("String")
        // and add to the ArrayList
        for (int i = 0; i < rootobj.size() ; i++) {
            JsonObject propertiesJson = (JsonObject) rootobj.get(i);
            String value = propertiesJson.get("Domain").getAsString();
            domain.add(value);
        }
    }

    // Process the JsonArray and get all the Breach Dates from it
    public void breachDateList(JsonArray rootobj) {
        List<String> breach = new ArrayList<>();
        breachDateList = breach;
        // Loop through the array to get each element that matches the get("String")
        // and add to the ArrayList
        for (int i = 0; i < rootobj.size() ; i++) {
            JsonObject propertiesJson = (JsonObject) rootobj.get(i);
            String value = propertiesJson.get("BreachDate").getAsString();
            breach.add(value);
        }
    }

    // Process the JsonArray and get the boolean of IsVerified from each
    public void verified(JsonArray rootobj) {
        List<Boolean> verify = new ArrayList<>();
        verifiedList = verify;
        // Loop through the array to get each element that matches the get("String")
        // and add to the ArrayList
        for (int i = 0; i < rootobj.size(); i++) {
            JsonObject propertiesJson = (JsonObject) rootobj.get(i);
            boolean value = propertiesJson.get("IsVerified").getAsBoolean();
            verify.add(value);
        }
    }

    // Make all of this info look nice
    public void formatStrings(int breaches, String titles, List<Boolean> verified) {
        System.out.println("- Number of breaches: " + breaches);
        System.out.println("- Websites: " + titles);
        System.out.println("- Are they verified?: " + verified);
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

    public List<Boolean> getVerifiedList() { return verifiedList; }
}
