package be.ehb.eindproject.model;

public class WinkelmandItem {
    private Long productId;
    private String naam;
    private int aantal;

    public WinkelmandItem(Long productId, String naam, int aantal) {
        this.productId = productId;
        this.naam = naam;
        this.aantal = aantal;
    }

    public Long getProductId() { return productId; }
    public String getNaam() { return naam; }
    public int getAantal() { return aantal; }
    public void setAantal(int aantal) { this.aantal = aantal; }
}
