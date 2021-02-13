package me.yjh;

public class Helloman {

    String name;
    int Howlong;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowlong() {
        return Howlong;
    }

    public void setHowlong(int howlong) {
        Howlong = howlong;
    }

    @Override
    public String toString() {
        return "Helloman{" +
                "name='" + name + '\'' +
                ", Howlong=" + Howlong +
                '}';
    }
}
