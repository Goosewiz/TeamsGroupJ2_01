package gusev.java24.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathFinder {
    public static List<PathDifferenceStatus> difference(Path path1, Path path2) throws IOException {
        List<PathDifferenceStatus> answer = new ArrayList<>();
        if (isNotExists(path1, path2)) {
            answer.add(PathDifferenceStatus.NotExists);
            return answer;
        }
        File file1 = path1.toFile();
        File file2 = path2.toFile();
        if (file1.isFile() && file2.isFile() && isSameFile(path1, path2))
            answer.add(PathDifferenceStatus.SameFile);
        if (file1.isFile() && file2.isFile())
            answer.add(getSizeFile(path1, path2));
        if (isSameDirectory(path1, path2))
            answer.add(PathDifferenceStatus.SameDirectory);
        if (isSameAbsoluteNameDepth(path1, path2))
            answer.add(PathDifferenceStatus.SameAbsoluteNameDepth);
        if (isSamePrefix(path1, path2))
            answer.add(PathDifferenceStatus.SamePrefix);
        if (isSameRoot(path1, path2))
            answer.add(PathDifferenceStatus.SameRoot);
        if (isSubpath(path1, path2))
            answer.add(PathDifferenceStatus.Subpath);
        else if (isParentPath(path1, path2))
            answer.add(PathDifferenceStatus.ParentPath);
        return answer;
    }

    public static boolean isNotExists(Path path1, Path path2) {
        return (Files.notExists(path1) || Files.notExists(path2));
    }

    public static boolean isSameFile(Path path1, Path path2) throws IOException {
        return (Files.isSameFile(path1, path2));
    }

    public static PathDifferenceStatus getSizeFile(Path path1, Path path2) throws IOException {
        if (Files.size(path1) > Files.size(path2))
            return PathDifferenceStatus.BiggerFile;
        if (Files.size(path1) < Files.size(path2))
            return PathDifferenceStatus.SmallerFile;
        return PathDifferenceStatus.SameSizeFile;
    }

    public static boolean isSameDirectory(Path path1, Path path2) {
        Path path1Parent = path1.getParent();
        Path path2Parent = path2.getParent();
        return (path1Parent.equals(path2Parent));
    }

    public static boolean isSameAbsoluteNameDepth(Path path1, Path path2) {
        path1 = path1.toAbsolutePath();
        path2 = path2.toAbsolutePath();
        int p1 = path1.getNameCount();
        int p2 = path2.getNameCount();
        return (p1 == p2);
    }

    public static boolean isSamePrefix(Path path1, Path path2) {
        path1 = path1.toAbsolutePath();
        path2 = path2.toAbsolutePath();
        Path path1Prefix = path1.getName(0);
        Path path2Prefix = path2.getName(0);
        return (path1Prefix.equals(path2Prefix));
    }

    public static boolean isSameRoot(Path path1, Path path2) {
        Path path1Root = path1.getRoot();
        Path path2Root = path2.getRoot();
        return (path1Root.equals(path2Root));
    }

    public static boolean isSubpath(Path path1, Path path2) {
        path1 = path1.toAbsolutePath();
        path2 = path2.toAbsolutePath();
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

    public static boolean isParentPath(Path path1, Path path2) {
        path1 = path1.toAbsolutePath();
        path2 = path2.toAbsolutePath();
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
