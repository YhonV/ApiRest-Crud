package com.yvivas.springboot.api.springbootcrud.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yvivas.springboot.api.springbootcrud.entities.Product;
import com.yvivas.springboot.api.springbootcrud.services.ProductService;
// import com.yvivas.springboot.api.springbootcrud.validation.ProductValidation;

import jakarta.validation.Valid;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService service;
    
    // @Autowired
    // private ProductValidation validation;

    @GetMapping
    public List<Product> list(){
        return service.findAll();
    };

    @GetMapping("/{id}")
    public ResponseEntity<?> viewDetail(@PathVariable Long id){
        Optional<Product> productOptional = service.findById(id); 
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow()); // 200 ok
        }
        return ResponseEntity.notFound().build(); //Error 404 not found
    }

    //Crear, utilizamos PostMapping para crear un nuevo objeto, recordemos que el Id se genera automatico por la BD
    @PostMapping 
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result){

        // validation.validate(product, result);
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Product productNew = service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productNew);
    }

    //Actualizar, utilizamos PutMapping con Path Variable para pedir el id del objeto a actualizar
    @PutMapping("/{id}")                   //Anotacion para validar - al lado de @RequestBody -  BindingResult para personalizar errorzes, a la derecha del objeto                               
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id){

        // validation.validate(product, result);
        if(result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Product> productOptional = service.update(id,product);
        if(productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    
    // Delete, utilizamos DeleteMappin con Path Variable para pedir el id a eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Product> productOptional = service.delete(id); 
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
    private ResponseEntity<?> validation(BindingResult result) {
        Map<String,String> errors = new HashMap<>();
        
        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(),  "El campo "+err.getField() + " " + err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
