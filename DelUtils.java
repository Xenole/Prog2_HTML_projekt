import java.io.File;
import java.util.List;

public class DelUtils {
    // ##################################################################################
    // Ez az eljaras kiszuri, majd egy Listaba kigyujti a ".html" kiterjesztesu file-okat
    // ##################################################################################
    public static List<String> filertHtmlFiles(String photoPath)
    {
        List<String> htmls = new ArrayList<>();

        File f = new File(photoPath);
        File[] pathNames = f.listFiles();

        assert pathNames != null;

        for (File pathname : pathNames)
        {
            String actual = pathname.toString();
            if (pathname.isFile() && (actual.endsWith(".html")))
            {
                String[] st = (pathname.toString()).split("/");
                htmls.add(st[st.length - 1]);
            }
        }

        Collections.sort(htmls);
        return htmls;
    }

    // #######################################
    // Ez az eljaras torli ki a html file-okat
    // #######################################
    public static void deleteHtmlFiles(List<String> directories)
    {
        List<String> htmls = new ArrayList<>();

        for (int i = 0; i < directories.size(); i++)
        {
            List<String> actual = filertHtmlFiles(directories.get(i));

            for (int j = 0; j < actual.size(); j++)
            {
                htmls.add(directories.get(i) + actual.get(j));
            }
        }

        for(int j = 0; j < htmls.size(); j++)
        {
            File file = new File(htmls.get(j));
            file.delete();
        }

        System.out.println("INFO\t" + "html files deleted.");
    }
}
