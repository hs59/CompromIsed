import java.util.Scanner;

public class Conditionals {

    private String domain;
    private String email;

    public static void WelcomeDecision() {
        Messages msg = new Messages();

        Scanner input = new Scanner(System.in);
        int ans = input.nextInt();

        SingleAccount acc = new SingleAccount();
        SingleSite site = new SingleSite();

        if(ans == 1) {
            msg.Domain();
            String dom = input.nextLine();
            site.Connection(dom);
        }
        else if (ans == 2) {
            msg.Email();
            String em = input.nextLine();
            acc.Connection(em);
        }
        else {
            while (ans > 2 || ans < 1) {
                System.out.println("\n" + ans + " is not a valid choice. Please try again.\n");
                msg.Welcome();
                ans = input.nextInt();
            }
        }

    }
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
