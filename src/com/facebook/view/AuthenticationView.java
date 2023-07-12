package com.facebook.view;

import com.facebook.controller.UserController;
import com.facebook.model.User;
import com.facebook.model.UserBuilder;
import com.facebook.view.validation.UserValidation;

/**
 * <p>
 * Manages the authentication view for user sign up, sign in
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class AuthenticationView extends CommonView {

    private final UserController userController = UserController.getInstance();
    private final UserValidation userValidation = UserValidation.getInstance();
    private final UserView userView = UserView.getInstance();
    private static AuthenticationView authenticationView;

    /**
     * <p>
     * Default constructor for Authentication view
     * </p>
     */
    private AuthenticationView() {
    }

    /**
     * <p>
     * Gets the instance of the Authentication view
     * </p>
     *
     * @return Returns the singleton instance of the Authentication view class.
     */
    public static AuthenticationView getInstance() {
        if (null == authenticationView) {
            authenticationView = new AuthenticationView();
        }

        return authenticationView;
    }

    public static void main(String[] args) {
        final AuthenticationView authenticationView = AuthenticationView.getInstance();

        authenticationView.displayMenu();
    }

    /**
     * <p>
     * Displays the menu details
     * </p>
     */
    public void displayMenu() {
        final UserBuilder user = new UserBuilder();

        System.out.println("\tFACEBOOK\nCLICK 1 TO SIGN UP\nCLICK 2 TO SIGN IN\nCLICK 3 TO EXIT");

        switch (userView.getChoice()) {
            case 1:
                signUp(user);
                break;
            case 2:
                signIn(user);
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("INVALID CHOICE");
                displayMenu();
        }
    }

    /**
     * <p>
     * Handles the sign-up process for a new user, collecting and validating user information
     * </p>
     *
     * @param user Refer the {@link User} for details
     */
    private void signUp(final UserBuilder user) {
        user.setId(userView.getUserIdGenerate());
        user.setName(userView.getName());
        user.setEmail(userView.getEmail());
        user.setMobileNumber(userView.getMobileNumber());
        user.setPassword(userView.getPassword());
        user.setGender(userView.getGender());
        user.setDateOfBirth(userView.getDateOfBirth());

        if (userController.signUp(user.build())) {
            System.out.println("ACCOUNT SUCCESSFULLY SIGN UP");
        } else {
            System.out.println("ACCOUNT ALREADY EXIST");
            displayMenu();
        }
        System.out.println("PRESS YES FOR EDIT USER DETAILS AND PRESS ANY KEY FOR MENU ");

        if (userValidation.validateAccess(SCANNER.nextLine())) {
            userView.displaysUserOptions(userController.getUserId(user.build()));
        } else {
            displayMenu();
        }
    }

    /**
     * <p>
     * Handles the sign-in process for a user, collecting and validating user information, providing options for
     * user edit details.
     * </p>
     *
     * @param user Refer the {@link User} for details
     */
    private void signIn(final UserBuilder user) {
        signInChoice(user);
        user.setPassword(userView.getPassword());

        if (userController.signIn(user.build())) {
            System.out.println("ACCOUNT SIGN IN");
        } else {
            System.out.println("INCORRECT EMAIL OR MOBILE NUMBER AND PASSWORD");
            displayMenu();
        }
        System.out.println(String.join("", "DO YOU WANT TO EDIT,GET,DELETE THE USER DETAILS,PRESS 'YES' ",
                "FOR PRINT OPTION AND PRESS ANY KEY FOR MAIN MENU"));

        if (userValidation.validateAccess(SCANNER.nextLine())) {
            userView.displaysUserOptions(userController.getUserId(user.build()));
        } else {
            displayMenu();
        }
    }

    /**
     * <p>
     * Collects the user's sign-in choice
     * </p>
     *
     * @param user Refer {@link User} to sign in
     */
    private void signInChoice(final UserBuilder user) {
        System.out.println("CLICK 1 TO MOBILE NUMBER\nCLICK 2 TO EMAIL ID");

        switch (userView.getChoice()) {
            case 1:
                user.setMobileNumber(userView.getMobileNumber());
                break;
            case 2:
                user.setEmail(userView.getEmail());
                break;
            default:
                System.out.println("INVALID CHOICE, SELECT 1 OR 2");
                signInChoice(user);
        }
    }
}
