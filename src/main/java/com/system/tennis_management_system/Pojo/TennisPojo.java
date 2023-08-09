package com.system.tennis_management_system.Pojo;

import com.system.tennis_management_system.entity.Tennis;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TennisPojo {
    private Integer fid;
    private String fname;
    private String fcontact;
    private  String fprice;
    private String flocation;
    private MultipartFile image;
    private MultipartFile image1;
    private MultipartFile image2;
    private String Description;


    public TennisPojo(Tennis tennis) {
        this.fid = tennis.getTennisId();
        this.fname = tennis.getTennisname();
        this.fcontact= tennis.getTenniscontact();
        this.fprice = tennis.getTennisprice();
        this.flocation = tennis.getTennislocation();
        this.Description = tennis.getDescription();

    }
}