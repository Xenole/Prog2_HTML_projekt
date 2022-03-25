import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HtmlGenerator {
   
    // ####################################################
    // Ez az eljaras hozza letre a html-fajlokat a kepekhez
    // ####################################################
    public static void createHtmlForPhotos(String photoPath, String photo)
    {
        int dotPosition = photo.indexOf(".");
        String photoWithoutExtension = photo.substring(0, dotPosition);
        String htmlOfPhoto = photoWithoutExtension + ".html";

        try
        {
            PrintWriter pr = new PrintWriter(photoPath + htmlOfPhoto);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    // #########################################################
    // Ez az eljaras modositja a kepekhez tartozo html file-okat
    // #########################################################
    public static void changeHtmlForPhoto (String photoPath, String photo, String photoRootDir)
    {
        List<String> htmlFiles = listHtml(photoPath);

        int index = 0;
        int position = 0;
        int dotPosition = photo.indexOf(".");

        String[] folders = photoPath.split("/");
        String pageName = folders[folders.length-1];
        String photoWithoutExtension = photo.substring(0, dotPosition);
        String htmlOfPhoto = photoWithoutExtension + ".html";

        htmlFiles.remove("index.html");

        for (int i = 0; i < htmlFiles.size(); i++)
        {
            if (htmlFiles.get(i).equals(htmlOfPhoto))
            {
                index = i;
            }
        }

        String[] rootDirArr = photoRootDir.split("/");
        String[] pathArr = photoPath.split("/");
        position = pathArr.length - rootDirArr.length;


        try (PrintWriter writer = new PrintWriter(photoPath + htmlOfPhoto))
        {
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\"/>");
            writer.println("<title>" + pageName + "</title>");
            writer.println("</head>");
            writer.println("<body style=\"background-color: #34eb49\">");
            writer.println("<body>");
            writer.println("<h1>");
            writer.println("<a href=\"");

            for (int i = 0; i < position; i++)
            {
                writer.println("../");
            }

            writer.println("index.html" + "\">Start Page</a>");
            writer.println("</h1>");
            writer.println("<hr>");
            writer.println("<p><a href=\"index.html\">^^</a></p>");

            if (htmlFiles.size() == 1)
            {
                writer.println("<p><a href=\"" + htmlFiles.get(index) + "\"> << </a>" + photo + " <a href=\"" + htmlFiles.get(index) + "\"> >> </a></p>");
                writer.println("<p>");
                writer.println("<a href=" + htmlFiles.get(index) + ">");
                writer.println("<img alt=\"\" src=\"" + photo + "\"  width=\"500 height=\"500\"/></a>");
                writer.println("</p>");
            }
            else if (index == 0)
            {
                writer.println("<p><a href=\"" + htmlFiles.get(index) + "\"> << </a>" + photo + " <a href=\"" + htmlFiles.get(index + 1)+ "\"> >> </a></p>");
                writer.println("<p>");
                writer.println("<a href=" + htmlFiles.get(index + 1) + ">");
                writer.println("<img alt=\"\" src=\"" + photo + "\"  width=\"500 height=\"500\"/></a>");
                writer.println("</p>");
            }
            else if (index == htmlFiles.size() - 1)
            {
                writer.println("<p><a href=\"" + htmlFiles.get(index - 1) + "\"> << </a>" + photo + " <a href=\"" + htmlFiles.get(index) + "\"> >> </a></p>");
                writer.println("<p>");
                writer.println("<img alt=\"\" src=\"" + photo + "\"  width=\"500 height=\"500\"/></a>");
                writer.println("</p>");
            }
            else
            {
                writer.println("<p><a href=\"" + htmlFiles.get(index - 1) + "\"> << </a>" + photo + " <a href=\"" + htmlFiles.get(index + 1) + "\"> >> </a></p>");
                writer.println("<p>");
                writer.println("<a href=" + htmlFiles.get(index + 1) + ">");
                writer.println("<img alt=\"\" src=\"" + photo + "\"  width=\"500 height=\"500\"/></a>");
                writer.println("</p>");
            }
            writer.println("</body>");
            writer.println("</html>");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    // ##############################################
    // Ez az eljaras hozza letre az index.html file-t
    // ##############################################
    public static void createIndexHtml(String photoPath, String photoRootDir)
    {
        String current = photoPath + "index.html";

        String[] folders = photoPath.split("/");
        String pageName = folders[folders.length - 1];

        List<String> files = getPhotos(photoPath);
        List<String> directories = listDirectories(photoPath);

        int position;
        String[] rootDirArr = photoRootDir.split("/");
        String[] pathArr = photoPath.split("/");
        position = pathArr.length - rootDirArr.length;

        try (PrintWriter writer = new PrintWriter(current))
        {
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\"/>");
            writer.println("<title>" + pageName + "</title>");
            writer.println("</head>");
            writer.println("<body style=\"background-color: #34eb49\">");
            writer.println("<body>");
            writer.println("<h1>");
            writer.println("<a href=\"");

            for (int i = 0; i < position; i++)
            {
                writer.println("../");
            }

            writer.println("index.html" + "\">Start Page</a>");
            writer.println("</h1>");
            writer.println("<hr>");
            writer.println("<h2>Directories:</h2>");
            writer.println("<p><a href=\"../index.html\">^^</a></p>");

            for (String directory : directories)
            {
                writer.println("<p><a href=\"" + directory + "/index.html" + "\">" + directory + "</p>");
            }

            if (directories.size() == 0)
            {
                writer.println("<p>There are no subdrirectories in this directory</p>");
            }

            writer.println("<hr>");
            writer.println("<h2>Photos:</h2>");

            if (files.size() > 0)
            {
                for (String file : files)
                {
                    int dotPosition = file.indexOf(".");
                    String photoWithoutExtension = file.substring(0, dotPosition);
                    String link = photoWithoutExtension + ".html";
                    writer.println("<p>");
                    writer.println("<a href=\"" + link + "\">" + file + "</a>");
                    writer.println("</p>");
                }
            }

            if (files.size() == 0)
            {
                writer.println("<p>There are no files in this directory</p>");
            }

            writer.println("</body>");
            writer.println("</html>");

        }
        catch (FileNotFoundException fileNotFoundException)
        {
            fileNotFoundException.printStackTrace();
        }
    }

    // ######################################
    // Ez az eljaras hozza letre a kezdolapot
    // ######################################
    public static void start(String photoPath)
    {
        String current = photoPath + "/index.html";
        String[] folders = photoPath.split("/");
        String pageName = folders[folders.length - 1];

        List<String> files = getPhotos(photoPath);
        List<String> directories = listDirectories(photoPath);

        try (PrintWriter writer = new PrintWriter(current))
        {
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"utf-8\"/>");
            writer.println("<title>" + pageName + "</title>");
            writer.println("</head>");
            writer.println("<body style=\"background-color:#34eb49\">");
            writer.println("<h1>Start Page</h1>");
            writer.println("<hr>");
            writer.println("<h2>Directories:</h2>");
            if (directories.size() == 0)
            {
                writer.println("<p>There are no subdirectories in this directory</p>");
            }
            else
            {
                for (String directory : directories)
                {
                    writer.println("<p><a href=\"" + directory + "/index.html" + "\">" + directory + "</a></p>");
                }
            }

            writer.println("<hr>");
            writer.println("<h2>Photos:</h2>");
            if (files.size() == 0)
            {
                writer.println("<p>There are no files in this directory</p>");
            }
            else
            {
                for (String file : files)
                {
                    int a = file.indexOf('.');
                    String newName = file.substring(0, a);
                    String link = newName + ".html";
                    writer.println("<p><a href=\"" + link + "\">" + file + "</a></p>");
                }
            }

            writer.println("</body>");
            writer.println("</html>");

        }
        catch (FileNotFoundException fileNotFoundException)
        {
            fileNotFoundException.printStackTrace();
        }
    }
}