package com.catalog.util;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring services registry.
 *
 * @author Julien.Deruere
 */
public final class Registry {

    private static Logger logger;

    private static Registry registry = new Registry();
    private ApplicationContext applicationContext;

    /**
     * Constructor
     */
    private Registry() {
        logger = Logger.getLogger(Registry.class);
        logger.debug("Spring registry initialisation");
        try {
            applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        } catch (Exception e) {
            logger.error("Spring registry initialisation error", e);
            throw new RuntimeException(e);
        }
        logger.debug("End of Spring registry initialisation");
    }

    /**
     * Get unique instance Registry class.
     *
     * @return
     */
    private static Registry getInstance() {
        return registry;
    }

    /**
     * Service getter.
     *
     * @param idService
     * @return
     */
    public static Object getService(String idService) {
        Object service;
        if (logger.isDebugEnabled()) {
            logger.debug("Spring service look up : " + idService);
        }
        try {
            service = getInstance().applicationContext.getBean(idService);
        } catch (Exception e) {
            logger.error("Error during Spring service look up : " + idService, e);
            throw new RuntimeException(e);
        }
        return service;
    }
}