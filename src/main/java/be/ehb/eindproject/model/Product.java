package be.ehb.eindproject.model;

public class Product {
    private Long id;
    private String naam;
    private String categorie;
    private String beschrijving;
    private int voorraad;
    private String afbeeldingUrl;

    public Product() {}

    public Product(Long id, String naam, String categorie, String beschrijving, int voorraad, String afbeeldingUrl) {
        this.id = id;
        this.naam = naam;
        this.categorie = categorie;
        this.beschrijving = beschrijving;
        this.voorraad = voorraad;
        this.afbeeldingUrl = afbeeldingUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNaam() { return naam; }
    public void setNaam(String naam) { this.naam = naam; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public String getBeschrijving() { return beschrijving; }
    public void setBeschrijving(String beschrijving) { this.beschrijving = beschrijving; }
    public int getVoorraad() { return voorraad; }
    public void setVoorraad(int voorraad) { this.voorraad = voorraad; }
    public String getAfbeeldingUrl() { return afbeeldingUrl; }
    public void setAfbeeldingUrl(String afbeeldingUrl) { this.afbeeldingUrl = afbeeldingUrl; }
}
