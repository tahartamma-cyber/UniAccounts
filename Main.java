public class Main {
    public static void main(String[] args) {
        AccountRepository repo = AccountRepository.getInstance();

        // القالب: دفعة L3 معلوماتية (ما نضيفوهوش للمستودع)
        StudentAccount template = new StudentAccount.Builder(
                "TEMPLATE", "Template", "template@univ.dz")
                .level("L3").major("Informatique")
                .course("POO").course("BD").course("Reseaux")
                .build();

        // ثلاثة طلبة بالنسخ: نبدل الهوية فقط
        StudentAccount a = template.cloneFor("101", "Amine Ben", "amine@univ.dz");
        StudentAccount b = template.cloneFor("102", "Sara Kaci", "sara@univ.dz");
        StudentAccount c = template.cloneFor("103", "Yacine Omar", "yacine@univ.dz");

        // نغيّر مقررات سارة فقط: هل تتأثر الحسابات الأخرى؟
        b.addCourse("IA");

        repo.add(a);
        repo.add(b);
        repo.add(c);

        System.out.println(repo.findById("101"));
        System.out.println(repo.findById("102"));
        System.out.println(repo.findById("103"));
        System.out.println(template);
        System.out.println("Count = " + repo.count());

        AuditLogger.getInstance().printAll();
    }
}