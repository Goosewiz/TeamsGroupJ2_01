package gusev.java24.task2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FindDepth extends SimpleFileVisitor<Path> {
    private int depth = -1;
    private int maxDepth = Integer.MIN_VALUE;
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        depth++;
        if (depth>maxDepth)
            maxDepth = depth;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        depth--;
        return FileVisitResult.CONTINUE;
    }
    public int getDepth(){
        return maxDepth;
    }
}
