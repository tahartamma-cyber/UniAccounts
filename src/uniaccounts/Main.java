package uniaccounts;

import uniaccounts.model.StudentAccount;
import uniaccounts.repository.AccountRepository;
import uniaccounts.util.AuditLogger;

/**
 * Client of the three patterns:
 * Builder (builds the template), Prototype (clones it), Singleton (repository + audit log).
 */
public class Main {
    public static void main(String[] args) {
        // Singleton: the unique repository
        AccountRepository repo = AccountRepository.getInstance();

        // Builder: template for an L3 Informatique cohort (not added to the repository)
        StudentAccount template = new StudentAccount.Builder(
                "TEMPLATE", "Template", "template@univ.dz")
                .phone("0556565656")
                .level("L3").major("Informatique")
                .course("POO").course("BD").course("Reseaux")
                .build();

        // Prototype: three students by cloning, only the identity changes
        StudentAccount a = template.cloneFor("101", "Amine Ben", "amine@univ.dz");
        StudentAccount b = template.cloneFor("102", "Sara Kaci", "sara@univ.dz");
        StudentAccount c = template.cloneFor("103", "Yacine Omar", "yacine@univ.dz");

        // only Sara's courses change: the other accounts must not be affected
        b.addCourse("IA");

        repo.add(a);
        repo.add(b);
        repo.add(c);

        System.out.println(repo.findById("101"));
        System.out.println(repo.findById("102"));
        System.out.println(repo.findById("103"));
        System.out.println(template);
        System.out.println("Count = " + repo.count());

        // Singleton: the unique audit log
        AuditLogger.getInstance().printAll();
    }
}
