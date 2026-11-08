package uniaccounts;

import uniaccounts.model.StudentAccount;
import uniaccounts.repository.AccountRepository;
import uniaccounts.util.AuditLogger;

/**
 * Demo for the report screenshots: shows that invalid data is rejected.
 * Run it with: java -cp out uniaccounts.ValidationDemo
 */
public class ValidationDemo {

    public static void main(String[] args) {
        AccountRepository repo = AccountRepository.getInstance();

        // 1) Builder rejects an email without "@"
        try {
            new StudentAccount.Builder("201", "Test One", "test.univ.dz").build();
        } catch (IllegalStateException e) {
            System.out.println("Builder  -> " + e.getMessage());
        }

        // 2) Builder rejects an unknown level
        try {
            new StudentAccount.Builder("202", "Test Two", "two@univ.dz").level("L9").build();
        } catch (IllegalStateException e) {
            System.out.println("Builder  -> " + e.getMessage());
        }

        // 3) Singleton repository rejects a duplicate id
        StudentAccount first = new StudentAccount.Builder("101", "Amine Ben", "amine@univ.dz").build();
        StudentAccount dup = new StudentAccount.Builder("101", "Other Student", "other@univ.dz").build();
        repo.add(first);
        try {
            repo.add(dup);
        } catch (IllegalStateException e) {
            System.out.println("Singleton -> " + e.getMessage());
        }

        AuditLogger.getInstance().printAll();
    }
}
