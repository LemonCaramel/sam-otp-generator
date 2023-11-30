import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.TimeZone;

public final class OtpGenerator {

    private OtpGenerator() { throw new UnsupportedOperationException(); }
    private static final DecimalFormat FORMAT = new DecimalFormat("00");

    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the key: ");
        final String input = scanner.next();
        if (input.isBlank()) {
            System.out.println("Please enter the correct Key. ᓀ‸ᓂ");
            return;
        }

        System.out.println("Here is the generated OTP code for you.");
        for (int count = 2; count >= -1; count--) {
            final String dateKey = OtpGenerator.strangeDate(count);
            final int key = OtpGenerator.makeHash(input + dateKey);
            System.out.println("- (" + key + ")");
        }
    }

    private static String strangeDate(final int min) {
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.add(Calendar.MINUTE, -min);

        return FORMAT.format(calendar.get(Calendar.YEAR) - 2000) +
               FORMAT.format(calendar.get(Calendar.MONTH) + 1) +
               FORMAT.format(calendar.get(Calendar.MINUTE)) +
               FORMAT.format(calendar.get(Calendar.DATE)) +
               FORMAT.format(calendar.get(Calendar.HOUR_OF_DAY));
    }

    private static int makeHash(final String finalKey) {
        int hash = 0;
        for (int index = 0; index < finalKey.length(); index++) {
            hash = (hash << 5) + hash + finalKey.charAt(index);
        }

        return Math.abs(hash);
    }
}
