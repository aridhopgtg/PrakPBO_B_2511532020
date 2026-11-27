package shop.model;

public abstract class Product {
    private final String sku;
    private final String name;
    private final long price;

    protected Product(String sku, String name, long price) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU tidak boleh kosong");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Harga tidak boleh negatif");
        }
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
}