package sel.prac.springboot.BlogApp.Service;

import sel.prac.springboot.BlogApp.Payload.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO getCategory(Long categoryId);

    List<CategoryDTO> getAllCategories();

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
    public void deleteCategory(Long categoryId);
}
