package lab9.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Змінюваний рекламний блок із незалежною копією списку роликів. */
public final class AdvertisingBlock {
    private final List<String> clips;

    public AdvertisingBlock(List<String> clips) {
        Objects.requireNonNull(clips, "clips");
        this.clips = new ArrayList<>();
        for (String clip : clips) {
            if (clip == null || clip.isBlank()) {
                throw new IllegalArgumentException("Назва рекламного ролика не може бути порожньою.");
            }
            this.clips.add(clip);
        }
    }

    private AdvertisingBlock(AdvertisingBlock source) {
        this(source.clips);
    }

    public AdvertisingBlock copy() {
        return new AdvertisingBlock(this);
    }

    public void replaceClip(int index, String clip) {
        if (clip == null || clip.isBlank()) {
            throw new IllegalArgumentException("Назва рекламного ролика не може бути порожньою.");
        }
        clips.set(index, clip);
    }

    public List<String> getClips() {
        return Collections.unmodifiableList(new ArrayList<>(clips));
    }

    @Override
    public String toString() {
        return clips.toString();
    }
}
