package shop.model;

public class DigitalProduct extends Product implements Downloadable {
    private final String downloadLink;

    public DigitalProduct(String sku, String name, long price, String downloadLink) {
        super(sku, name, price);
        if (downloadLink == null || downloadLink.isBlank()) {
            throw new IllegalArgumentException("Link unduhan tidak boleh kosong");
        }
        this.downloadLink = downloadLink;
    }

    @Override
    public String getDownloadLink() {
        return downloadLink;
    }
}