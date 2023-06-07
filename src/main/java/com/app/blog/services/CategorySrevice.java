package com.app.blog.services;

import java.util.List;

import com.app.blog.payloads.CategoryDto;

public interface CategorySrevice {

	
	CategoryDto createCategory(CategoryDto category);
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer CategoryId);
	
	
	void dleleteCategory(Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	
	
	List<CategoryDto>getallcategories();
}
