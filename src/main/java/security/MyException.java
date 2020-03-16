package security;

import org.springframework.security.core.AuthenticationException;

public class MyException extends AuthenticationException {

    public MyException(String msg, Throwable t) {
        super(msg, t);
    }

    public MyException(String msg) {
        super(msg);
    }
}
