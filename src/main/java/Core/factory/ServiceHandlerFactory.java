package Core.factory;

import Core.annotation.ServiceHandlerMapping;
import Core.template.ServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ServiceHandlerFactory {
    private static final Logger log= LoggerFactory.getLogger(ServiceHandlerFactory.class);
    private final Map<String, ServiceHandler>requestMapping=new HashMap<>();
    private static ServiceHandlerFactory instance;

    public static void setInstance(ServiceHandlerFactory s){
        instance=s;
    }
    public static ServiceHandlerFactory getInstance(){
        return instance;
    }

    /**
     * instantiate the serviceHandlerFactory in the way of singleton
     */
    public ServiceHandlerFactory(String[]paths){
        String base= Objects.requireNonNull(this.getClass().getResource("/")).getPath();

        for(String p:paths){
            String locate=p.replaceAll("\\.","\\/");
            String fullPath=base+locate;
            try {
                List<String>classNames=getAllClassName(fullPath,p);
                classInstantiation(classNames);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param classNames the name of the classed that need to be instantiate
     */
    private void classInstantiation(List<String>classNames){
        for(String s:classNames){
            try {
                ServiceHandler h=(ServiceHandler)Class.forName(s).
                        getDeclaredConstructor().newInstance();
                ServiceHandlerMapping mapping=h.getClass().getAnnotation(ServiceHandlerMapping.class);
                String requestURI=mapping.value();
                requestMapping.put(requestURI,h);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * @param path the package path
     * @return the list of classNames in the package
     * @throws FileNotFoundException if the package does not exist
     */
    private List<String> getAllClassName(String path,String fullClassContext) throws FileNotFoundException {
        File file=new File(path);
        if(!file.exists()){
            log.info("file {} not found",path);
            throw  new FileNotFoundException();
        }
        if(!file.isDirectory()){
            String fileName=file.getName();
            if(!fileName.endsWith(".class")){
                return new ArrayList<>();
            }else{
                return new ArrayList<>(){{add(fileName);}};
            }
        }
        List<String>allClass=new ArrayList<>();
        File[]subFiles=file.listFiles();
        assert subFiles != null;
        for(File f:subFiles){
            if(!f.isDirectory()){
                String n=f.getName();
                if(n.endsWith(".class")){
                    n=n.substring(0,n.indexOf("."));
                    allClass.add(fullClassContext+"."+n);
                }
            }
        }
        return allClass;
    }

    /**
     *
     * @param requestURI the URI that mapping to a serviceHandler
     * @return the serviceHandler
     */
    public ServiceHandler getServiceHandlerInstance(String requestURI){
        return requestMapping.getOrDefault(requestURI,null);
    }
}
