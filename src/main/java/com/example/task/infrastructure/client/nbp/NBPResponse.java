package com.example.task.infrastructure.client.nbp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
class NBPResponse {

    private String code;

    private List<Rate> rates;

}