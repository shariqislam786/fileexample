import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet(name = "downladingServlet", urlPatterns = "/downloadingServlet", loadOnStartup = 1)
public class DownloadingServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try
        {
            URL url = new URI("https://speed.hetzner.de/100MB.bin").toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //change method to HEAD to quickly get metadata without this step
            // the next methods getContentType will take lot of time in downloading unnecessary data.
            connection.setRequestMethod("HEAD");

            //getMimeType and length
            String mimetype=connection.getContentType();
            int length=connection.getContentLength();

            //initialize another connection to get content
            connection= (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();

            // set file metadata
            resp.setHeader("Content-disposition", "attachment; filename=100MB.bin");
            resp.setContentLength(length);
            resp.setContentType(mimetype);

            //sending file to browser
            IOUtils.copy(inputStream, resp.getOutputStream());
        }
        catch (URISyntaxException e)
        {
            resp.sendError(500);
        }
    }
}
