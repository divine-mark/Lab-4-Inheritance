class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
        if (age < 0 || age > 150) throw new IllegalArgumentException("Invalid age");
        this.name = name.trim();
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Student extends Person {
    private final String course;

    public Student(String name, int age, String course) {
        super(name, age); // superclass constructor runs first
        this.course = course;
    }

    @Override
    public void display() {
        super.display(); // reuse parent's display
        System.out.println("Course: " + course);
    }
}

class Teacher extends Person {
    private final String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Subject: " + subject);
    }
}

public class PersonDemo {
    public static void main(String[] args) {
        Student s = new Student("Tariro", 21, "OOP");
        Teacher t = new Teacher("Mr Moyo", 45, "Databases");

        System.out.println("--- Student ---");
        s.display();
        System.out.println("--- Teacher ---");
        t.display();

        // Both inherit getName() and getAge() from Person
        System.out.println("Student age via inherited getter: " + s.getAge());
        System.out.println("Teacher age via inherited getter: " + t.getAge());
    }
}