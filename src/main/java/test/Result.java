package test;

public class Result {

    private String id;
    private boolean asrtResult;
    private String message;

    public Result() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isAsrtResult() {
        return asrtResult;
    }

    public void setAsrtResult(boolean asrtResult) {
        this.asrtResult = asrtResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
