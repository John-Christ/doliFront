package doli.web;

import java.util.List;
import java.util.Optional;

import doli.exception.RecordNotFoundException;
import doli.model.EmployeeEntity;
import doli.model.Produit;
import doli.service.EmployeeService;
import doli.service1.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class EmployeeMvcController 
{
	@Autowired
    EmployeeService  service;



	@Autowired
	ProduitService service1;





// Home page
	@RequestMapping
	public String home() {
		return "home";
	}



	//Wallet page
	@RequestMapping(path = "/wallet")
	public String getWallet()
	{

		return "wallet";
	}




	// Items page
	@RequestMapping(path = "/items")
	public String getItems( Model model)
	{
       //List of products
		List<Produit> list = service1.getAllProducts();
		model.addAttribute("prods", list);

		return "items";
	}



	// Employees page
	@RequestMapping(path = "/list-employees")
	public String getAllEmployees(Model model) 
	{
		List<EmployeeEntity> list = service.getAllEmployees();

		model.addAttribute("employees", list);
		return "list-employees";
	}




	//EDIT PRODUCT ON PAGE EDIT1
	@RequestMapping(path = {"/edit1", "/edit1/{id}"})
	public String editProductById(Model model, @PathVariable("id") Optional<Long> id)
			throws RecordNotFoundException
	{
		if (id.isPresent()) {
			Produit product = service1.getProductById(id.get());
			model.addAttribute("prod", product);
		} else {
			model.addAttribute("prod", new Produit());
		}
		return "add-edit-product";
	}




// employee edit and update
	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException
	{
		if (id.isPresent()) {
			EmployeeEntity entity = service.getEmployeeById(id.get());
			model.addAttribute("employee", entity);
		} else {
			model.addAttribute("employee", new EmployeeEntity());
		}
		return "add-edit-employee";
	}



	//  DELETE PRODUCT ON PAGE DELETE1
	@RequestMapping(path = "/delete1/{id}")
	public String deleteProductById(Model model, @PathVariable("id") Long id)
			throws RecordNotFoundException
	{
		service1.deleteProductById(id);
		return "redirect:/items";
	}



	//  Delete employee
	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		service.deleteEmployeeById(id);
		return "redirect:/list-employees";
	}





	//  Create or Update Product
	@RequestMapping(path = "/createEmployee1", method = RequestMethod.POST)
	public String createOrUpdateProduct(Produit prod)
	{
		service1.createOrUpdateProduct(prod);
		return "redirect:/items";
	}




	// Create employee

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee(EmployeeEntity employee) 
	{
		service.createOrUpdateEmployee(employee);
		return "redirect:/list-employees";
	}


}
