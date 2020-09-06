package electronics.backend.exception;

public class UserAlreadyExistException extends RuntimeException {
    private final String username;

    public String getUsername() {
        return username;
    }

    public UserAlreadyExistException(String username){
        this.username = username;
    }
}
