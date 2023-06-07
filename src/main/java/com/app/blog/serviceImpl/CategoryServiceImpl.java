package com.app.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entity.Category;
import com.app.blog.exceptions.ResouceNotFoundException;
import com.app.blog.payloads.CategoryDto;
import com.app.blog.repositries.CategoryRepo;
import com.app.blog.services.CategorySrevice;
@Service
public class CategoryServiceImpl implements CategorySrevice {

	
	@Autowired
	private CategoryRepo categoryrepo;
	
	@Autowired
	private ModelMapper modalmaper;
	@Override
	public CategoryDto createCategory(CategoryDto category) {
		// TODO Auto-generated method stub
		
		Category categores=this.modalmaper.map(category, Category.class);
		
		Category addedcat=this.categoryrepo.save(categores);
		return this.modalmaper.map(addedcat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer CategoryId) {
		// TODO Auto-generated method stub
	
		Category category=this.categoryrepo.findById(CategoryId).orElseThrow(()-> new ResouceNotFoundException("User","Id",CategoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory=this.categoryrepo.save(category);
		return this.modalmaper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void dleleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
Category cat=	this.categoryrepo.findById(categoryId).orElseThrow(()->new ResouceNotFoundException("Category", "categoryId", categoryId));
	
	this.categoryrepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=	this.categoryrepo.findById(categoryId).orElseThrow(()->new ResouceNotFoundException("Category", "categoryId", categoryId));
		
		return this.modalmaper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getallcategories() {
		// TODO Auto-generated method stub
		
		List< Category>categories=this.categoryrepo.findAll();
	List<CategoryDto>catDto=categories.stream().map((cat)->this.modalmaper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDto;
	}
public Category dtotoCategory(CategoryDto categoryDto) {
	Category category=this.modalmaper.map(categoryDto,Category.class);
	return category;
	
	
}
public CategoryDto categorytoDto(Category category) {
	
	CategoryDto categoryDto =this.modalmaper.map(category,CategoryDto.class);
	
	return categoryDto;
}


}
