# Object-Oriented Programming in Java — Lab 4: Inheritance

**Author:** Divine Mark Makhomeya  
**Registration Number:** H250333E  
**Course:** Object Oriented Programming  


This repository contains my solutions for **Lab 4** of the OOP in Java lab manual.
The lab covers creating subclasses with `extends`, calling superclass constructors
and methods with `super`, using `protected`, `final`, and `@Override`, understanding
constructor call order, and deciding between inheritance and composition.

## Lab Objectives
- Create subclasses with `extends` and reuse superclass code.
- Call superclass constructors and methods with `super`.
- Use `protected`, `final`, and the `@Override` annotation correctly.
- Explain the constructor call order and the role of `java.lang.Object`.
- Decide between inheritance ("is-a") and composition ("has-a").

## Exercises

### Exercise 1 & 2: `Person`, `Student`, `Teacher`
- `Person` with private `name` and `age`, and a `display()` method.
- `Student extends Person` adding `course`.
- `Teacher extends Person` adding `subject`.
- Both override `display()` and call `super.display()` to reuse parent output.

**Expected Output:**
--- Student ---
Name: Tariro, Age: 21
Course: OOP
--- Teacher ---
Name: Mr Moyo, Age: 45
Subject: Databases
Student age via inherited getter: 21
Teacher age via inherited getter: 45

### Exercise 3: `Vehicle`, `Car`, `ElectricCar` (Multilevel)
- Three-level hierarchy showing constructor call order (top-down).
- Each level overrides `describe()` and calls `super.describe()`.

**Expected Output:**
--- creating ElectricCar ---
Vehicle constructor: EV-001
Car constructor: EV-001
ElectricCar constructor: EV-001
--- describe ---
Vehicle: EV-001
doors: 5
range: 400 km

### Exercise 4: Why `Dog extends Animal` Fails Without `super(...)`
- `Animal` has only `Animal(String name)`.
- The broken version of `Dog` (shown commented out in `BrokenDogDemo.java`)
  fails to compile because Java inserts an implicit `super()` call, and
  `Animal` has no no-argument constructor.

**Compiler error (from the broken version):**
error: constructor Animal in class Animal cannot be applied to given types;
public Dog() {
^
required: String
found: no arguments
reason: actual and formal argument lists differ in length


- The fixed version calls `super(name)` explicitly and compiles.

**Expected Output:**
Dog name: Rex


### Exercise 5: Refactor `Stack extends ArrayList` into Composition

The exercise asks us to refactor an inheritance-based Stack into one that uses
composition, and explain what the inheritance version wrongly exposes.

#### Bad version: `class BadStack<T> extends ArrayList<T>`

Because `BadStack` **is an** `ArrayList`, it inherits every public method of
`ArrayList`, including:

| Method | Why it's wrong for a Stack |
|---|---|
| `add(int index, E element)` | Inserts in the middle — breaks LIFO |
| `get(int index)` | Random access — not a stack operation |
| `remove(int index)` | Removes from anywhere — breaks LIFO |
| `set(int index, E element)` | Replaces an element mid-stack |
| `clear()` | Wipes the stack without popping |
| `subList(...)` | Returns a view into the middle |

The `main` method demonstrates this by calling `bad.add(0, 999)`,
`bad.set(2, 777)`, and `bad.remove(0)`, which destroy the stack's ordering.
The class compiles and runs, but its public API is far larger than a stack's
should be.

#### Good version: `class Stack<T>` (composition)

The internal `List<T>` is private. Only `push`, `pop`, `peek`, `isEmpty`, and
`size` are exposed. The LIFO invariant is enforced by the class, and attempts
to call `add(int, E)` or `get(int)` on a `Stack` do not compile.

**Conclusion:** A `Stack` is **not a kind of** `ArrayList`; it **has** a list.
Composition models this correctly and keeps the internal representation hidden.

**Expected Output:**
=== BAD: inheritance version ===
Stack after pushes: [1, 2, 3]
After misuse: [777, 2]
LIFO invariant broken: pop() returns 2 but the stack was tampered with

=== GOOD: composition version ===
peek: 3
pop: 3
pop: 2
size: 1

## Files
- `PersonDemo.java` — Exercises 1 & 2
- `VehicleDemo.java` — Exercise 3
- `BrokenDogDemo.java` — Exercise 4 (broken version commented out)
- `StackDemo.java` — Exercise 5 (both bad and good versions live)

>
