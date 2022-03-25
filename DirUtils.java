import java.io.File;
import java.util.Collections;
import java.util.List;

public class DirUtils {
    // #############################################
    // Ez az eljaras Listakent visszaadja a mappakat
    // #############################################
    public static List<String> listDirectories(String currentDir)
    {
        List<String> folders = new ArrayList<>();

        File file = new File(currentDir);
        File[] pathNames = file.listFiles();

        assert pathNames != null;

        for (File pathname : pathNames)
        {
            if (pathname.isDirectory())
            {
                String[] st = (pathname.toString()).split("/");
                folders.add(st[st.length-1]);
            }
        }

        Collections.sort(folders);
        return folders;
    }

    // #################################################################
    // Ez az eljaras Listakent visszaadja a mappaban levo html file-okat
    // #################################################################
    public static List<String> listHtml(String currentDir)
    {
        List<String> html = new ArrayList<>();

        File file = new File(currentDir);
        String[] pathNames;
        pathNames = file.list();

        assert pathNames != null;

        for (String pathname : pathNames)
        {
            if (pathname.endsWith(".html"))
            {
                html.add(pathname);
            }
        }

        Collections.sort(html);
        return html;
    }

    // ##############################################################################
    // Ez az eljaras Listakent visszaadja a mappaban levo megfelelo formatumu kepeket
    // ##############################################################################
    public static List<String> getPhotos(String currentDir)
    {
        List<String> photos = new ArrayList<>();

        File file = new File(currentDir);
        File[] pathNames = file.listFiles();

        assert pathNames != null;

        for (File pathname : pathNames)
        {
            String actual = pathname.toString();
            if (pathname.isFile() && (actual.endsWith(".png") ||  actual.endsWith(".PNG") || actual.endsWith(".jpg") || actual.endsWith(".JPG") || actual.endsWith(".jpeg") || actual.endsWith(".JPEG") || actual.endsWith(".gif") || actual.endsWith(".GIF")))
            {
                String[] st = pathname.toString().split("/");
                photos.add(st[st.length-1]);
            }
        }

        Collections.sort(photos);
        return photos;
    }

    // ############################################
    // Ez az eljaras megvalosit egy rekurziv Listat
    // ############################################
    public static List<String> recursiveList(File directory, List<String> folders)
    {
        File[] files = directory.listFiles();

        assert files != null;

        for (File file : files)
        {
            if (file.isDirectory())
            {
                folders.add(file.toString() + "/");
                recursiveList(file, folders);
            }
        }
        return folders;
    }
    
}