package gusev.java24.task1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {

    @Test
    void differenceExists() throws IOException {
        String p1 = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test\\1.txt";
        String p2 = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test\\2.txt";
        Path path1 = Path.of(p1);
        Path path2 = Path.of(p2);
    /*  String text = Files.readString(path1);
        System.out.println(text);
        text = Files.readString(path2);
        System.out.println(text);*/
        List<PathDifferenceStatus> answer = PathFinder.difference(path1, path2);
        assertEquals(5, answer.size());
        assertEquals(PathDifferenceStatus.SmallerFile, answer.get(0));
        assertEquals(PathDifferenceStatus.SameDirectory, answer.get(1));
        assertEquals(PathDifferenceStatus.SameAbsoluteNameDepth, answer.get(2));
        assertEquals(PathDifferenceStatus.SamePrefix, answer.get(3));
        assertEquals(PathDifferenceStatus.SameRoot, answer.get(4));
    }
    @Test
    void differenceNotExists() throws IOException {
        String p1 = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test\\3.txt";
        String p2 = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test\\2.txt";
        Path path1 = Path.of(p1);
        Path path2 = Path.of(p2);
        List<PathDifferenceStatus> answer = PathFinder.difference(path1, path2);
        assertEquals(1, answer.size());
        assertEquals(PathDifferenceStatus.NotExists, answer.get(0));
    }
    @Test
    void differenceCatalog() throws IOException{
        String p1 = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test\\1.txt";
        String p2 = "C:\\TeamsGroup\\TeamsGroupJ2_01\\test\\nextLVL\\1.txt";
        Path path1 = Path.of(p1);
        Path path2 = Path.of(p2);
        List<PathDifferenceStatus> answer = PathFinder.difference(path1, path2);
        assertEquals(4, answer.size());
        assertEquals(PathDifferenceStatus.BiggerFile, answer.get(0));
        assertEquals(PathDifferenceStatus.SamePrefix, answer.get(1));
        assertEquals(PathDifferenceStatus.SameRoot, answer.get(2));
        assertEquals(PathDifferenceStatus.Subpath, answer.get(3));
    }
}