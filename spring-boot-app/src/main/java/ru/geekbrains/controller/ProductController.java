package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.controller.repr.ProductFilter;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

//    private final ProductRepository productRepository;
//
//    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String products(@RequestParam(name = "categoryId", required = false) Long categoryId,
//                           @RequestParam(name = "priceFrom", required = false) BigDecimal priceFrom,
//                           @RequestParam(name = "priceTo", required = false) BigDecimal priceTo,
//                           Model model) {
//        ProductFilter productFilter = new ProductFilter(categoryId != null ? categoryId : -1, priceFrom, priceTo);
//
//        model.addAttribute("filter", productFilter);
//        model.addAttribute("categories", categoryService.findAllWithoutProducts());
//        model.addAttribute("products", productService.filterProducts(productFilter));
//
//        return "products";
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String products(@RequestParam(name = "categoryId", required = false) Long categoryId,
                           @RequestParam(name = "priceFrom", required = false) BigDecimal priceFrom,
                           @RequestParam(name = "priceTo", required = false) BigDecimal priceTo,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        ProductFilter productFilter = new ProductFilter(categoryId != null ? categoryId : -1, priceFrom, priceTo);
        model.addAttribute("filter", productFilter);
        model.addAttribute("categories", categoryService.findAllWithoutProducts());
        Page<ProductRepr> productReprPage = productService.getProductReprPages(PageRequest.of(currentPage - 1, pageSize), productFilter);
        model.addAttribute("products", productReprPage);

        int totalPages = productReprPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "products";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createProductFrom(@RequestParam("categoryId") Long categoryId, Model model) {
        model.addAttribute("product", productService.getEmptyProductReprWithCategory(categoryId));
        return "product";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editProduct(@RequestParam("id") Long id, Model model) {
        model.addAttribute("product", productService.getProductReprById(id));
        return "product";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") ProductRepr productRepr) {
        System.out.println("productRepr: " + productRepr.getId() + " : " + productRepr.getCategoryId());
        productService.save(productRepr);
        return "redirect:/categories/edit?id=" + productRepr.getCategoryId();
    }


}
