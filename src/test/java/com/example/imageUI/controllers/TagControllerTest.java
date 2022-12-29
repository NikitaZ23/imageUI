package com.example.imageUI.controllers;

import com.example.imageUI.configuration.ConfigurationTests;
import com.example.imageUI.domain.Tag;
import com.example.imageUI.repository.TagRepository;
import com.example.imageUI.service.Imp.TagServiceImp;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ConfigurationTests.class)
public class TagControllerTest {
    @Test
    public void test(){
        System.out.println("check pull");
    }

}
