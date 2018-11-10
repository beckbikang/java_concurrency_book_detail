package head.first.dp.builder;

/**
 * 还是觉得建造者模式这个例子不错
 *
 *
 */

public class BuilderDemo2 {

    public static void main(String[] args){

        Computer mycomputer = ComputerBuilder.makeMyComputer();
        mycomputer.show();

    }
}

class ComputerBuilder{

    public static Computer makeMyComputer(){
        Computer computer = new Computer();
        computer.screen = new OldScreen();
        computer.cpu = new AmdCpu();
        computer.ram = new SumsangRam();
        computer.storage = new JJStorage();
        computer.keyBoard = new ColKeyBoard();
        return computer;
    }
}

class Computer{
    Screen screen;
    Cpu cpu;
    Ram ram;
    Storage storage;
    KeyBoard keyBoard;

    public void show(){
        screen.showScreen();
        cpu.showCpu();
        ram.showRam();
        storage.showStorage();
        keyBoard.showKeyBoard();
    }
}

interface Screen{
    void showScreen();
}
class OldScreen implements Screen{
    public void showScreen(){
        System.out.println("show screen");
    }
}

interface Cpu{
    void showCpu();
}

class AmdCpu implements Cpu{
    public void showCpu(){
        System.out.println(" amd cpu");
    }
}

interface Ram{
    void showRam();
}

class SumsangRam implements Ram{
    public void showRam(){
        System.out.println("Sumsang Ram");
    }
}

interface Storage{
    void showStorage();
}

class JJStorage implements Storage{
    public void showStorage(){
        System.out.println("jj Storage");
    }
}

interface KeyBoard{
    void showKeyBoard();
}
class ColKeyBoard implements KeyBoard{
    public void showKeyBoard(){
        System.out.println("Col KeyBoard");
    }
}



