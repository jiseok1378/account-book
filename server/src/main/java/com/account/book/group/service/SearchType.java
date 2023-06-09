package com.account.book.group.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SearchType {
    FROM("FROM"),
    TO("TO");
    private final String name;
    public static SearchType nameToType(String name){
        switch (name){
            case "FROM" : return FROM;
            case "TO" : return TO;
            default:  return FROM;
        }
    }
}
