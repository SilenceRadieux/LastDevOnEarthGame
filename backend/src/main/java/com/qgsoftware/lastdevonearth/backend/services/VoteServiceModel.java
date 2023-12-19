package com.qgsoftware.lastdevonearth.backend.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoteServiceModel {
    private Long id;
    private String vote;
}
