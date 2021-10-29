package com.zhongger.zchat.entity;

import javafx.scene.chart.PieChart;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Contactperson {
    private Integer id;
    private String person_name;
    private String person_phone;
    private Integer user_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    private Date create_date;
    private Integer person_id;
    public Contactperson() {
    }

    public Contactperson(Integer id, String person_name, String person_phone, Integer user_id, Date create_date) {
        this.id = id;
        this.person_name = person_name;
        this.person_phone = person_phone;
        this.user_id = user_id;
        this.create_date = create_date;
    }
}
