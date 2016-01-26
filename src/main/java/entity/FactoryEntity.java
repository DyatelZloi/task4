package entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 30.12.2015.
 */
public abstract class FactoryEntity {

    private static final Logger log = LoggerFactory.getLogger(FactoryEntity.class);

    private static FactoryEntity instance;

    /**
     * @return
     */
    public static FactoryEntity getInstance() {
        if (instance == null) {
            instance = new FactoryCreateEntity();
        }
        return instance;
    }

    /**
     * @param clazz
     * @return
     */
    public abstract BaseEntity getEntity(Class clazz);
}