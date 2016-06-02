package org.foodrev.planner_json_interface.GsonModels;

/**
 * Created by magulo on 5/22/16.
 */
public class GsonTemplate {
    String name;
    int meals;
    String carType;

    public GsonTemplate(String name, int meals, String carType) {
        this.name = name;
        this.meals = meals;
        this.carType = carType;
    }
    public GsonTemplate() {
        this.name = "name";
        this.meals = 200;
        this.carType = "carType";
    }
}
