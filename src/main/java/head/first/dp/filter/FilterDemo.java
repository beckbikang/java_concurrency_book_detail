package head.first.dp.filter;


import java.util.ArrayList;
import java.util.List;

public class FilterDemo {


    public static void main(String[] args){
        Person person = new Person("a",11);
        Person person2 = new Person("b",22);
        Person person3 = new Person("c",33);
        List<Person> list = new ArrayList<>();
        list.add(person);
        list.add(person2);
        list.add(person3);
        //添加过滤器
        PersonFilter aFilter = new AFilter();
        PersonFilter bFilter = new BFilter();
        MyFilter myFilter = new MyFilter(list);
        myFilter.addFilter(aFilter);
        myFilter.addFilter(bFilter);
        myFilter.doFilter();



    }
}

class Person{
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
}

class MyFilter{
    List<PersonFilter> filters;
    List<Person> list;

    public MyFilter(List<Person> list){
        filters = new ArrayList<>();
        this.list = list;
    }
    public void addFilter(PersonFilter personFilter){
        filters.add(personFilter);
    }
    public void doFilter(){
        for (PersonFilter filter:filters
             ) {
            filter.filter(list);
        }
    }
}

interface PersonFilter{
    void filter(List<Person> list);
}

class AFilter implements PersonFilter{
    public void filter(List<Person> list){
        for (Person person:
                list) {
            System.out.println("AFilter");
        }
    }
}

class BFilter implements PersonFilter{
    public void filter(List<Person> list){
        for (Person person:
             list) {
            System.out.println("BFilter");
        }
    }
}
