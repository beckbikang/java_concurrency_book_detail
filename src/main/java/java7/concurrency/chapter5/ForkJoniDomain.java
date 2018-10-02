package java7.concurrency.chapter5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Main class of the example
 */
public class ForkJoniDomain {

    /**
     * Main method of the example
     */
    public static void main(String[] args) {
        // Create the pool
        ForkJoinPool pool=new ForkJoinPool();

        // Create three FolderProcessor tasks for three diferent folders
        FolderProcessor system=new FolderProcessor("/Users/kang/Documents/phpProject/NewPhpProject/weibo_ad/php", "php");
        FolderProcessor apps=new FolderProcessor("/Users/kang/Documents/phpProject/NewPhpProject/weibo_ad/java","java");
        FolderProcessor documents=new FolderProcessor("/Users/kang/Documents/phpProject/NewPhpProject/weibo_ad/cpp","cpp");

        // Execute the three tasks in the pool
        pool.execute(system);
        pool.execute(apps);
        pool.execute(documents);

        // Write statistics of the pool until the three tasks end
        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
            System.out.printf("******************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while ((!system.isDone())||(!apps.isDone())||(!documents.isDone()));

        // Shutdown the pool
        pool.shutdown();

        // Write the number of results calculate by each task
        List<String> results;

        results=system.join();
        System.out.printf("System: %d files found.\n",results.size());

        results=apps.join();
        System.out.printf("Apps: %d files found.\n",results.size());

        results=documents.join();
        System.out.printf("Documents: %d files found.\n",results.size());


    }

}

class FolderProcessor extends RecursiveTask<List<String>> {

    /**
     * Serial Version of the class. You have to add it because the
     * ForkJoinTask class implements the Serializable interfaces
     */
    private static final long serialVersionUID = 1L;

    /**
     * Path of the folder this task is going to process
     */
    private String path;

    /**
     * Extension of the file the task is looking for
     */
    private String extension;

    /**
     * Constructor of the class
     * @param path Path of the folder this task is going to process
     * @param extension Extension of the files this task is looking for
     */
    public FolderProcessor (String path, String extension) {
        this.path=path;
        this.extension=extension;
    }

    /**
     * Main method of the task. It throws an additional FolderProcessor task
     * for each folder in this folder. For each file in the folder, it compare
     * its extension with the extension it's looking for. If they are equals, it
     * add the full path of the file to the list of results
     */
    @Override
    protected List<String> compute() {
        List<String> list=new ArrayList<>();
        List<FolderProcessor> tasks=new ArrayList<>();

        File file=new File(path);
        File content[] = file.listFiles();
        if (content != null) {
            for (int i = 0; i < content.length; i++) {
                if (content[i].isDirectory()) {
                    // If is a directory, process it. Execute a new Task
                    FolderProcessor task=new FolderProcessor(content[i].getAbsolutePath(), extension);
                    task.fork();
                    tasks.add(task);
                } else {
                    // If is a file, process it. Compare the extension of the file and the extension
                    // it's looking for
                    if (checkFile(content[i].getName())){
                        list.add(content[i].getAbsolutePath());
                    }
                }
            }

            // If the number of tasks thrown by this tasks is bigger than 50, we write a message
            if (tasks.size()>50) {
                System.out.printf("%s: %d tasks ran.\n",file.getAbsolutePath(),tasks.size());
            }

            // Include the results of the tasks
            addResultsFromTasks(list,tasks);
        }
        return list;
    }

    /**
     * Add the results of the tasks thrown by this task to the list this
     * task will return. Use the join() method to wait for the finalization of
     * the tasks
     * @param list List of results
     * @param tasks List of tasks
     */
    private void addResultsFromTasks(List<String> list,
                                     List<FolderProcessor> tasks) {
        for (FolderProcessor item: tasks) {
            list.addAll(item.join());
        }
    }

    /**
     * Checks if a name of a file has the extension the task is looking for
     * @param name name of the file
     * @return true if the name has the extension or false otherwise
     */
    private boolean checkFile(String name) {
        if (name.endsWith(extension)) {
            return true;
        }
        return false;
    }


}


