package sel.prac.springboot.BlogApp.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sel.prac.springboot.BlogApp.Entity.Category;
import sel.prac.springboot.BlogApp.Entity.Post;
import sel.prac.springboot.BlogApp.Exception.ResourceNotFoundException;
import sel.prac.springboot.BlogApp.Payload.CategoryDTO;
import sel.prac.springboot.BlogApp.Repository.CategoryRepository;
import sel.prac.springboot.BlogApp.Service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {

        Category category=modelMapper.map(categoryDTO,Category.class);

        Category savedCategory=categoryRepository.save(category);

        return modelMapper.map(savedCategory,CategoryDTO.class);

    }

    @Override
    public CategoryDTO getCategory(Long categoryId) {

        Category category=categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category" , "id ", categoryId));
        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<Category> allCategories = categoryRepository.findAll();
        return allCategories.stream().map((category -> modelMapper.map(category,CategoryDTO.class))).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setId(categoryDTO.getId());

        Category updatedCategory = categoryRepository.save(category);

        return modelMapper.map(updatedCategory,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","ID",categoryId));
        categoryRepository.delete(category);
    }
}
