package doli.service1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import doli.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doli.model.Produit;
import doli.repository.ProduitRepository;

@Service
public class ProduitService {

    @Autowired
    ProduitRepository repository;

    public List<Produit> getAllProducts()
    {
        List<Produit> result = (List<Produit>) repository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Produit>();
        }
    }

    public Produit getProductById(Long id) throws RecordNotFoundException
    {
        Optional<Produit> prod = repository.findById(id);

        if(prod.isPresent()) {
            return prod.get();
        } else {
            throw new RecordNotFoundException("Aucun produit a cet ID");
        }
    }

    public Produit createOrUpdateProduct(Produit product)
    {
        if(product.getId()  == null)
        {
            product = repository.save(product);

            return product;
        }
        else
        {
            Optional<Produit> prod = repository.findById(product.getId());

            if(prod.isPresent())
            {
                Produit newProduct = prod.get();
                newProduct.setNomProduit(product.getNomProduit());
                newProduct.setDescription(product.getDescription());
                newProduct.setPrixUnitaire(product.getPrixUnitaire());
                newProduct.setQuantiteStock(product.getQuantiteStock());

                newProduct = repository.save(newProduct);

                return newProduct;
            } else {
                product = repository.save(product);

                return product;
            }
        }
    }

    public void deleteProductById(Long id) throws RecordNotFoundException
    {
        Optional<Produit> prod = repository.findById(id);

        if(prod.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Aucun produit appartient a cet ID");
        }
    }
}

