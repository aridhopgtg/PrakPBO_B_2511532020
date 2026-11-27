package shop.model;

public class PhysicalProduct extends Product implements Shippable {
    private final int weightGram;

    public PhysicalProduct(String sku, String name, long price, int weightGram) {
        super(sku, name, price);
        if (weightGram <= 0) {
            throw new IllegalArgumentException("Berat produk fisik harus lebih dari 0 gram");
        }
        this.weightGram = weightGram;
    }

    @Override
    public int getWeightGram() {
        return weightGram;
    }
}