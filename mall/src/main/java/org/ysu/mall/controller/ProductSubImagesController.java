package org.ysu.mall.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ysu.mall.service.ProductSubImagesService;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/ProductSubImages")
public class ProductSubImagesController {
        private final ProductSubImagesService productSubImagesService;
}
