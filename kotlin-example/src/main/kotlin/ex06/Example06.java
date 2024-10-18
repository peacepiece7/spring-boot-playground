package ex06;

interface Bark {
    void bark();
}

abstract class Animal implements Bark {

    private final String name;
    private int age;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Animal(String name){
        this.name = name;
    }

    public void eat(){
        System.out.println(name+" 식사 시작 합니다.");
    }

    public String getName() {
        return name;
    }
}

class Dog extends Animal{

    public Dog(){
        super("");
    }

    public Dog(String name){
        super(name);
    }

    @Override
    public void bark() {
        System.out.println(this.getName()+" : 멍멍");
    }
}


public class Example06 {

    public Example06(){
        var dog = new Dog("퍼피");
        dog.setAge(10);
        var age = dog.getAge();
        dog.eat();
        dog.bark();
    }

    public static void main(String[] args){
        new Example06();
    }
}
