package head.first.dp;

import java.util.ArrayList;
import java.util.List;

public class CompositeDemo {

    public static void main(String[] args){

        Person boss = new Person("tom", 40);
        Person cfo = new Person("cfo", 20);
        boss.addPerson(cfo);
        Person cto = new Person("cto", 21);
        boss.addPerson(cto);
        Person cpo = new Person("cpo", 22);
        boss.addPerson(cpo);
        Person coo = new Person("coo", 23);
        boss.addPerson(coo);

        boss.showPerson();
    }
}

class Person{
    private String name;
    private int age;
    List<Person> list;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
        list = new ArrayList<>();
    }

    public void addPerson(Person person){
        list.add(person);
    }

    public void showPerson(){
        for (Person person : list){
            System.out.println(person);
        }
    }

    @Override
    public String toString(){

        return "name:"+name+" age:"+age;
    }
}
