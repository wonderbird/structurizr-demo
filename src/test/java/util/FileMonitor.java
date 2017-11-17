package util;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertTrue;

public class FileMonitor
{
   private final File file;

   private final long oldModifiedDate;

   public FileMonitor(final Path aFilePath)
   {
      file = new File(aFilePath.toString());
      oldModifiedDate = file.lastModified();
   }

   public void assertModified()
   {
      final long newModifiedDate = file.lastModified();

      final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss.SSS");

      String message =
         String.format("File \"%s\" has not been changed. Current last modified date: %s, previous last modified date: %s",
            file.getPath(),
            dateFormat.format(newModifiedDate), dateFormat.format(oldModifiedDate));

      assertTrue(message,oldModifiedDate < newModifiedDate);
   }
}
