package com.account.book.category.dto;

import com.account.book.acbook.dto.AccountBookDTO;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDTO implements Serializable {
    private Integer categorySn;
    private Integer pairSn;
    private String categoryNm;
    private String otherColumn;
    private List<AccountBookDTO> accountBooks;
}
