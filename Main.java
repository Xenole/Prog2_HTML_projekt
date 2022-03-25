import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class Main
{
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String dirPath;
        String strDirPath;

        if (args.length == 1) // Parancssori argumentumok kezelese
        {
            dirPath = args[0]; // Ebben a String-ben tarolom el a gyokerkonyvtaramnak a nevet
            File file = new File(dirPath); // Egy uj File objektumot hozok letre amely a dirPath-bol egy absztrakt eleresi utat hoz letre

            strDirPath = file.toString() + "/";
            list.add(strDirPath);

            try
            {
                if (file.isDirectory() == false)
                {
                    System.out.println("Error: provide the path of a directory");
                    System.exit(1);
                }

                List<String> dirs = DirUtils.recursiveList(file, list);

                for (int i = 0; i < dirs.size(); i++)
                {
                    System.out.println("DEBUG\t" + dtf.format(now) + " " + dirs.get(i));
                }

                for (int i = 0; i < dirs.size(); i++)
                {
                    List<String> photofiles = DirUtils.getPhotos(dirs.get(i));

                    for (String pfajl : photofiles)
                    {
                        HtmlGenerator.createHtmlForPhotos(dirs.get(i), pfajl);
                    }

                    for (String pfajl : photofiles)
                    {
                        HtmlGenerator.changeHtmlForPhoto(dirs.get(i), pfajl, dirPath);
                    }

                    HtmlGenerator.createIndexHtml(dirs.get(i) + "/", dirPath);
                }

                HtmlGenerator.start(dirPath);

                // ####################################################
                // Itt tudom kitorolni a kepekhez tartozo html-fajlokat
                // ####################################################
                // DelUtils.deleteHtmlFiles(dirs);
            }
            catch (NullPointerException e)
            {
                System.out.println("Error: provide the path of a directory");
                System.exit(1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Error: provide the path of a directory");
            System.exit(1);
        }
    }
}