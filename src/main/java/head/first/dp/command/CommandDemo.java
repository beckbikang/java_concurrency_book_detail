package head.first.dp.command;


public class CommandDemo {

    public static void main(String[] args){

        OrExp orExp = new OrExp(
          new TermeExp("w"), new TermeExp("tom"));
        System.out.println(orExp.interpret("tom w"));
        AndExp andExp = new AndExp(
                new TermeExp("w"), new TermeExp("tom"));
        System.out.println(orExp.interpret("tom w"));


    }
}

interface Exp{
    boolean interpret(String txt);
}

class TermeExp implements Exp{

    private String data;

    public TermeExp(String data){
        this.data = data;
    }
    @Override
    public boolean interpret(String txt){
        if(txt.contains(data)){
            return true;
        }
        return false;
    }
}

class OrExp implements Exp{
    private Exp ex1;
    private Exp ex2;

    public OrExp(Exp ex1, Exp ex2){
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    @Override
    public boolean interpret(String txt){

        return ex1.interpret(txt) || ex2.interpret(txt);
    }
}

class AndExp implements Exp{
    private Exp ex1;
    private Exp ex2;

    public AndExp(Exp ex1, Exp ex2){
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    @Override
    public boolean interpret(String txt){

        return ex1.interpret(txt) && ex2.interpret(txt);
    }
}

















