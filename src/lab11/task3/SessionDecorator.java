package lab11.task3;

public abstract class SessionDecorator implements MovieSession {
    protected final MovieSession wrappedSession;

    public SessionDecorator(MovieSession wrappedSession) {
        this.wrappedSession = wrappedSession;
    }

    @Override
    public String getDescription() {
        return wrappedSession.getDescription();
    }

    @Override
    public double getPrice() {
        return wrappedSession.getPrice();
    }
}