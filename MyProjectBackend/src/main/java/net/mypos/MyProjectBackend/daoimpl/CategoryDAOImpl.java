package net.mypos.MyProjectBackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.mypos.MyProjectBackend.dao.CatergoryDAO;
import net.mypos.MyProjectBackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CatergoryDAO {

	
	private static List<Category> categories = new ArrayList<>();
	
	static {
		Category catergory1 = new Category();
		catergory1.setId(1);
		catergory1.setName("Television");
		catergory1.setDescription("This is some description for television");
		catergory1.setImageURL("CAT_1.png");
		categories.add(catergory1);
		
		catergory1 = new Category();
		catergory1.setId(2);
		catergory1.setName("Mobile");
		catergory1.setDescription("This is some description for Mobile");
		catergory1.setImageURL("CAT_2.png");
		categories.add(catergory1);
		
		catergory1 = new Category();
		catergory1.setId(3);
		catergory1.setName("Laptop");
		catergory1.setDescription("This is some description for Laptop");
		catergory1.setImageURL("CAT_3.png");
		categories.add(catergory1);

	}
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		for(Category category:categories) {
			if(category.getId() == id) return category;
			}
		
			return null;
		}

}
