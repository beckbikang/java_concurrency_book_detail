package head.first.dp.prototype;



public class PrototypeDemo {


    public static void main(String[] args) throws CloneNotSupportedException{

        Teacher teacher = new Teacher();
        teacher.setAge(44);
        teacher.setName("zhang ai");
        teacher.setIndustry("经济学");

        Student boyA = new Student();
        boyA.setName("A");
        boyA.setAget(20);
        boyA.setTeacher(teacher);
        System.out.println(boyA);

        Student boyB = (Student) boyA.clone();
        boyB.setName("B");
        boyB.getTeacher().setName("tom");
        System.out.println(boyB);


    }
}


class Student implements Cloneable{

    private String name;
    private int aget;
    private Teacher teacher;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAget() {
        return aget;
    }

    public void setAget(int aget) {
        this.aget = aget;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString(){
        return " Student [name=" + name + ", age=" + aget+" teacher:" + teacher ;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Student s = (Student) super.clone();
        s.setTeacher((Teacher) s.teacher.clone());
        return s;
    }

}

class Teacher implements Cloneable{
    private String name;
    private int age;
    private String industry;

    @Override
    public String toString(){
        return industry+" Teacher name=" + name + ", age=" + age ;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }


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
        this.age = age;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
