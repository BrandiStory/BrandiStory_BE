package com.supercoding.brandiStory.web.controller.direction;

import com.supercoding.brandiStory.service.exceptions.CAuthenticationEntryPointException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exceptions")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public void entrypointException() {
        throw new CAuthenticationEntryPointException("인증 과정에서 에러");
    }

    @GetMapping("/access-denied")
    public void accessDeniedException() {
        throw new AccessDeniedException("");
    }
}
