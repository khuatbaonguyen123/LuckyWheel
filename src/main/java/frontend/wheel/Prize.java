package frontend.wheel;

public class Prize {
    private String name;
    private Integer index;
    private Integer amount;

    public Prize(String name, Integer index, Integer amount) {
        this.name = name;
        this.index = index;
        this.amount = amount;
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

    public void decreaseAmount() {
        amount = amount - 1;
    }
}
