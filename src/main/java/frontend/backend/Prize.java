package frontend.backend;

import javafx.scene.image.Image;

import java.util.Objects;

public class Prize {
    private String name;
    private Integer index;
    private Integer amount;
    private String item;
    private Image image;
    private int currentRound;

    public Prize(String name, Integer index, Integer amount, String imagePath, String item) {
        this.name = name;
        this.index = index;
        this.amount = amount;
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        this.item = item;
        currentRound = 1;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String filePath) {
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public void decreaseCurrentRound() {
        currentRound = currentRound - 1;
    }

    public void increaseCurrentRound() {
        currentRound = currentRound + 1;
    }

    public void decreaseAmount() {
        amount = amount - 1;
    }

    public void increaseAmount() {
        amount = amount + 1;
    }
}
