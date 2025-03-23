package com.jamadev.firstapi.controllers;

import com.jamadev.firstapi.dto.global.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/global")
public class GlobalController {

    @GetMapping("/proof-of-life")
    @PreAuthorize("permitAll()")
    public Result<String> proofOfLife() {
        return Result.success("Proof of life");
    }
}
