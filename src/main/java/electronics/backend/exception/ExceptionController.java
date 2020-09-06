package electronics.backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<?> exception(ProductNotFoundException exception){
        String message = "Product with id = " + exception.getProductId() + " not found";
        LOG.info(message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity<?> exception(UserAlreadyExistException exception){
        String message = "User " + exception.getUsername() + " already exists";
        LOG.info(message);
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<?> exception(NullPointerException exception, WebRequest request){
        LOG.error("Null pointer exception", exception);
        return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
