package be.ehb.eindproject.model;

public class Lening {
    private Long id;
    private Long userId;
    private Long productId;
    private String productNaam;
    private int aantal;
    private String afhaaldatum;
    private String einddatum;
    private String opmerkingen;

    public Lening() {}

    public Lening(Long id, Long userId, Long productId, String productNaam, int aantal, String afhaaldatum, String einddatum, String opmerkingen) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.productNaam = productNaam;
        this.aantal = aantal;
        this.afhaaldatum = afhaaldatum;
        this.einddatum = einddatum;
        this.opmerkingen = opmerkingen;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getProductNaam() { return productNaam; }
    public void setProductNaam(String productNaam) { this.productNaam = productNaam; }
    public int getAantal() { return aantal; }
    public void setAantal(int aantal) { this.aantal = aantal; }
    public String getAfhaaldatum() { return afhaaldatum; }
    public void setAfhaaldatum(String afhaaldatum) { this.afhaaldatum = afhaaldatum; }
    public String getEinddatum() { return einddatum; }
    public void setEinddatum(String einddatum) { this.einddatum = einddatum; }
    public String getOpmerkingen() { return opmerkingen; }
    public void setOpmerkingen(String opmerkingen) { this.opmerkingen = opmerkingen; }
}
