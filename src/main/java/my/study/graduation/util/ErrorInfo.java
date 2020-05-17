package my.study.graduation.util;

public class ErrorInfo {
    public final String url;
    public final String ex;
    public final String detail;


    public ErrorInfo(String url, Throwable ex, Throwable root) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
        this.detail = root.getLocalizedMessage();
    }
}
