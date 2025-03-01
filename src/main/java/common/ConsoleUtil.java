package common;

public class ConsoleUtil {
    private final ParseService parseService;

    public ConsoleUtil(ParseService parseService) {
        this.parseService = parseService;
    }

    public void waitAndCls() {
        System.out.println("\nPress any key to continue.");
        parseService.scanner.nextLine();
        System.out.print("\n".repeat(10));
    }
}
