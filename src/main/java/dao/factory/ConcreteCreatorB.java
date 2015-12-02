package dao.factory;

/**
 * Created by DiZi on 01.12.2015.
 */
class ConcreteCreatorB extends Creator {
    @Override
    public DaoFactory factoryMethod() { return new ConcreteProductB(); }
}