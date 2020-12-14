package com.company;

public class Main {

    public static void main(String[] args) {
        ShadyStuff stuff = new ShadyStuff();
        int typeOfTask = 3;
        /* Type of Task Values:
         * - 0 -> element by element
         * - 1 -> row by row
         * - 2 -> column by column
         * - 3 -> every k-th element (k - the number of tasks)
         * */
        Controller controller = new Controller(stuff,typeOfTask);
        controller.threadForEachTask();
        //controller.threadPoolForTasks();


    }
}
