package gusev.java24.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String args[]) throws IOException {
        //pathS = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test\\nextLVL\\last\\1.txt";-1
        //pathS = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test\\nextLVL\\last";0
        //pathS = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test\\nextLVL";1
        //pathS = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test";2
        //pathS = "C:\\TeamsGroup\\TeamsGroupJ2_01;6
        String pathS = args[0];
        Path path = Path.of(pathS);
        if(!Files.isDirectory(path))
            System.out.println("This is not a directory");
        else{
            FindDepth findDepth = new FindDepth();
            Files.walkFileTree(path, findDepth);
            int result = findDepth.getDepth();
            System.out.println(result);
        }
    }
}
