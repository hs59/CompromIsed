public class URLs {
    private String singleAccountURL = "https://haveibeenpwned.com/api/v2/breachedaccount/";
    private String singleDomainURL = "https://haveibeenpwned.com/api/v2/breach/";
    private String systemBreachURL = "https://haveibeenpwned.com/api/v2/breaches";
    private String systemDataURL = "https://haveibeenpwned.com/api/v2/dataclasses";

    // Constructor
    public URLs() {
        setSingleAccountURL(singleAccountURL);
        setSingleDomainURL(singleDomainURL);
        setSystemBreachURL(systemBreachURL);
        setSystemDataURL(systemDataURL);
    }

    public String getSingleAccountURL() {
        return singleAccountURL;
    }

    public void setSingleAccountURL(String singleAccountURL) {
        this.singleAccountURL= singleAccountURL;
    }

    public String getSingleDomainURL() {
        return singleDomainURL;
    }

    public void setSingleDomainURL(String singleDomainURL) {
        this.singleDomainURL = singleDomainURL;
    }

    public String getSystemBreachURL() {
        return systemBreachURL;
    }

    public void setSystemBreachURL(String systemBreachURL) {
        this.systemBreachURL = systemBreachURL;
    }

    public String getSystemDataURL() {
        return systemDataURL;
    }

    public void setSystemDataURL(String systemDataURL) {
        this.systemDataURL = systemDataURL;
    }
}
