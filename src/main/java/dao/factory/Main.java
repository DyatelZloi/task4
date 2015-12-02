package dao.factory;

/**
 * Created by DiZi on 01.12.2015.
 */
public class Main {
    public static void main(String[] args){
        Creator creator = new ConcreteCreatorA();
        DaoFactory product = creator.factoryMethod();
            System.out.printf("Created {%s}\n", product.getClass());
    }
}