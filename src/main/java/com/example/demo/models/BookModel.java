package com.example.demo.models;

import com.example.demo.helpers.DoctorEnum;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "book")
@Data
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "type")
    @Enumerated
    DoctorEnum doctorEnum;

    @Column(name = "time")
    SimpleDateFormat simpleDateFormat;

    @Column(name = "tg_id")
    String tgid;
}
