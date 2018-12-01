package head.first.dp.Chain;

import java.util.ArrayList;
import java.util.List;

public class ChainDemo {

    public static void main(String[] args){

        LoggerParser loggerParser = new LoggerParser();
        loggerParser.addLog(new ConsoleLog());
        loggerParser.addLog(new FileLog());
        loggerParser.sendlog();

    }
}

class LoggerParser {

    List<Logger> logList = new ArrayList<>();

    public void addLog(Logger l){
        logList.add(l);
    }

    public void sendlog(){
        if(!logList.isEmpty()){
            for (Logger logger:logList
                    ) {
                logger.write();
            }
        }
    }
}


abstract class Logger{


    abstract void write();
}

class ConsoleLog extends Logger{
    public void write(){
        System.out.println("ConsoleLog");
    }
}

class FileLog extends Logger{
    public void write(){
        System.out.println("FileLog");
    }
}