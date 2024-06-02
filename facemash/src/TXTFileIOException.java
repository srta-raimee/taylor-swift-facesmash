import java.io.IOException;
public class TXTFileIOException extends IOException {

    public TXTFileIOException() {
        super();
    }

    public TXTFileIOException(String message) {
        super(message);
    }

    public TXTFileIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public TXTFileIOException(Throwable cause) {
        super(cause);
    }

}
