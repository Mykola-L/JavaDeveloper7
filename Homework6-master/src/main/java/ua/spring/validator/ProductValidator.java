package ua.spring.validator;

import ua.spring.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        if (product.getPrice() == null || product.getPrice().signum() < 0) {
            errors.rejectValue("price", "price.productform");
        }
    }
}
