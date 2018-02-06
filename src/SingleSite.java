
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

    public static void singleSite(String name) {
        try {
            // New URL object with the URL set - next few lines are all using native java lib
            URL url = new URL("https://haveibeenpwned.com/api/v2/breach/" + name);
            // New HttpURLConnection object, opening the url
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Setting the User Agent - otherwise, Error 403 (See docs)
            conn.setRequestProperty("User-Agent","Pwnage-Checker");
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
            String title = rootobj.get("Title").getAsString();

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

            // Disconect from connection
            conn.disconnect();
            System.out.println(title);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Getters and setters for variables
     */
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDomain() {
        return Domain;
    }

    public void setDomain(String domain) {
        Domain = domain;
    }

    public String getBreachDate() {
        return BreachDate;
    }

    public void setBreachDate(String breachDate) {
        BreachDate = breachDate;
    }

    public String getAddedDate() {
        return AddedDate;
    }

    public void setAddedDate(String addedDate) {
        AddedDate = addedDate;
    }

    public String getPwnCount() {
        return PwnCount;
    }

    public void setPwnCount(String pwnCount) {
        PwnCount = pwnCount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isVerified() {
        return IsVerified;
    }

    public void setVerified(boolean verified) {
        IsVerified = verified;
    }

    public boolean isFabricated() {
        return IsFabricated;
    }

    public void setFabricated(boolean fabricated) {
        IsFabricated = fabricated;
    }

    public boolean isSensitive() {
        return IsSensitive;
    }

    public void setSensitive(boolean sensitive) {
        IsSensitive = sensitive;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public boolean isRetired() {
        return IsRetired;
    }

    public void setRetired(boolean retired) {
        IsRetired = retired;
    }

    public boolean isSpamList() {
        return IsSpamList;
    }

    public void setSpamList(boolean spamList) {
        IsSpamList = spamList;
    }
}
