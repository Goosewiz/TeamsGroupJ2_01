package gusev.java24.task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class PathFinder {
    public static List<PathDifferenceStatus> difference(Path path1, Path path2) throws IOException {
        List<PathDifferenceStatus> answer = new LinkedList<>();
        if (Files.notExists(path1) || Files.notExists(path2)) {
            answer.add(PathDifferenceStatus.NotExists);
            return answer;
        }
        if (Files.isSameFile(path1, path2))
            answer.add(PathDifferenceStatus.SameFile);
        if (Files.size(path1) > Files.size(path2))
            answer.add(PathDifferenceStatus.BiggerFile);
        else if (Files.size(path1) < Files.size(path2))
            answer.add(PathDifferenceStatus.SmallerFile);
        else
            answer.add(PathDifferenceStatus.SameSizeFile);
        Path path1Parent = path1.getParent();
        Path path2Parent = path2.getParent();
        if (path1Parent.equals(path2Parent))
            answer.add(PathDifferenceStatus.SameDirectory);
        int p1 = path1.getNameCount();
        int p2 = path2.getNameCount();
        if (p1 == p2)
            answer.add(PathDifferenceStatus.SameAbsoluteNameDepth);
        Path path1Prefix = path1.getName(0);
        Path path2Prefix = path2.getName(0);
        if (path1Prefix.equals(path2Prefix))
            answer.add(PathDifferenceStatus.SamePrefix);
        Path path1Root = path1.getRoot();
        Path path2Root = path2.getRoot();
        if (path1Root.equals(path2Root))
            answer.add(PathDifferenceStatus.SameRoot);
        int min = Math.min(p1, p2) - 1;
        int i = 0;
        boolean isSame = true;
        while (i < min && isSame) {
            Path path1Name = path1.getName(i);
            Path path2Name = path2.getName(i);
            if (!path1Name.equals(path2Name))
                isSame = false;
            i++;
        }
        if (isSame && p1 > p2)
            answer.add(PathDifferenceStatus.ParentPath);
        else if (isSame && p2 > p1)
            answer.add(PathDifferenceStatus.Subpath);
        return answer;
    }

    public boolean isNotExists(Path path1, Path path2) {
        return (Files.notExists(path1) || Files.notExists(path2));
    }

    public boolean isSameFile(Path path1, Path path2) throws IOException {
        return (Files.isSameFile(path1, path2));
    }

    public boolean isBiggerFile(Path path1, Path path2) throws IOException {
        return (Files.size(path1) > Files.size(path2));
    }

    public boolean isSmallerFile(Path path1, Path path2) throws IOException {
        return (Files.size(path1) < Files.size(path2));
    }

    public boolean isSameSizeFile(Path path1, Path path2) throws IOException {
        return (Files.size(path1) == Files.size(path2));
    }

    public boolean isSameDirectory(Path path1, Path path2) {
        Path path1Parent = path1.getParent();
        Path path2Parent = path2.getParent();
        return (path1Parent.equals(path2Parent));
    }

    public boolean isSameAbsoluteNameDepth(Path path1, Path path2) {
        int p1 = path1.getNameCount();
        int p2 = path2.getNameCount();
        return (p1 == p2);
    }

    public boolean isSamePrefix(Path path1, Path path2) {
        Path path1Prefix = path1.getName(0);
        Path path2Prefix = path2.getName(0);
        return (path1Prefix.equals(path2Prefix));
    }

    public boolean isSameRoot(Path path1, Path path2) {
        Path path1Root = path1.getRoot();
        Path path2Root = path2.getRoot();
        return (path1Root.equals(path2Root));
    }

    public boolean isSubpath(Path path1, Path path2) {
        int p1 = path1.getNameCount();
        int p2 = path2.getNameCount();
        int min = Math.min(p1, p2) - 1;
        int i = 0;
        boolean isSame = true;
        while (i < min && isSame) {
            Path path1Name = path1.getName(i);
            Path path2Name = path2.getName(i);
            if (!path1Name.equals(path2Name))
                isSame = false;
            i++;
        }
        return (isSame && p1 < p2);
    }

    public boolean isParentPath(Path path1, Path path2) {
        int p1 = path1.getNameCount();
        int p2 = path2.getNameCount();
        int min = Math.min(p1, p2) - 1;
        int i = 0;
        boolean isSame = true;
        while (i < min && isSame) {
            Path path1Name = path1.getName(i);
            Path path2Name = path2.getName(i);
            if (!path1Name.equals(path2Name))
                isSame = false;
            i++;
        }
        return (isSame && p1 > p2);
    }
}
