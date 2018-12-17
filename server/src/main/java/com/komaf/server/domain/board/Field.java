package com.komaf.server.domain.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
class Field {
    private Integer position;
    private Status status;
}
