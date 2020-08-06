import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAccess {
    private Configuration configuration;
    private FileSystem hdfs;
    private String rootPath;

    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     *                 for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) throws URISyntaxException, IOException {
        configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");

        hdfs = FileSystem.get(
                new URI(rootPath), configuration
        );
        this.rootPath = rootPath;
    }

    /**
     * Creates empty file or directory
     *
     * @param path
     * @param isFolder
     */
    public void create(String path, boolean isFolder) throws IOException {
        if (!exists(path)) {
            if (isFolder) {
                hdfs.mkdirs(new Path(path));
            } else {
                hdfs.createNewFile(new Path(path));
            }
        } else {
            System.out.println(path + " already exists");
        }
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) throws IOException, URISyntaxException {
        hdfs = FileSystem.get(
                new URI(rootPath), configuration
        );
        if (exists(path) && !isDirectory(path)) {
            Path apPath = new Path(path);
            OutputStream os = hdfs.append(apPath);
            BufferedWriter br = new BufferedWriter(
                    new OutputStreamWriter(os, StandardCharsets.UTF_8)
            );
            br.append(content);
            br.flush();
            br.close();
            os.close();
        } else {
            System.out.println(path + " doesn't exist or is a dir");
        }
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws IOException {
        if (exists(path)) {
            if (!isDirectory(path)) {
                Path apPath = new Path(path);
                InputStream is = hdfs.open(apPath);
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(is, StandardCharsets.UTF_8)
                );
                StringBuilder sb = new StringBuilder();
                while (br.ready()) {
                    sb.append(br.readLine());
                }
                br.close();
                is.close();
                return sb.toString();
            }
        } else {
            System.out.println(path + " doesn't exist");
        }
        return null;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws IOException {
        if (exists(path)) {
            Path delPath = new Path(path);
            hdfs.delete(delPath, true);
        } else {
            System.out.println(path + " doesn't exist");
        }
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) throws IOException {
        return hdfs.isDirectory(new Path(path));
    }

    /**
     * Checks, is the "path" exists
     *
     * @param path
     * @return
     */
    public boolean exists(String path) throws IOException {
        return hdfs.exists(new Path(path));
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) throws IOException {
        if (exists(path) && isDirectory(path)) {
            ArrayList<String> fileList = new ArrayList<>();

            FileStatus[] fsList = hdfs.listStatus(new Path(path));
            for (FileStatus fs : fsList) {
                String filePath = fs.getPath().toString();
                if (fs.isDirectory()) {
                    fileList.addAll(list(filePath));
                } else {
                    fileList.add(filePath);
                }
            }
            return fileList;
        } else {
            System.out.println(path + " doesn't exist or is not dir");
            return null;
        }
    }
}
