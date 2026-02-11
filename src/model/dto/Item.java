package model.dto;

public class Item {
    private int item_no;
    private String name;
    private String type;
    private int price;
    private String description;
    private String effect_code;

    public Item(int item_no, String name, String type, int price, String description, String effect_code) {
        this.item_no = item_no;
        this.name = name;
        this.type = type;
        this.price = price;
        this.description = description;
        this.effect_code = effect_code;
    }

    public int getItem_no() { return item_no; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getPrice() { return price; }
    public String getDescription() { return description; }
    public String getEffect_code() { return effect_code; }
    public void setItem_no(int item_no) { this.item_no = item_no; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setPrice(int price) { this.price = price; }
    public void setDescription(String description) { this.description = description; }
    public void setEffect_code(String effect_code) { this.effect_code = effect_code; }

    @Override
    public String toString() {
        return "Item{" +
                "item_no=" + item_no +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", effect_code='" + effect_code + '\'' +
                '}';
    }
}
