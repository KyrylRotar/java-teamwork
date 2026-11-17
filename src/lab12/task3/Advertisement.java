package lab12.task3;

public abstract class Advertisement {
    protected DisplayMedia media;
    protected final String title;

    public Advertisement(String title, DisplayMedia media) {
        this.title = title;
        this.media = media;
    }

    public void setMedia(DisplayMedia media) {
        this.media = media;
    }

    public abstract void show();
}