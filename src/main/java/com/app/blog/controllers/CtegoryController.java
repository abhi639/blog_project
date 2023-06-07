package com.app.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.payloads.ApiResponce;
import com.app.blog.payloads.CategoryDto;
import com.app.blog.services.CategorySrevice;

@RestController
@RequestMapping("/api/categories")
public class CtegoryController {

	
    @Autowired	
	private CategorySrevice categoryService;
	
        @PostMapping("/")
	public ResponseEntity<CategoryDto>createCte(@RequestBody CategoryDto Categorydto){
		CategoryDto createCategory=this.categoryService.createCategory(Categorydto);
		
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
		
		
		
	}
	
        @PutMapping("/{CategoryId}")
    	public ResponseEntity<CategoryDto>updateCte(@RequestBody CategoryDto Categorydto,@PathVariable("catid") Integer id){
    		CategoryDto upadatedCategory=this.categoryService.updateCategory(Categorydto, id);
    		
    		return new ResponseEntity<CategoryDto>(upadatedCategory,HttpStatus.CREATED);
    		
    		
    		
    		
    	}
     @DeleteMapping("/{catId}")   
	public ResponseEntity<ApiResponce> deletecat(@PathVariable("catId") Integer catid) {
		
		this.categoryService.dleleteCategory(catid);
		return new ResponseEntity<ApiResponce>(new ApiResponce("category is deleted successfully",true),HttpStatus.OK);
		
	}
	 
     @GetMapping("/{catId}")   
 	public ResponseEntity<CategoryDto> getcatbyId(@PathVariable("catId") Integer catid) {
 		
 		CategoryDto catDto=this.categoryService.getCategory(catid);
 		return new ResponseEntity<CategoryDto>(catDto,HttpStatus.OK);
 		
 	}
     
     @GetMapping("/")   
  	public ResponseEntity<List<CategoryDto>> getallcategory() {
  		
  	List<CategoryDto> catDto=this.categoryService.getallcategories();
  		return ResponseEntity.ok(catDto);
  		
  	}
     
     
 	 
     
     
}
