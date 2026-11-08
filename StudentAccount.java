import java.util.ArrayList;
import java.util.List;

public class StudentAccount {
    private final String id;
    private final String fullName;
    private final String email;
    private final String phone;
    private final String level;
    private final String major;
    private final boolean scholarship;
    private final List<String> courses;

    // المنشئ private: الحساب ما يتصنع إلا عن طريق Builder
    private StudentAccount(Builder b) {
        this.id = b.id;
        this.fullName = b.fullName;
        this.email = b.email;
        this.phone = b.phone;
        this.level = b.level;
        this.major = b.major;
        this.scholarship = b.scholarship;
        this.courses = new ArrayList<>(b.courses);
    }

    public String getId() { return id; }

    public void addCourse(String course) { courses.add(course); }

    // ===== Prototype: نسخ القالب وتغيير الهوية فقط =====
    public StudentAccount cloneFor(String newId, String newFullName, String newEmail) {
        Builder b = new Builder(newId, newFullName, newEmail)
                .level(this.level)
                .major(this.major)
                .scholarship(this.scholarship);
        // نسخ المقررات واحداً واحداً: قائمة جديدة مستقلة (نسخ عميق)
        for (String c : this.courses) {
            b.course(c);
        }
        return b.build();
    }

    @Override
    public String toString() {
        return id + " | " + fullName + " | " + email + " | " + level
                + " | " + major + " | scholarship=" + scholarship + " | " + courses;
    }

    // ===== Builder =====
    public static class Builder {
        private final String id;
        private final String fullName;
        private final String email;
        private String phone = null;
        private String level = "L1";
        private String major;
        private boolean scholarship = false;
        private List<String> courses = new ArrayList<>();

        public Builder(String id, String fullName, String email) {
            this.id = id;
            this.fullName = fullName;
            this.email = email;
        }

        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder level(String level) { this.level = level; return this; }
        public Builder major(String major) { this.major = major; return this; }
        public Builder scholarship(boolean s) { this.scholarship = s; return this; }
        public Builder course(String c) { this.courses.add(c); return this; }

        public StudentAccount build() {
            if (id == null || id.isBlank())
                throw new IllegalStateException("id obligatoire");
            if (fullName == null || fullName.isBlank())
                throw new IllegalStateException("fullName obligatoire");
            if (email == null || !email.contains("@"))
                throw new IllegalStateException("email invalide");
            if (!List.of("L1", "L2", "L3", "M1", "M2").contains(level))
                throw new IllegalStateException("level invalide");
            return new StudentAccount(this);
        }
    }
}