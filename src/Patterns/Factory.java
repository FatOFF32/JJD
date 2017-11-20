package Patterns;

public abstract class Factory {

    private static Factory jp;
    private static Factory de;
    private static Factory ru;

    //    @Override
    //    Factory createCar(Factory car) {
    //        if (car == null)
    //            car = new RussianFactory();
    //        return car;
    //    }
    public abstract Car createCar(String nameCar);

    public abstract Car createCar();


    public static Factory getFactory(String countryCar){

        Factory car = null;

        switch (countryCar){
            case "JP":
                if (jp == null)
                    jp = JapanFactory.getFactory();
                car = jp;
                break;
            case "DE":
                if (de == null)
                    de = GermanFactory.getFactory();
                car = de;
                break;
            case "RU":
                if (ru == null)
                    ru = RussianFactory.getFactory();
                car = de;
                break;
        }

        return car;

    }
}
