package com.balgoorm.balgoorm_backend.ide.controller;

import com.balgoorm.balgoorm_backend.ide.service.IdeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ide")
public class IdeController {

    private final IdeService service;




}
