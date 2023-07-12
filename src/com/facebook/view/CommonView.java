package com.facebook.view;

import java.util.Scanner;

/**
 * <p>
 * Manages the view for user, post, like, comment for getting user input
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class CommonView {

    public static final Scanner SCANNER = new Scanner(System.in);
    private static CommonView commonView;

    /**
     * <p>
     * Default constructor for comment view
     * </p>
     */
    public CommonView() {
    }

    /**
     * <p>
     * Gets the instance of the common view
     * </p>
     *
     * @return Returns the singleton instance of the common view class.
     */
    public static CommonView getInstance() {
        if (null == commonView) {
            commonView = new CommonView();
        }

        return commonView;
    }
}
