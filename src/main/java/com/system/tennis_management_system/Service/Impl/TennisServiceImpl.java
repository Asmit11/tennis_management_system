package com.system.tennis_management_system.Service.Impl;


import com.system.tennis_management_system.Pojo.TennisPojo;
import com.system.tennis_management_system.Repo.TennisRepo;
import com.system.tennis_management_system.Service.TennisService;
import com.system.tennis_management_system.entity.Tennis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TennisServiceImpl implements TennisService {

    private final TennisRepo tennisRepo;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images/";


    @Override
    public TennisPojo savetennis(TennisPojo tennisPojo) throws IOException {
        Tennis tennis = new Tennis();
        if (tennisPojo.getFid()!= null){
            tennis.setTennisId(tennisPojo.getFid());
        }
        tennis.setTennisname(tennisPojo.getFname());
        tennis.setTennisprice(tennisPojo.getFprice());
        tennis.setTenniscontact(tennisPojo.getFcontact());
        tennis.setTennislocation(tennisPojo.getFlocation());
        tennis.setDescription(tennisPojo.getDescription());



        if(tennisPojo.getImage1()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, tennisPojo.getImage1().getOriginalFilename());
            fileNames.append(tennisPojo.getImage1().getOriginalFilename());
            Files.write(fileNameAndPath, tennisPojo.getImage1().getBytes());

            tennis.setTennisimage1(tennisPojo.getImage1().getOriginalFilename());
        }
        if(tennisPojo.getImage2()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, tennisPojo.getImage2().getOriginalFilename());
            fileNames.append(tennisPojo.getImage2().getOriginalFilename());
            Files.write(fileNameAndPath, tennisPojo.getImage2().getBytes());

            tennis.setTennisimage2(tennisPojo.getImage2().getOriginalFilename());
        }
        if(tennisPojo.getImage()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, tennisPojo.getImage().getOriginalFilename());
            fileNames.append(tennisPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, tennisPojo.getImage().getBytes());

            tennis.setTennisimage(tennisPojo.getImage().getOriginalFilename());
        }
        tennisRepo.save(tennis);
        return new TennisPojo(tennis);
    }

    @Override
    public Tennis fetchById(Integer id) {
        Tennis tennis = tennisRepo.findById(id).orElseThrow(()-> new RuntimeException("Couldnot find"));
        tennis = Tennis.builder()
                .tennisId(tennis.getTennisId())
                .imageBase64(getImageBase64(tennis.getTennisimage()))
                .image1Base64(getImageBase64(tennis.getTennisimage1()))
                .image2Base64(getImageBase64(tennis.getTennisimage2()))
                .tennisname(tennis.getTennisname())
                .tenniscontact(tennis.getTenniscontact())
                .tennisprice(tennis.getTennisprice())
                . tennislocation(tennis.getTennislocation())
                .Description(tennis.getDescription())
                .build();
        return tennis;
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/images/";
        File file = new File(filePath + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public List<Tennis> fetchAll() {
        return tennisRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        tennisRepo.deleteById(id);
    }

}
