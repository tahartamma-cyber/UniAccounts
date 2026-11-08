import java.util.ArrayList;
import java.util.List;

// نمط Singleton: سجل تدقيق واحد فقط في البرنامج
public class AuditLogger {

    private static AuditLogger instance;

    // كل العمليات المسجلة
    private final List<String> entries = new ArrayList<>();

    private AuditLogger() { }

    public static AuditLogger getInstance() {
        if (instance == null) {
            instance = new AuditLogger();
        }
        return instance;
    }

    public void log(String message) {
        entries.add(message);
    }

    public void printAll() {
        System.out.println("--- Audit log ---");
        for (int i = 0; i < entries.size(); i++) {
            System.out.println((i + 1) + ". " + entries.get(i));
        }
    }
}