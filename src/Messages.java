// Class designed to output messages to user
public class Messages {
    public static void singleSite1() {
        System.out.println("\nBelow is the information on your domain: ");
    }

    public static void Welcome() {
        System.out.println("Welcome to ComprimIsed.\n" +
                "Would you like information on a domain/URL or an email address?\n" +
                "1: Domain\n" + "2: Email");
    }

    public static void Domain() {
        System.out.println("What's the name of the site you would like to check?\n" +
                "For example, Adobe.com's name would just be 'Adobe'.\n\n" +
                "Enter it now: ");
    }

    public static void Email() {
        System.out.println("What email address would you like to check?\n\n" +
                "Enter it now: ");
    }

}
