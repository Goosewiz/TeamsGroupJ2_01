package gusev.java24.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String args[]) throws IOException {
        //pathS = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test\\nextLVL\\last\\1.txt";
        //pathS = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test";
        String pathS = args[0];
        Path path = Path.of(pathS);
        if(!Files.isDirectory(path))
            System.out.println("This is not a directory");
        else{
            Finder finder = new Finder();
            Files.walkFileTree(path, finder);
            int result = finder.getDepth();
            System.out.println(result);
        }
    }
}
