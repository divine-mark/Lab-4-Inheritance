// Lab 4 — Exercise 4
//
// This file demonstrates WHY class Dog extends Animal fails to compile
// when Animal only has Animal(String name).
//
// The broken version is commented out below. Uncomment it to see the error:
//
//     error: constructor Animal in class Animal cannot be applied to given types;
//         public Dog() {
//                ^
//       required: String
//       found:    no arguments
//       reason: actual and formal argument lists differ in length
//
// Explanation:
// When Dog's constructor does not call super(...) explicitly, Java inserts
// super() — a call to the no-argument superclass constructor. But Animal
// only has Animal(String). The compiler cannot find a matching constructor,
// so it errors.

class Animal {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

// BROKEN — uncomment to see the compiler error
// class Dog extends Animal {
//     public Dog() {
//         // Java inserts super(); here — but Animal has no no-arg constructor!
//     }
// }

// FIXED version — compiles because it calls super(name) explicitly
class Dog extends Animal {
    public Dog(String name) {
        super(name); // ✅ now it compiles
    }
}

public class BrokenDogDemo {
    public static void main(String[] args) {
        Dog d = new Dog("Rex");
        System.out.println("Dog name: " + d.getName());
    }
}