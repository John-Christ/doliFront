package doli.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PRODUIT")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nomProduit")
    private String nomProduit;

    @Column(name="description")
    private String description;

    @Column(name="prixUnitaire")
    private String prixUnitaire;


    @Column(name="quantiteStock")
    private String quantiteStock;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(String prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }


    public String getQuantiteStock() {
        return prixUnitaire;
    }

    public void setQuantiteStock(String quantiteStock) {
        this.quantiteStock = quantiteStock;
    }


    @Override
    public String toString() {
        return "Produit [id=" + id + ", nomProduit=" + nomProduit +
                ", description=" + description + ", prixUnitaire=" + prixUnitaire   + ",  quantiteStock=" + quantiteStock   + "]";
    }
}
