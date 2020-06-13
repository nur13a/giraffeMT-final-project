package itAcademy.giraffeMT.giraffeMT.exceptions;

public class UserNotFound extends Exception {
    private static final String message="User not found";
    public UserNotFound(){
        super(message);
    }
}
