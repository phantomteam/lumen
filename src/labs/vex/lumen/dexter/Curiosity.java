package labs.vex.lumen.dexter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Class used for internal service discovery!
 *
 * @author vex | Ciobanu Laurentiu
 */
public class Curiosity {

    private static IEngine engine;

    /**
     * Public method used to install a custom service discovery
     * engine
     *
     * @param e used as the default engine
     * @author vex | Ciobanu Laurentiu
     */
    public static void installEngine(IEngine e) {
        engine = e;
    }

    /**
     * Public method used for service discovery!
     *
     * @param pack location of the package
     * @return list of classes in the package
     * @throws IOException when reflection fails
     * @throws ClassNotFoundException when reflection fails
     * @throws EngineException when engine fails loading resources
     * @author vex | Ciobanu Laurentiu
     */
    public static Class[] explore(String pack) throws IOException, ClassNotFoundException, EngineException {
        if(engine != null)
            return engine.explore(pack);
        return getClasses(pack);
    }


    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
