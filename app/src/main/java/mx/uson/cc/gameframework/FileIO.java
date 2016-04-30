package mx.uson.cc.gameframework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ernesto on 2/11/15.
 */
public interface FileIO {
    public InputStream readAsset(String fileName) throws IOException;

    public InputStream readFile(String fileName) throws IOException;

    public OutputStream writeFile(String fileName) throws IOException;
}
