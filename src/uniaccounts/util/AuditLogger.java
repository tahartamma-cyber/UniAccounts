package uniaccounts.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Pattern: Singleton.
 * Role: the one and only audit log of the program.
 */
public class AuditLogger {

    // Singleton: the single instance
    private static AuditLogger instance;

    // all recorded operations
    private final List<String> entries = new ArrayList<>();

    // Singleton: private constructor, nobody else can call "new"
    private AuditLogger() { }

    // Singleton: lazy initialization, created on first request then always reused
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
