package Patterns;

public class Pizza {
    private boolean thick;

    @Override
    public String toString() {
        return "Pizza{" +
                "thick=" + thick +
                ", sausege=" + sausege +
                ", becon=" + becon +
                ", cheese=" + cheese +
                '}';
    }

    private int sausege;
    private int becon;
    private int cheese;

    private Pizza(Builder builder) {
        this.thick = builder.thick;
        this.becon = builder.becon;
        this.sausege = builder.sausege;
        this.cheese = builder.cheese;
    }

    public static Builder builder1(boolean thick) {
        return new Builder(thick);
    }

    public static class Builder{
        private final boolean thick;
        private int sausege = 0;
        private int becon = 0;
        private int cheese = 0;

        public Builder(boolean thick) {
            this.thick = thick;
        }
        

        public Builder addSauseg(int sausege){
            this.sausege = sausege;
            return this;
        }

        public Builder addBecon(int becon){
            this.becon = becon;
            return this;
        }

        public Builder addCheese(int cheese){
            this.cheese = cheese;
            return this;
        }

        public Pizza build(){
            return new Pizza(this);
        }
    }
}
