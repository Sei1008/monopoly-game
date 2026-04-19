package config;

import java.util.List;


public class PropertyConfig {
    public String name;
    public int rent;
    public int price;
    public int position;

    public PropertyConfig(String name, int price, int rent, int position){
        this.name = name;
        this.rent = rent;
        this.price = price;
        this.position = position;
    }

    public static List<PropertyConfig> PROPERTIES = List.of(
    new PropertyConfig("Harrenhal Avenue", 60, 2, 1),
    new PropertyConfig("Winterfell Avenue", 80, 4, 7));

}


