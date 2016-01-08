package entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 30.12.2015.
 */
public class FactoryCreateEntity extends FactoryEntity {

    private static final Logger log = LoggerFactory.getLogger(FactoryCreateEntity.class);

    /**
     *
     * @param clazz
     * @return
     */
    @Override
    public BaseEntity getEntity(Class clazz) {
        BaseEntity baseEntity = null;
        try {
            baseEntity = (BaseEntity) clazz.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Ошибка при создании сущности");
            throw new FactoryException(e);
        }
        return baseEntity;
    }
}