import com.beust.jcommander.JCommander;
import org.nd4j.util.ArchiveUtils;
import java.io.File;

public class Unzipper {
    public static void main(String[] args) throws Exception {

        String[] argv = { "-log", "2", "-groups", "unit" };
        JCommander jc = new JCommander();
        jc.parse(argv);


        // https://app.snyk.io/vuln/SNYK-JAVA-ORGND4J-72550
        // should appear as Reachable
        ArchiveUtils.unzipFileTo("./malicious_file.zip", "./unzipped/");
        File f = new File("/tmp/evil.txt");
        if (f.exists()) {
            throw new Exception("Malicious file /tmp/evil.txt was created");
        };
    }
}

