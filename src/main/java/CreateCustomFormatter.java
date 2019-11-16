import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class CreateCustomFormatter {

    public static void main(String[] args) throws Exception {

    }

    public static class MyCustomFormatter extends Formatter {

        @Override
        public String format(LogRecord record) {
            StringBuffer sb = new StringBuffer();
            sb.append(record.getMessage());
            sb.append("\n");
            return sb.toString();
        }

    }

}