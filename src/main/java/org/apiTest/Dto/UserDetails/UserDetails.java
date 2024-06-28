package org.apiTest.Dto.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private ArrayList<Data> data;
    private Support support;
}
