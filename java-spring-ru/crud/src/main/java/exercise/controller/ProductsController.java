package exercise.controller;

import java.net.BindException;
import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import exercise.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductCreateDTO productCreateDTO) throws BindException {
        var product = productMapper.map(productCreateDTO);

        var categoryIdDto = productCreateDTO.getCategoryId();
        var category =  categoryRepository.findById(categoryIdDto).isPresent();

        if (!category) {
            throw  new HttpMessageNotReadableException("no");
        }

        productRepository.save(product);
        var productDTO = productMapper.map(product);

        return productDTO;
    }

    @GetMapping(path = "")
    public List<ProductDTO> index() {
        var products = productRepository.findAll();
        return products.stream()
                .map(productMapper::map)
                .toList();
    }

    @GetMapping(path = "/{id}")
    public ProductDTO show(@PathVariable long id) {
        var product =  productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        var productDTO = productMapper.map(product);
        return productDTO;
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@Valid @RequestBody ProductUpdateDTO productUpdateDTO,
                          @PathVariable long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        productMapper.update(productUpdateDTO, product);

     //   var userIdDTO = taskUpdateDTO.getAssigneeId();
     //   var user = userRepository.findById(userIdDTO).get();

     //   task.setAssignee(user);

      //  taskRepository.save(task);
        var categoryIdDto = productUpdateDTO.getCategoryId().get();
        var category =  categoryRepository.findById(categoryIdDto)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category with id " + id + " not found"));

        product.setCategory(category);
        productRepository.save(product);

        var productDTO = productMapper.map(product);

        return productDTO;
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        productRepository.delete(product);
    }

}
