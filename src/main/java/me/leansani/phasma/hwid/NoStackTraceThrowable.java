package me.leansani.phasma.hwid;

public class NoStackTraceThrowable extends RuntimeException {

    public NoStackTraceThrowable(final String msg) {
        super(msg);
        this.setStackTrace(new StackTraceElement[0]);
    }

    @Override
    public String toString() {
        return "xz";
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
