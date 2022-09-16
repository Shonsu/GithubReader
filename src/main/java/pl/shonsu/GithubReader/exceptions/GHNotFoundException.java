package pl.shonsu.GithubReader.exceptions;

public class GHNotFoundException extends RuntimeException{

    public GHNotFoundException(String message) {
        super(message);
    }
    
}
