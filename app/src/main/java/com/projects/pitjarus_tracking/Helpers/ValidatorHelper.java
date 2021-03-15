package com.projects.pitjarus_tracking.Helpers;

/**
 * Created by Sohibun Nawawi on 12/03/2021.
 */

public class ValidatorHelper {
    public static boolean checkNotNullOrEmpty(String string) {
        return (string != null && !string.contains("null") && !string.isEmpty());
    }

    public static boolean checkNotNullOrEmpty(Integer integer) {
        return (integer != null);
    }
}
