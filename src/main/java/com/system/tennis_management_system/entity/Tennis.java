package com.system.tennis_management_system.entity;



import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tennis")

public class Tennis {
    @Id
    @SequenceGenerator(name = "shb_product_seq_gen", sequenceName = "shb_product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "shb_product_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer tennisId;

    private String tennisname;

    private String tennisprice;

    private String tenniscontact;

    private String tennislocation;

    private String tennisimage;
    private String tennisimage1;
    private String tennisimage2;
@Column(length=1000)
    private String Description;

    @Transient
    private String imageBase64;

    @Transient
    private String image1Base64;


    @Transient
    private String image2Base64;

}
