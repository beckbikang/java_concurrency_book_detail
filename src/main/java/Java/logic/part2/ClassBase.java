package Java.logic.part2;

public class ClassBase {

    public static void main(String args[]){
        tt();
    }

    public static void tt(){
        Fodler fodler = new Fodler();
        fodler.setName("k");
        System.out.println(fodler.getName());
        Fodler.showVersion();
    }
}

class Fodler {


    public static final String VERSION = "0.1";
    public static void showVersion(){
        System.out.println(VERSION);
    }

    private Fodler parrent;

    private Mfiles[] myfiles;

    private String name;

    public Fodler getParrent() {
        return parrent;
    }

    public void setParrent(Fodler parrent) {
        this.parrent = parrent;
    }

    public Mfiles[] getMyfiles() {
        return myfiles;
    }

    public void setMyfiles(Mfiles[] myfiles) {
        this.myfiles = myfiles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Mfiles{
    private String name;
    private String dir;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
