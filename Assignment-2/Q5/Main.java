class EncapsulatedPerson {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        EncapsulatedPerson person = new EncapsulatedPerson();
        person.setName("John Doe");
        person.setAge(20);
        System.out.println(person.getName());
        System.out.println(person.getAge());
    }
}
