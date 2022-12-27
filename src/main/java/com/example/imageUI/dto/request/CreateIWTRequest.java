package com.example.imageUI.dto.request;

import com.example.imageUI.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIWTRequest {
    int id_im;
    Tag id_tg;
}
