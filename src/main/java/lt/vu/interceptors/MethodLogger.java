package lt.vu.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Interceptor
@LoggedInvocation
public class MethodLogger implements Serializable{
    @AroundInvoke
    public Object logMethodInvocation(InvocationContext context) throws Exception {
        Logger logger = Logger.getLogger(MethodLogger.class.getName());
        FileHandler fileHandler = new FileHandler("methods.log", true);
        logger.addHandler(fileHandler);
        Date timestamp = new Date();
        logger.info("Method invoked: " + context.getMethod().getName());
        logger.info("Method invoked at: " + timestamp.toString());
        return context.proceed();
    }
}
