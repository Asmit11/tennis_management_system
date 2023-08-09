package com.system.tennis_management_system;

import com.system.tennis_management_system.Repo.TennisRepo;
import com.system.tennis_management_system.entity.Tennis;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TennisRepositoryTest {
    @Autowired
    private TennisRepo tennisRepo;


    @Test
    @Order(1)
    public void savetennisTest() {

        Tennis tennis = Tennis.builder()
                .tennisname("rak")
                .tenniscontact("123454")
                .tennisprice("98888888")
                .build();


        tennisRepo.save(tennis);

        Assertions.assertThat(tennis.getTennisId()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updatetennisTest(){

        Tennis tennis = Tennis.builder()
                .tennisname("rak")
                .tenniscontact("123454")
                .tennisprice("98888888")
                .build();


        tennisRepo.save(tennis);

        Tennis tennis1 = tennisRepo.findById(tennis.getTennisId()).get();

        tennis1.setTenniscontact("13265");

        Tennis tennisupdated  = tennisRepo.save(tennis);

        Assertions.assertThat(tennisupdated.getTenniscontact()).isEqualTo("85207410");

    }
}