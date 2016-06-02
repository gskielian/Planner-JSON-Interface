package org.foodrev.planner_json_interface.GsonModels;

/**
 * Created by magulo on 6/1/16.
 */
public class Plan {
    String first_step;
    String second_step;
    String third_step;

    Plan() {
        first_step="first";
        second_step="second";
        third_step="third";
    }

    Plan(String first_step, String second_step, String third_step) {
        this.first_step=first_step;
        this.second_step=second_step;
        this.third_step=third_step;
    }

}
