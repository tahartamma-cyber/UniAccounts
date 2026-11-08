package uniaccounts.repository;

import java.util.HashMap;
import java.util.Map;

import uniaccounts.model.StudentAccount;
import uniaccounts.util.AuditLogger;

/**
 * Pattern: Singleton.
 * Role: the one and only repository that stores all the accounts.
 */
public class AccountRepository {

    // Singleton: the single instance
    private static AccountRepository instance;

    private final Map<String, StudentAccount> accounts = new HashMap<>();

    // Singleton: private constructor
    private AccountRepository() { }

    // Singleton: lazy initialization
    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public void add(StudentAccount account) {
        if (accounts.containsKey(account.getId())) {
            AuditLogger.getInstance().log("REJECTED duplicate id " + account.getId());
            throw new IllegalStateException("id deja existant: " + account.getId());
        }
        accounts.put(account.getId(), account);
        AuditLogger.getInstance().log("CREATED account " + account.getId());
    }

    public StudentAccount findById(String id) { return accounts.get(id); }

    public int count() { return accounts.size(); }
}
