package com.berryman.todo.model;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private String firstName;
    private String surName;
    private List<Task> tasks;
}
