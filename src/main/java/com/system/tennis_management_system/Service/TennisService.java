package com.system.tennis_management_system.Service;

import com.system.tennis_management_system.Pojo.TennisPojo;
import com.system.tennis_management_system.entity.Tennis;

import java.io.IOException;
import java.util.List;

public interface TennisService {
    TennisPojo savetennis(TennisPojo tennisPojo) throws IOException;

    Tennis fetchById(Integer id);

    List<Tennis> fetchAll();

    void deleteById(Integer id);
}
