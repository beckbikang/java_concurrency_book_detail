package head.first.dp.iterator;

public class IteratorDemo {

    public static void main(String args[]){

        NameRepos nameRepos = new NameRepos();
        for(Iterator iterator = nameRepos.getIterator();iterator.hasNext();){
            String name = (String) iterator.next();
            System.out.println("Name:"+name);
        }


    }
}

interface Iterator{

    boolean hasNext();
    Object next();
}

interface Container{
    Iterator getIterator();
}

class NameRepos implements Container{

    public String names[] = {"Robet","a","b","c"};

    @Override
    public Iterator getIterator(){
        return new NameIterator();
    }

    private class NameIterator implements Iterator{
        int index;

        @Override
        public boolean hasNext(){
            if(index < names.length){
                return true;
            }
            return false;
        }

        @Override
        public Object next(){
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}




